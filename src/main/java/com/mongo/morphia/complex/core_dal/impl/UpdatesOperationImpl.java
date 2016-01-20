package com.mongo.morphia.complex.core_dal.impl;



import java.util.List;

import com.mongo.morphia.complex.core_dal.Updates;
import com.mongo.morphia.complex.core_dal.UpdatesOperation;


/**
 * 根据field构建updates
 * @author yuanpei_lin
 *
 */
public class UpdatesOperationImpl implements UpdatesOperation {
	private String field;
	private Updates updates;
	public UpdatesOperationImpl(String field,Updates updates){
		this.field = field;
		this.updates = updates;
	}
	@Override
	public Updates addToSet(Object value) {
		return updates.addToSet(field, value);
	}

	@Override
	public Updates addToSet(List<?> values) { 
		return updates.addToSet(field, values);
	}

	@Override
	public Updates inc(int value) {
		return updates.inc(field, value);
	}

	@Override
	public Updates popFirst() {
		return updates.popFirst(field);
	}

	@Override
	public Updates popLast() {
		return updates.popLast(field);
	}

	@Override
	public Updates pull(Object value) {
		return updates.pull(field, value);
	}

	@Override
	public Updates pullAll(List<?> values) {
		return updates.pullAll(field, values);
	}

	@Override
	public Updates push(Object value) {
		return updates.push(field, value);
	}

	@Override
	public Updates pushAll(List<?> values) {
		return updates.pushAll(field, values);
	}

	@Override
	public Updates set(Object value) {
		return updates.set(field, value);
	}
	@Override
	public Updates unset() {
		return updates.unset(field);
	}

}
