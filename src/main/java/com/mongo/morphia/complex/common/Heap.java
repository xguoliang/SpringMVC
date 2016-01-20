package com.mongo.morphia.complex.common;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 堆管理, 用于按优先级排序, 实现快速插入与移除
 * 
 * @since 2010-3-16
 * @author xichu_yu
 */
public class Heap<K, V> implements Serializable {
    private static final long serialVersionUID = -8716794514931758256L;

    private static class HeapNode<K, V> implements Serializable {
        private static final long serialVersionUID = 991109882217680055L;

        private HeapNode(K key, V obj, long priority) {
            this.obj = obj;
            this.key = key;
            this.priority = priority;
        }

        @Override
        public String toString() {
            return obj.toString() + " <= " + key;
        }

        V obj;
        K key;
        long priority;
        int index;
    }

    public Heap(int initCapacity) {
        list = new ArrayList<HeapNode<K, V>>(initCapacity);
        map = new HashMap<K, HeapNode<K, V>>(initCapacity);
    }

    public Heap() {
        this(50);
    }

    /**
     * 元素个数
     * 
     * @return
     */
    public int size() {
        return list.size();
    }

    /**
     * 是否存在指定KEY的元素
     * 
     * @param key
     * @return
     */
    public boolean containsKey(K key) {
        return map.containsKey(key);
    }

    /**
     * 从堆中移走指定KEY的元素
     * 
     * @param key
     * @return
     */
    public V remove(K key) {
        HeapNode<K, V> node = map.get(key);
        if (node == null)
            return null;
        int i = node.index;
        HeapNode<K, V> tail = tail();
        if (i < list.size() && list.size() > 0) {
            set(i, tail);
            if (!shiftR(i))
                shiftL(i);
        }
        map.remove(node.key);
        return node.obj;
    }

    /**
     * 向堆中压入一个元素
     * 
     * @param key
     * @param obj
     * @param priority
     * @return
     */
    public boolean push(K key, V obj, long priority) {
        if (map.containsKey(key)) {
            return false;
        } else {
            HeapNode<K, V> node = new HeapNode<K, V>(key, obj, priority);
            int i = add(node);
            shiftL(i);
            return true;
        }
    }

    /**
     * 获取堆顶元素
     * 
     * @return
     */
    public V top() {
        if (list.size() == 0)
            return null;

        return (list.get(0)).obj;
    }

    public V get(K key) {
        HeapNode<K, V> node = map.get(key);
        if (node == null)
            return null;
        else
            return node.obj;
    }

    /**
     * 弹出堆顶元素
     * @return
     */
    public V pop() {
        if (list.size() == 0)
            return null;

        HeapNode<K, V> head = list.get(0);
        HeapNode<K, V> tail = tail();
        if (list.size() > 0) {
            set(0, tail);
            shiftR(0);
        }
        map.remove(head.key);
        return head.obj;
    }

    public V[] toArray(V[] target) {
        return list.toArray(target);
    }

    private boolean shiftL(int i) {
        HeapNode<K, V> current = list.get(i);
        int index = i;
        do {
            if (index <= 0)
                break;
            int j = index - 1 >> 1;
            HeapNode<K, V> node = list.get(j);
            if (current.priority > node.priority)
                break;
            set(index, node);
            index = j;
        } while (true);
        set(index, current);
        return index != i;
    }

    private boolean shiftR(int i) {
        HeapNode<K, V> current = list.get(i);
        int index = i;
        int j = (index << 1) + 1;
        int size = list.size();
        do {
            if (j >= size)
                break;
            HeapNode<K, V> node = list.get(j);
            if (j + 1 < size) {
                HeapNode<K, V> node2 = list.get(j + 1);
                if (node2.priority < node.priority) {
                    j++;
                    node = node2;
                }
            }
            if (current.priority < node.priority)
                break;
            set(index, node);
            index = j;
            j = (index << 1) + 1;
        } while (true);
        set(index, current);
        return index != i;
    }

    private HeapNode<K, V> tail() {
        int i = list.size() - 1;
        return list.remove(i);
    }

    private void set(int i, HeapNode<K, V> node) {
        list.set(i, node);
        node.index = i;
    }

    private int add(HeapNode<K, V> node) {
        int i = list.size();
        list.add(node);
        node.index = i;
        map.put(node.key, node);
        return i;
    }

    private ArrayList<HeapNode<K, V>> list;
    private HashMap<K, HeapNode<K, V>> map;

    public void clear() {
        this.list.clear();
        this.map.clear();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return this.list.toString();
    }
}
