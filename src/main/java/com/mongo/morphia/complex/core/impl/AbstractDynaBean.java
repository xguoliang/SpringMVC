/**
 * Copyright 1993-2010 Kingdee Software (China) Co., Ltd.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */

package com.mongo.morphia.complex.core.impl;

import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.beanutils.NestedNullException;
import org.apache.commons.beanutils.PropertyUtils;

import com.google.common.base.Throwables;
import com.mongo.morphia.complex.core.DynaBean;


/**
 * {@code DynaBean }实现
 * 
 * @since 2010-5-20
 * @author pl
 */
public abstract class AbstractDynaBean implements DynaBean, Serializable {

	private static final long serialVersionUID = 7185445740465709143L;

	/**
	 * maybe null.
	 */
    // extMap的value为基本类型：Integer, Long, Double, String, Date等
	protected Map<String, Object> extMap;

	public AbstractDynaBean() {
		this(null);
	}

	/**
	 * @param extMap
	 *            扩展部分。如果{@code key }与{@code pojo }中的属性名重复，将会被忽略。
	 */
	public AbstractDynaBean(Map<String, Object> extMap) {
		initExtProperties(extMap);
	}

	protected void initExtProperties(Map<String, Object> extProperties) {
		if (extProperties == null || extProperties.size() == 0)
			return;

		Map<String, Object> map = new LinkedHashMap<String, Object>(extProperties);
		for (PropertyDescriptor pd : PropertyUtils.getPropertyDescriptors(getClass())) {
			map.remove(pd.getName());
		}

		if (map.size() > 0) {
			extMap = map;
		}
	}

	@Override
	public Map<String, Object> extended() {
		return extMap;
	}

	@Override
	public boolean containsField(String s) {
		if (PropertyUtils.isReadable(this, s) || PropertyUtils.isWriteable(this, s))
			return true;

		return (extMap != null && extMap.containsKey(s));
	}

	@Override
	public Object get(String key) {
		if (PropertyUtils.isReadable(this, key)) {
			try {
				return PropertyUtils.getProperty(this, key);
			} catch (NestedNullException nne) {				
				return null;
			} catch (Throwable th) {
				Throwables.propagate(th);
			}
		}

		if (extMap != null)
			return extMap.get(key);

		return null;
	}

	@Override
	public Set<String> keySet() {
		Set<String> keys = new HashSet<String>();
		for (PropertyDescriptor pd : PropertyUtils.getPropertyDescriptors(getClass())) {
			String key = pd.getName();
			if (!"class".equals(key)) {
				keys.add(key);
			}
		}

		if (extMap != null)
			keys.addAll(extMap.keySet());

		return keys;
	}

	@Override
	public Object put(String key, Object v) {
		if (PropertyUtils.isWriteable(this, key)) {
			try {
				PropertyUtils.setProperty(this, key, v);
				return v;
			} catch (Throwable th) {
				Throwables.propagate(th);
			}
		}
		if (extMap == null)
			extMap = new LinkedHashMap<String, Object>();
		return extMap.put(key, v);
	}

	@Override
	public void putAll(DynaBean o) {
		for (String k : o.keySet()) {
			put(k, o.get(k));
		}
	}

	@Override
	public void putAll(Map<String, Object> m) {
		for (Map.Entry<String, Object> entry : m.entrySet()) {
			put(entry.getKey(), entry.getValue());
		}
	}

	@Override
	public Object removeField(String key) {
		if (extMap != null)
			return extMap.remove(key);

		return null;
	}

	@Override
	public Map<String, Object> toMap() {
		// 滤掉class域
		@SuppressWarnings("unchecked")
		Map<String, Object> allMap = new HashMap<String, Object>(new BeanMap(this));
		if (allMap.containsKey("class")) {
			allMap.remove("class");
		}
		if (extMap != null)
			allMap.putAll(extMap);

		return allMap;
	}

	private void writeObject(ObjectOutputStream oos) throws IOException {
		oos.defaultWriteObject();
		if (extMap == null || extMap.size() == 0) {
			oos.writeObject(null);
		} else {
			oos.writeObject(extMap);
		}
	}

	@SuppressWarnings("unchecked")
	private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
		ois.defaultReadObject();
		extMap = (Map<String, Object>) ois.readObject();
	}
}