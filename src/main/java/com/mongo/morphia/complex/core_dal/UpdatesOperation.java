/**
 * Copyright 1993-2010 Kingdee Software (China) Co., Ltd.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */

package com.mongo.morphia.complex.core_dal;

import java.util.List;

/**
 * 
 * @since 2010-6-1
 * @author pl
 */
public interface UpdatesOperation {

    /**
     * Sets field to the value supplied.
     */
    Updates set(Object value);

    /**
     * Deletes the given field from an object.
     */
    Updates unset();

    /**
     * Increments field by the number value if field is present in the object, otherwise sets field
     * to the number value.
     */
    Updates inc(int value);

    /**
     * Appends value to field, if field is an existing array, otherwise sets field to the array
     * [value] if field is not present. If field is present but is not an array, an error condition
     * is raised.
     */
    Updates push(Object value);

    /**
     * Appends each value in values to field, if field is an existing array, otherwise sets field to
     * the array values if field is not present. If field is present but is not an array, an error
     * condition is raised.
     */
    Updates pushAll(List<?> values);

    /**
     * Adds value to the array only if its not in the array already.
     */
    Updates addToSet(Object value);

    /**
     * Adds values to the array only if they're not in the array already.
     */
    Updates addToSet(List<?> values);

    /**
     * Removes the first element in an array.
     */
    Updates popFirst();

    /**
     * Removes the last element in an array.
     */
    Updates popLast();

    /**
     * Removes all occurrences of value from field, if field is an array. If field is present but is
     * not an array, an error condition is raised.
     */
    Updates pull(Object value);

    /**
     * Removes all occurrences of each value in value_array from field, if field is an array. If
     * field is present but is not an array, an error condition is raised.
     */
    Updates pullAll(List<?> values);
}
