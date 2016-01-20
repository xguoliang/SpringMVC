/**
 *
 * 
 * 修订历史记录：
 * 
 * Revision	1.0	 2010-11-3  guolei_mao  创建。
 * 
 */

package com.mongo.morphia.complex.core_dal;

import java.util.List;


import com.mongodb.WriteConcern;

/**
 * 类说明:
 * 
 * @author <a href="mailto:guolei_mao@kingdee.com">guolei_mao</a>
 * @version 1.0, 2010-11-3
 */
public interface BaseDAO<T extends com.mongo.morphia.complex.core.BObject> {
    long countAll(int limit);

    void delete(String id);

    boolean exist(String id);

    List<T> find(List<String> ids);

    T find(String id);

    Results<T> findAll();

    List<T> findAll(int start, int limit);
    
    List<T> findAll(String order, int start, int limit);

    void inc(String id, String field, int i);

    /**
     * @param id
     * @param field
     * @param f
     */
    void incFloat(String id, String field, float f);

    void save(T t);
    
    void save(T t, WriteConcern writerConcern);

    byte[] encode(T entity);

    T decode(byte[] data);
}
