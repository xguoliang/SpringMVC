/**
 * Copyright 1993-2010 Kingdee , Inc.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */

package com.mongo.morphia.complex.core_dal;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @since 2010-6-4
 * @author guolei_mao
 */
public class SubSystemUtils {
	private static Map<String, String> classNameToSubSystem = new HashMap<String, String>();

	public static void setSubSystem(String className, String subSystem) {
		classNameToSubSystem.put(className, subSystem);
	}

	public static void setSubSystem(Class<?> clazz, String subSystem) {
		setSubSystem(clazz.getName(), subSystem);
	}

	public static String getSubSystem(String className) {
		return classNameToSubSystem.get(className);
	}

	public static String getSubSystem(Class<?> clazz) {
		return getSubSystem(clazz.getName());
	}
}
