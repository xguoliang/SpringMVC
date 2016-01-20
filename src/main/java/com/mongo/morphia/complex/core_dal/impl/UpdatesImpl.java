package com.mongo.morphia.complex.core_dal.impl;



import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.mongo.morphia.complex.core_dal.Updates;
import com.mongo.morphia.complex.core_dal.UpdatesOperation;


/**
 * 更新对象 实现
 * 
 * @author Olafur Gauti Gudmundsson
 * @author yuanpei_lin
 * @author pl 合并 UpdatesImpl 及 Modifiers
 */
public class UpdatesImpl implements Updates, Serializable {	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1689575881193370405L;
	
	private final Map<String,Map<String,Object>> operations;
	
	public UpdatesImpl() {
		this.operations = new HashMap<String,Map<String,Object>>();
    }
	
	@Override
	public Updates addToSet(String field, Object value) {	
		return addOperation("$addToSet", field, value);
	}

	@Override
	public Updates addToSet(String field, List<?> values) {
		return addOperation("$addToSet", field, map("$each", values));
	}

	@Override
	public UpdatesOperation field(String fieldExpr) {
		return new UpdatesOperationImpl(fieldExpr,this);
	}

	@Override
	public Updates inc(String field, int value) {
		return addOperation("$inc", field, value);
	}
	
	@Override
    public Updates incFloat(String field, float value) {
        return addOperation("$inc", field, value);
    }

	@Override
	public Updates popFirst(String field) {
		return addOperation("$pop", field, -1);
	}

	@Override
	public Updates popLast(String field) {
		return addOperation("$pop", field, 1);
	}

	@Override
	public Updates pull(String field, Object value) {
		return addOperation("$pull", field, value);
	}

	@Override
	public Updates pullAll(String field, List<?> values) {
		return addOperation("$pullAll", field, values);
	}

	@Override
	public Updates push(String field, Object value) {
		return addOperation("$push", field, value);
	}

	@Override
	public Updates pushAll(String field, List<?> values) {
		return addOperation("$pullAll", field, values);
	}

	@Override
	public Updates set(String field, Object value) {
		return addOperation("$set", field, value);
	}

	@Override
	public Updates unset(String field) {
		return addOperation("$unset", field, 1);
	}


	@Override
	public Map<String, Map<String, Object>> getOperations() {
		return operations;
	}

    private Updates addOperation( String op, String field, Object value ) {
        if ( !operations.containsKey(op) ) {
            operations.put(op, map(field, valueOf(value)));
        } else {
            operations.get(op).put(field, valueOf(value));
        }
        return this;
    }

    private static Object valueOf( Object obj ) {
        if ( obj.getClass().isEnum() ) {
            return ((Enum<?>)obj).name();
        } else if ( obj instanceof Locale ) {
            return ((Locale)obj).toString();
        } else {
            return obj;
        }
    }

    private static Map<String,Object> map( String key, Object value ) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put(key, value);
        return map;
    }	
}
