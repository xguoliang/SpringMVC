package com.mongo.morphia.complex.core_dal.impl.morphia;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.code.morphia.Key;
import com.google.code.morphia.query.FieldEnd;
import com.google.code.morphia.query.Query;
import com.mongo.morphia.complex.core.BObject;
import com.mongo.morphia.complex.core_dal.CriteriaQuery;
import com.mongo.morphia.complex.core_dal.CriteriaQueryField;
import com.mongodb.DBRef;
/**
 * 动态查询field part 实现
 * @author yuanpei_lin
 *
 * @param <E>
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class CriteriaQueryFieldImpl<E> implements CriteriaQueryField<E> {
	
    private CriteriaQueryImpl<E> criteraQuery;
    private FieldEnd<? extends Query<E>> fields;

    public CriteriaQueryFieldImpl(FieldEnd<? extends Query<E>> fields, CriteriaQueryImpl<E> criteraQuery) {
        this.criteraQuery = criteraQuery;
        this.fields = fields;
    }

    @Override
    public CriteriaQuery<E> doesNotExist() {
        fields.doesNotExist();
        return criteraQuery;
    }

    @Override
    public CriteriaQuery<E> equal(Object val) {
        // added by mgl 2010-11-11
        if (val instanceof BObject) {
            Key<DBRef> key = getKey(val);
            List<Key> list = new ArrayList<Key>(2);
            list.add(key);
            fields.hasAnyOf(list);
        } else {
            fields.equal(val);
        }
        return criteraQuery;
    }

	@Override
	public CriteriaQuery<E> exists() {
		fields.exists();
		return criteraQuery;
	}

	@Override
	public CriteriaQuery<E> greaterThan(Object val) {
		fields.greaterThan(val);
		return criteraQuery;
	}

	@Override
	public CriteriaQuery<E> greaterThanOrEq(Object val) {
		fields.greaterThanOrEq(val);
		return criteraQuery;
	}

	@Override
	public CriteriaQuery<E> hasAllOf(Iterable<?> vals) {
		fields.hasAllOf(vals);
		return criteraQuery;
	}

    @Override
    public CriteriaQuery<E> hasAnyOf(Iterable<?> vals) {
        // added by mgl 2010-11-11
        Iterator ite = vals.iterator();
        if (ite.hasNext() && (ite.next() instanceof BObject)) {
            List keys = getKeys(vals);
            fields.hasAnyOf(keys);
        } else {
            fields.hasAnyOf(vals);
        }
        return criteraQuery;
    }

    @Override
    public CriteriaQuery<E> hasNoneOf(Iterable<?> vals) {
        // added by mgl 2010-11-11
        Iterator ite = vals.iterator();
        if (ite.hasNext() && (ite.next() instanceof BObject)) {
            List keys = getKeys(vals);
            fields.hasNoneOf(keys);
        } else {
            fields.hasNoneOf(vals);
        }
        return criteraQuery;
    }

    private List getKeys(Iterable<?> vals) {
        List keys = new ArrayList();
        for (Object object : vals) {
            if (object instanceof BObject) {
                BObject val = (BObject) object;
                String collectionName = criteraQuery.getMongoHelper().getCollectionName(val.getClass());
                Key key = new Key(collectionName, ((BObject) val).getId());
                keys.add(key);
            } else {
                keys.add(object);
            }
        }
        return keys;
    }

	@Override
	public CriteriaQuery<E> hasThisElement(Object val) {
		fields.hasThisElement(val);
		return criteraQuery;
	}

	@Override
	public CriteriaQuery<E> hasThisOne(Object val) {
		fields.hasThisOne(val);
		return criteraQuery;
	}

	@Override
	public CriteriaQuery<E> lessThan(Object val) {
		fields.lessThan(val);
		return criteraQuery;
	}

	@Override
	public CriteriaQuery<E> lessThanOrEq(Object val) {
		fields.lessThanOrEq(val);
		return criteraQuery;
	}

    @Override
    public CriteriaQuery<E> notEqual(Object val) {
        // added by mgl 2010-11-11
        if (val instanceof BObject) {
            Key<DBRef> key = getKey(val);
            fields.notEqual(key);
        } else {
            fields.notEqual(val);
        }
        return criteraQuery;
    }


    private Key<DBRef> getKey(Object val) {
        String collectionName = criteraQuery.getMongoHelper().getCollectionName(val.getClass());
        Key key = new Key(collectionName, ((BObject) val).getId());
        return key;
    }
	
    // added by mgl 2010-10-13
    @Override
    // 如果是关联对象上的字段，做模糊查询，使用query.filter：
    // query.filter("关联字段.关联对象上的某字段名", Pattern.compile("^" + prefix));
    public CriteriaQuery<E> startsWith(String prefix) {
        fields.startsWith(prefix);
        return criteraQuery;
    }

    @Override
    public CriteriaQuery<E> startsWithIgnoreCase(String prefix) {
        fields.startsWithIgnoreCase(prefix);
        return criteraQuery;
    }

    @Override
    public CriteriaQuery<E> endsWith(String postfix) {
        fields.endsWith(postfix);
        return criteraQuery;
    }

    @Override
    public CriteriaQuery<E> endsWithIgnoreCase(String postfix) {
        fields.endsWithIgnoreCase(postfix);
        return criteraQuery;
    }

    @Override
    // 如果是关联对象上的字段，做模糊查询，使用query.filter：
    // query.filter("关联字段.关联对象上的某字段名", Pattern.compile(string));
    public CriteriaQuery<E> contains(String string) {
        fields.contains(string);
        return criteraQuery;
    }

    @Override
    // 如果是关联对象上的字段，做模糊查询，使用query.filter：
    // query.filter("关联字段.关联对象上的某字段名", Pattern.compile(string, , Pattern.CASE_INSENSITIVE));
    public CriteriaQuery<E> containsIgnoreCase(String string) {
        fields.containsIgnoreCase(string);
        return criteraQuery;
    }

}
