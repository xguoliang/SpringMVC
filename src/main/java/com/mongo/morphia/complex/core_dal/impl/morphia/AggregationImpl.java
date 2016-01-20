package com.mongo.morphia.complex.core_dal.impl.morphia;

/**
 * Copyright 1993-2010 Kingdee Software (China), Co., Ltd. , Inc.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.code.morphia.DatastoreImpl;
import com.mongo.morphia.complex.core_dal.Aggregation;
import com.mongo.morphia.complex.core_dal.AggregationResult;
import com.mongo.morphia.complex.core_dal.CriteriaQuery;
import com.mongo.morphia.complex.core_dal.CriteriaQueryField;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MapReduceCommand;
import com.mongodb.MapReduceOutput;
import com.mongodb.QueryOperators;
import com.mongodb.ReadPreference;

/**
 * 
 * @since 2010-7-24
 * @author guolei_mao
 */
public class AggregationImpl<E> implements Aggregation<E> {
    private static Logger logger = LoggerFactory.getLogger(AggregationImpl.class);
    private MongoHelperImpl mongohelper;
    private DBCollection coll;
    private Class<E> clazz;

    private Map<String, String> sum = new HashMap<String, String>(4);
    private Map<String, String> avg = new HashMap<String, String>(4);
    private Map<String, String> max = new HashMap<String, String>(4);
    private Map<String, String> min = new HashMap<String, String>(4);
    private boolean count = false;
    private List<String> groupBy = new ArrayList<String>(4);
    private CriteriaQueryImpl<E> query;
    private DBObject having;
    private DBObject orderBy;

