package com.mongo.morphia.complex.kingdee.core;

/**
 * Copyright 1993-2010 Kingdee Software (China), Co., Ltd. , Inc.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */



import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.bson.types.ObjectId;

import com.mongo.morphia.complex.kingdee.core.server.BObject;





/**
 * 
 * @since 2010-7-22
 * @author guolei_mao
 */
public class Utils {
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static Number max(Number n1, Number n2) {
        if (n1 instanceof Comparable && n2 instanceof Comparable) {
            if (((Comparable) n1).compareTo(n2) >= 0)
                return n1;
            else
                return n2;
        }
        throw new IllegalArgumentException("n1, n2 must be Comparable");
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static Number min(Number n1, Number n2) {
        if (n1 instanceof Comparable && n2 instanceof Comparable) {
            if (((Comparable) n1).compareTo(n2) < 0)
                return n1;
            else
                return n2;
        }
        throw new IllegalArgumentException("n1, n2 must be Comparable");
    }

    public static Number sum(Number n1, Number n2) {
        if (n1 instanceof Integer && n2 instanceof Integer) {
            return new Integer((((Integer) n1).intValue() + ((Integer) n2).intValue()));
        } else if (n1 instanceof Long && n2 instanceof Long) {
            return new Long((((Long) n1).longValue() + ((Long) n2).longValue()));
        } else if (n1 instanceof Double && n2 instanceof Double) {
            return new Double((((Double) n1).doubleValue() + ((Double) n2).doubleValue()));
        } else if (n1 instanceof Float && n2 instanceof Float) {
            return new Float((((Float) n1).floatValue() + ((Float) n2).floatValue()));
        } else if (n1 instanceof Short && n2 instanceof Short) {
            return new Short((short) ((((Short) n1).shortValue() + ((Short) n2).shortValue())));
        } else if (n1 instanceof BigInteger && n2 instanceof BigInteger) {
            return ((BigInteger) n1).add((BigInteger) n2);
        } else if (n1 instanceof BigDecimal && n2 instanceof BigDecimal) {
            return ((BigDecimal) n1).add((BigDecimal) n2);
        }
        throw new IllegalArgumentException(
                "n1, n2 must be Integer, Long, Double, Float, Short, BigInteger or BigDecimal");
    }

    public static Number avg(Number sum, int size) {
        if (sum instanceof Integer) {
            return new Integer((Integer) sum / size);
        } else if (sum instanceof Long) {
            return new Long((((Long) sum).longValue() / size));
        } else if (sum instanceof Double) {
            return new Double((((Double) sum).doubleValue() / size));
        } else if (sum instanceof Float) {
            return new Float((((Float) sum).floatValue() / size));
        } else if (sum instanceof Short) {
            return new Short((short) ((((Short) sum).shortValue() / size)));
        } else if (sum instanceof BigInteger) {
            return ((BigInteger) sum).divide(BigInteger.valueOf(size));
        } else if (sum instanceof BigDecimal) {
            return ((BigDecimal) sum).divide(BigDecimal.valueOf(size));
        }
        throw new IllegalArgumentException(
                "n1, n2 must be Integer, Long, Double, Float, Short, BigInteger or BigDecimal");
    }

    public static void generateId(BObject bo) throws IllegalArgumentException, IllegalAccessException {
        Set<BObject> set = getAssociateBObjects(bo);
        for (Iterator<BObject> iterator = set.iterator(); iterator.hasNext();) {
            BObject bObject = iterator.next();
            if (bObject.getId() == null || bObject.getId().trim().equals(""))
                bObject.setId(ObjectId.get().toString());
        }
    }

    private static Set<BObject> getAssociateBObjects(BObject bo) throws IllegalArgumentException,
            IllegalAccessException {
        Set<BObject> set = new HashSet<BObject>();
        getAssociateBObjects(bo, set);
        return set;
    }

    @SuppressWarnings("rawtypes")
    private static void getAssociateBObjects(BObject bo, Set<BObject> set) throws IllegalArgumentException,
            IllegalAccessException {
        if (set.contains(bo))
            return;

        set.add(bo);
        Set<Field> fields = getFields(bo);
        for (Iterator<Field> iterator = fields.iterator(); iterator.hasNext();) {
            Field field = iterator.next();
            if (BObject.class.isAssignableFrom(field.getType())) {
                field.setAccessible(true);
                BObject b = (BObject) field.get(bo);
                if (b != null)
                    getAssociateBObjects(b, set);
            } else if (Collection.class.isAssignableFrom(field.getType())) {
                field.setAccessible(true);
                Collection c = (Collection) field.get(bo);
                if (c != null && c.size() > 0) {
                    Iterator ite = c.iterator();
                    while (ite.hasNext()) {
                        Object o = ite.next();
                        if (o instanceof BObject)
                            getAssociateBObjects((BObject) o, set);
                    }
                }
            }
        }
    }

    @SuppressWarnings("rawtypes")
    private static Set<Field> getFields(BObject bo) {
        Set<Field> set = new HashSet<Field>();
        Class parent = bo.getClass();
        while (parent != null) {
            Field[] fields = parent.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                set.add(fields[i]);
            }
            parent = parent.getSuperclass();
        }
        return set;
    }
}
