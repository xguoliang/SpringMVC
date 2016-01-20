package com.mongo.morphia.complex.kingdee.core.server;



import java.util.Map;
import java.util.Set;

/**
 * 表述一个cBOS数据。可能是一个完整生命的对象，或一个嵌入式cBOS的结构。
 * 包含固定部分及扩展部分，均支持统一的Map访问风格。
 * 
 * @param <T> 固定部分对应的POJO Class。
 * 
 * @since 2010-5-20
 * @author pl
 */
public interface DynaBean {

    /**
     * @return 扩展部分
     */    
    Map<String, Object> extended();
    
    /**
     * Returns a map representing this DynaBean.
     * 
     * @return the map
     */
    public Map<String, Object> toMap();

    /**
     * Sets a name/value pair in this object.
     * 
     * @param key
     *            Name to set
     * @param v
     *            Corresponding value
     * @return <tt>v</tt>
     */
    public Object put(String key, Object v);

    public void putAll(DynaBean o);

    public void putAll(Map<String, Object> m);

    /**
     * Gets a field from this object by a given name.
     * 
     * @param key
     *            The name of the field fetch
     * @return The field, if found
     */
    public Object get(String key);

    /**
     * Remove a field with a given name from this object.
     * 
     * @param key
     *            The name of the field to remove
     * @return The value removed from this object
     */
    public Object removeField(String key);

    /**
     * Checks if this object contains a field with the given name.
     * 
     * @param s
     *            Field name for which to check
     * @return if this object contains a field with the given name
     */
    public boolean containsField(String s);

    /**
     * Returns this object's fields' names
     * 
     * @return The names of the fields in this object
     */
    public Set<String> keySet();
}