    public AggregationImpl(MongoHelperImpl mongohelper, DBCollection coll, Class<E> clazz) {
        this.mongohelper = mongohelper;
        this.coll = coll;
        this.clazz = clazz;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.kingdee.cbos.core.framework.dal.Aggregation#sum(java.lang.String)
     */
    @Override
    public Aggregation<E> sum(String fieldName) {
        String[] fns = fieldName.split(",");
        for (String fn : fns) {
            sum.put("sum_" + fn.trim(), fn.trim());
        }

        return this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.kingdee.cbos.core.framework.dal.Aggregation#count()
     */
    @Override
    public Aggregation<E> count() {
        count = true;
        return this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.kingdee.cbos.core.framework.dal.Aggregation#avg(java.lang.String)
     */
    @Override
    public Aggregation<E> avg(String fieldName) {
        String[] fns = fieldName.split(",");
        for (String fn : fns) {
            // avg.put("avg_" + fn.trim(), fn.trim());
            // DBCollection.mapreduce貌似不支持finalize，所以变通处理
            if (!sum.containsKey("sum_" + fn.trim()))
                sum(fieldName);
            count = true;
        }

        return this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.kingdee.cbos.core.framework.dal.Aggregation#min(java.lang.String)
     */
    @Override
    public Aggregation<E> min(String fieldName) {
        String[] fns = fieldName.split(",");
        for (String fn : fns) {
            min.put("min_" + fn.trim(), fn.trim());
        }

        return this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.kingdee.cbos.core.framework.dal.Aggregation#max(java.lang.String)
     */
    @Override
    public Aggregation<E> max(String fieldName) {
        String[] fns = fieldName.split(",");
        for (String fn : fns) {
            max.put("max_" + fn.trim(), fn.trim());
        }

        return this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.kingdee.cbos.core.framework.dal.Aggregation#groupBy(java.lang.String)
     */
    @Override
    public Aggregation<E> groupBy(String fieldName) {
        String[] fns = fieldName.split(",");
        for (String fn : fns) {
            groupBy.add(fn.trim());
        }

        return this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.kingdee.cbos.core.framework.dal.Aggregation#having(java.lang.String,
     * java.lang.Object)
     */
    @Override
    public Aggregation<E> having(String condition, Object value) {
        if (having == null) {
            having = new BasicDBObject();
        }
        
        String[] sa = condition.split(" ");
        if (sa[0].contains("count"))
            sa[0] = sa[0].replace("count()", "count");
        else {
            sa[0] = sa[0].replace('(', '_');
            sa[0] = sa[0].replace(")", "");
        }
        
        if (sa.length == 1 || "=".equals(sa[1])) {
            having.put("value." + sa[0], value);
        } else {
            if (">".equals(sa[1]))
                sa[1] = QueryOperators.GT;
            else if (">=".equals(sa[1]))
                sa[1] = QueryOperators.GTE;
            else if ("<".equals(sa[1]))
                sa[1] = QueryOperators.LT;
            else if ("<=".equals(sa[1]))
                sa[1] = QueryOperators.LTE;
            else if ("!=".equals(sa[1]) || "<>".equals(sa[1]))
                sa[1] = QueryOperators.NE;
            else if ("in".equalsIgnoreCase(sa[1]))
                sa[1] = QueryOperators.IN;
            else if ("not in".equalsIgnoreCase(sa[1]))
                sa[1] = QueryOperators.NIN;

            BasicDBObject param = (BasicDBObject) having.get("value." + sa[0]);
            if (param == null) {
                param = new BasicDBObject();
            }
            param.put(sa[1], value);
            having.put("value." + sa[0], param);
        }

        return this;
    }
    
    @Override
    public Aggregation<E> order(String condition) {
        BasicDBObjectBuilder ret = BasicDBObjectBuilder.start();
        String[] sa = condition.split(",");
        for (int i = 0; i < sa.length; i++) {
            if (sa[i].contains("count"))
                sa[i] = sa[i].replace("count()", "count");
            else {
                sa[i] = sa[i].replace('(', '_');
                sa[i] = sa[i].replace(")", "");
            }

            int dir = 1;
            if (sa[i].startsWith("-")) {
                dir = -1;
                sa[i] = sa[i].substring(1).trim();
            }

            // ret = ret.add("value." + sa[i], dir);
            ret = ret.add(sa[i], dir);
        }

        orderBy = ret.get();

        return this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.kingdee.cbos.core.framework.dal.Aggregation#having(java.lang.String)
     */
    @Override
    public CriteriaQueryField<E> having(String fieldName) {
        if (query == null) {
            DatastoreImpl ds = new DatastoreImpl(mongohelper.getMorphia(), mongohelper.getMongo(), mongohelper.getDb()
                    .getName());
            MongoQueryImpl<E> qi = new MongoQueryImpl<E>(clazz, coll, ds);
            query = new CriteriaQueryImpl<E>(qi, mongohelper);
        }

        return query.field(fieldName);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.kingdee.cbos.core.framework.dal.Aggregation#query(com.kingdee.cbos.core.framework.dal
     * .CriteriaQuery)
     */
    @Override
    public Aggregation<E> query(CriteriaQuery<E> query) {
        this.query = (CriteriaQueryImpl<E>) query;
        return this;
    }

    public List<AggregationResult> asList() {
        List<AggregationResult> list = new ArrayList<AggregationResult>();

        if (!mongohelper.getDb().collectionExists(mongohelper.getCollectionName()))
            return list;
        
        StringBuffer cmd = new StringBuffer();

        // map
        cmd.append("function() { emit(\r\n");
        cmd.append("{");
        if (groupBy.size() > 0) {
            for (int i = 0; i < groupBy.size(); i++) {
                if (i != 0)
                    cmd.append(", ");
                cmd.append(groupBy.get(i)).append(": this.");
                cmd.append(groupBy.get(i));
            }
        } else
            cmd.append("gid: this._id");
        cmd.append("},\r\n");

        cmd.append("{");
        if (sum.size() > 0) {
            List<String> temp = new ArrayList<String>(sum.keySet());
            for (int i = 0; i < temp.size(); i++) {
                if (i != 0)
                    cmd.append(", ");
                cmd.append(temp.get(i)).append(": ").append("this.").append(sum.get(temp.get(i)));
            }
        }
        if (count) {
            if (sum.size() > 0)
                cmd.append(", ");
            cmd.append("count: 1 ");
        }
        if (min.size() > 0) {
            if (sum.size() > 0 || count)
                cmd.append(", ");
            List<String> temp = new ArrayList<String>(min.keySet());
            for (int i = 0; i < temp.size(); i++) {
                if (i != 0)
                    cmd.append(", ");
                cmd.append(temp.get(i)).append(": ").append("this.").append(min.get(temp.get(i)));
            }
        }
        if (max.size() > 0) {
            if (sum.size() > 0 || count || min.size() > 0)
                cmd.append(", ");
            List<String> temp = new ArrayList<String>(max.keySet());
            for (int i = 0; i < temp.size(); i++) {
                if (i != 0)
                    cmd.append(", ");
                cmd.append(temp.get(i)).append(": ").append("this.").append(max.get(temp.get(i)));
            }
        }
        cmd.append("}\r\n");

        cmd.append(");};\r\n");
        String map = cmd.toString();

        // reduce
        cmd = new StringBuffer();
        cmd.append("function(key, vals) {\r\n");
        cmd.append("var ret = {");
        if (sum.size() > 0) {
            List<String> temp = new ArrayList<String>(sum.keySet());
            for (int i = 0; i < temp.size(); i++) {
                if (i != 0)
                    cmd.append(", ");
                cmd.append(temp.get(i)).append(": ").append(0);
            }
        }
        if (count) {
            if (sum.size() > 0)
                cmd.append(", ");
            cmd.append("count: 0");
        }
        if (min.size() > 0) {
            if (sum.size() > 0 || count)
                cmd.append(", ");
            List<String> temp = new ArrayList<String>(min.keySet());
            for (int i = 0; i < temp.size(); i++) {
                if (i != 0)
                    cmd.append(", ");
                cmd.append(temp.get(i)).append(": ").append(Integer.MAX_VALUE);
            }
        }
        if (max.size() > 0) {
            if (sum.size() > 0 || count || min.size() > 0)
                cmd.append(", ");
            List<String> temp = new ArrayList<String>(max.keySet());
            for (int i = 0; i < temp.size(); i++) {
                if (i != 0)
                    cmd.append(", ");
                cmd.append(temp.get(i)).append(": ").append(Integer.MIN_VALUE);
            }
        }
        cmd.append(" };\r\n");

        cmd.append("for(var i = 0; i < vals.length; i++) {\r\n");
        if (sum.size() > 0) {
            List<String> temp = new ArrayList<String>(sum.keySet());
            for (int i = 0; i < temp.size(); i++) {
                if (i != 0)
                    cmd.append("; ");
                cmd.append("ret.").append(temp.get(i)).append(" += vals[i].").append(temp.get(i));
            }
        }
        if (count) {
            if (sum.size() > 0)
                cmd.append("; ");
            cmd.append("ret.count += vals[i].count");
        }
        if (min.size() > 0) {
            if (sum.size() > 0 || count)
                cmd.append("; ");
            List<String> temp = new ArrayList<String>(min.keySet());
            for (int i = 0; i < temp.size(); i++) {
                if (i != 0)
                    cmd.append("; ");
                cmd.append("if(vals[i].").append(temp.get(i)).append(" < ret.").append(temp.get(i)).append(")");
                cmd.append(" ret.").append(temp.get(i)).append(" = vals[i].").append(temp.get(i));
            }
        }
        if (max.size() > 0) {
            if (sum.size() > 0 || count || min.size() > 0)
                cmd.append("; ");
            List<String> temp = new ArrayList<String>(max.keySet());
            for (int i = 0; i < temp.size(); i++) {
                if (i != 0)
                    cmd.append("; ");
                cmd.append("if(vals[i].").append(temp.get(i)).append(" > ret.").append(temp.get(i)).append(")");
                cmd.append(" ret.").append(temp.get(i)).append(" = vals[i].").append(temp.get(i));
            }
        }
        cmd.append(";}\r\n");
        cmd.append("return ret;\r\n");
        cmd.append("}\r\n");
        String reduce = cmd.toString();

        // finalize
        cmd = new StringBuffer();
        if (avg.size() > 0) {
            cmd.append("function(key, val) {\r\n");
            List<String> temp = new ArrayList<String>(avg.keySet());
            for (int i = 0; i < temp.size(); i++) {
                cmd.append("val.").append(temp.get(i));
                cmd.append(" = val.").append(temp.get(i).replace("avg", "sum")).append(" / val.count;\r\n");
            }
            cmd.append("return val;\r\n");
            cmd.append("}\r\n");
        }
        // cmd.append("out: 'result_mgltest',\r\n");
        // cmd.append("verbose: true\r\n");
        // cmd.append("}");
        // cmd.append(";\r\n");

        String finalize = cmd.toString();

        BasicDBObjectBuilder dbob = BasicDBObjectBuilder.start().add("mapreduce", mongohelper.getCollectionName()).add(
                "map", map).add("reduce", reduce);

        if (avg.size() > 0)
            dbob.add("finalize", finalize);

        // String outputCollection = mongohelper.getCollectionName() + "_aggrgt";
        // dbob.add("out", outputCollection);
        // mongohelper.getDb().getCollection(outputCollection).drop();
        
        if (this.query != null)
            dbob.add("query", this.query.getQueryObject());

        // MapReduceOutput cr = mongohelper.getCollection(clazz).mapReduce(map, reduce, null,
        // query);
        MapReduceOutput cr = null;
        try {
            DBObject mq = null;
            if (this.query != null)
                mq = this.query.getQueryObject();
            DBCollection coll = mongohelper.getCollection(clazz);
            coll.setReadPreference(ReadPreference.secondaryPreferred());
            cr = coll.mapReduce(map, reduce, null, MapReduceCommand.OutputType.INLINE, mq);
            Iterable<DBObject> ite = cr.results();
            for (DBObject dbObject : ite) {
                DBObject value = (DBObject) dbObject.get("value");
                value.putAll((DBObject) dbObject.get("_id"));
                AggregationResultImpl ar = new AggregationResultImpl(value);
                list.add(ar);
            }
        } finally {
            if (cr != null) {
                try {
                    cr.drop();
                } catch (Exception e) {
                    logger.debug("", e);
                }
            }
        }

        if (orderBy != null) {
            Collections.sort(list, new AggrComparator(orderBy));
        }
        
        return list;
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private static class AggrComparator<AggregationResult> implements Comparator<AggregationResultImpl> {
        DBObject orderBy;

        AggrComparator(DBObject orderBy) {
            this.orderBy = orderBy;
        }

        @Override
        public int compare(AggregationResultImpl ar1, AggregationResultImpl ar2) {
            DBObject dbo1 = ar1.getDbo();
            DBObject dbo2 = ar2.getDbo();
            List<String> keys = new ArrayList<String>(orderBy.keySet());
            Object o1 = dbo1.get(keys.get(0));
            Object o2 = dbo2.get(keys.get(0));
            if (o1 instanceof Comparable && o2 instanceof Comparable) {
                if ((Integer) orderBy.get(keys.get(0)) > 0) {
                    return ((Comparable) o1).compareTo(o2);
                } else {
                    return ((Comparable) o2).compareTo(o1);
                }
            }
            return 0;
        }
    }
}
