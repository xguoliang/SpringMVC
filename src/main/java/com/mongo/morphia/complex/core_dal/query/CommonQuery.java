package com.mongo.morphia.complex.core_dal.query;

/**
 * Copyright 1993-2010 Kingdee Software (China), Co., Ltd. , Inc.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */




import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.mongo.morphia.complex.core.BObject;
import com.mongo.morphia.complex.core.ContextToken;
import com.mongo.morphia.complex.core_dal.CriteriaQuery;
import com.mongo.morphia.complex.core_dal.CriteriaQueryField;
import com.mongo.morphia.complex.core_dal.DAOFactory;
import com.mongo.morphia.complex.core_dal.Results;
import com.mongo.morphia.complex.core_dal.annotation.Relation;



/**
 * <pre>
 * 
 * 假设Bill和Bill2不在同一个PersistenceUnit，和BillEntry、Bill3在同一个PersistenceUnit
 * Bill                                                  BillType
 * 
 * int i1                                                int i1
 * 
 * int i2                                                int i2
 * 
 * List<BillEntry> entries                               List<BillEntryType> entries
 * 
 * @Relation(entity = Bill2.class, name = "bill2")       @Adapt("bill2")
 * String bill2_id                                       Bill2Type bill2Type
 * 
 *                                                       @Adapt("bill3")
 * Bill3 bill3                                           Bill3Type bill3Type
 * 
 *                                                       Bill3Type bill3
 *                                                       
 *                                                       @Adapt("bill2.i2")
 *                                                       int a;
 *                                                       
 *                                                       @Adapt("i1+i2")
 *                                                       int b
 *                                                       
 *                                                       @Adapt("i1+bill2.i2")
 *                                                       int c
 *                                                       
 *                                                       
 * CommonQuery<Bill> cq = new CommonQuery<Bill>(contextToken, Bill.class);
 * cq.filter("bill2.i2 < ", 5);
 * cq.filter("i1 !=", 19);
 * List<BillType> list = TypeTranslator.translate(contextToken, cq, BillType.class);
 * 
 * </pre>
 *
 * @since 2010-6-29
 * @author guolei_mao
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class CommonQuery<T extends BObject> {
    private ContextToken ct;
    private Class<T> entityClass;
    private CriteriaQuery<T> query;

    public CommonQuery(ContextToken ct, Class<T> entityClass) {
        this.ct = ct;
        this.entityClass = entityClass;
        query = DAOFactory.getInstance(ct, entityClass).getCriteriaQueryBuilder().createCriteriaQuery();
    }

    /* (non-Javadoc)
     * @see com.kingdee.cbos.core.framework.dal.CriteriaQuery#filter(java.lang.String, java.lang.Object)
     */
    public CommonQuery<T> filter(String condition, Object value) {
        Relation relation = containRelation(condition);
        if (relation == null) {
            if (condition.equals("id") || condition.startsWith("id =")) {
                condition = condition.replace("id", "_id");
            }
            query.filter(condition, value);
        } else {
            getQuery(relation).filter(condition.replace(relation.name() + ".", ""), value);
        }

        return this;
    }

    /* (non-Javadoc)
     * @see com.kingdee.cbos.core.framework.dal.CriteriaQuery#field(java.lang.String)
     */
    public CommonQueryField<T> field(String field) {
        if (field.equals("id")) {
            field = field.replace("id", "_id");
        }
        CriteriaQueryField<T> cqf = query.field(field);
        return new CommonQueryField<T>(this, cqf);
    }

    /* (non-Javadoc)
     * @see com.kingdee.cbos.core.framework.dal.CriteriaQuery#order(java.lang.String)
     */
    public CommonQuery<T> order(String condition) {
        query.order(condition);
        return this;
    }

    /* (non-Javadoc)
     * @see com.kingdee.cbos.core.framework.dal.CriteriaQuery#limit(int)
     */
    public CommonQuery<T> limit(int value) {
        query.limit(value);
        return this;
    }

    /* (non-Javadoc)
     * @see com.kingdee.cbos.core.framework.dal.CriteriaQuery#offset(int)
     */
    public CommonQuery<T> offset(int value) {
        query.limit(value);
        return this;
    }

    private Map<Relation, Field> relations;

    private Relation containRelation(String condition) {
        Map<Relation, Field> relations = getRelations();
        for (Map.Entry<Relation, Field> entry : relations.entrySet()) {
            if (condition.indexOf(entry.getKey().name() + ".") >= 0) {
                return entry.getKey();
            }
        }
        return null;
    }

    private Map<Relation, Field> getRelations() {
        if (relations == null) {
            relations = new HashMap<Relation, Field>();
            Field[] fields = entityClass.getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(Relation.class)) {
                    relations.put(field.getAnnotation(Relation.class), field);
                }
            }
        }
        return relations;
    }
    
    private Map<Relation, CriteriaQuery> queries;

    private CriteriaQuery getQuery(Relation relation) {
        if (queries == null)
            queries = new HashMap<Relation, CriteriaQuery>();
        CriteriaQuery query = queries.get(relation);

        if (query == null) {
            query = DAOFactory.getInstance(ct, relation.entity()).getCriteriaQueryBuilder().createCriteriaQuery();
            queries.put(relation, query);
        }
        return query;
    }
    
    public Results<T> getResults() {
        if (queries != null) {
            for (Map.Entry<Relation, CriteriaQuery> entry : queries.entrySet()) {
                Results ids = DAOFactory.getInstance(ct, entry.getKey().entity()).findIds(entry.getValue());
                if (ids != null && ids.asList().size() > 0) {
                    query.filter(relations.get(entry.getKey()).getName() + " in", ids.asList());
                }
            }
        }

        return DAOFactory.getInstance(ct, entityClass).query(query);
    }
}
