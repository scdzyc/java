package com.sdzyc.arithmetic.interview;

import java.util.HashMap;
import java.util.Map;

/** LRU 算法 本实现非线程安全
 * LRUArithmetic class
 *
 * @author yanchuan
 * @module com.sdzyc.arithmetic.interview
 * @blame yanchuan
 * @since 20/04/26 16:11
 */
public class LRUArithmetic {

    /**
     * 头节点
     */
    private Node head;
    /**
     * 尾节点
     */
    private Node end;
    /**
     * 缓存上限
     */
    private int limit;

    private Map<String, Node> hashMap;

    public LRUArithmetic(int limit) {
        this.limit = limit;
        this.hashMap = new HashMap<>();
    }

    /**
     * 获取值
     * @param key
     * @return
     */
    public String get(String key) {
        Node node = hashMap.get(key);
        if(null == node) {
            return null;
        }
        refreshNode(node);
        return node.value;
    }

    /**
     * 设置k-v
     * @param key
     * @param value
     */
    public void put(String key, String value) {
        Node node = hashMap.get(key);
        if(null != node) {
            refreshNode(node);
            return;
        }
        // 如果key不存在，则插入key-value
        // 如果已经达到限制先移除久不用的
        if(hashMap.size() >= limit) {
            String oldKey = removeNode(head);
            hashMap.remove(oldKey);
        }
        node = new Node(key, value);
        addNode(node);
        hashMap.put(key, node);
    }

    /**
     * 移除元素
     * @param key
     */
    public void remove(String key) {
        Node node = hashMap.get(key);
        removeNode(node);
        hashMap.remove(key);
    }

    /**
     *  刷新被访问的节点位置
     * @param node
     */
    private void refreshNode(Node node) {
        // 如果访问的是尾节点，则无需移动元素
        if(end == node) {
            return;
        }
        // 移除节点
        removeNode(node);
        // 重新插入节点
        addNode(node);
    }

    /**
     * 删除节点
     * @param node
     * @return
     */
    private String removeNode(Node node) {
        if(node == head && node == end) {
            // 移除唯一节点
            head = null;
            end = null;
        }else if(node == end) {
            // 移除尾节点
            end = end.pre;
            end.next = null;
        }else if(node == head) {
            // 移除头节点
            head = head.next;
            head.pre = null;
        }else {
            // 移除中间节点
            node.pre.next = node.next;
            node.next.pre = node.pre;
        }
        return node.key;
    }

    /**
     * 尾部插入节点
     * @param node
     */
    private void addNode(Node node) {
        if(null != end) {
            end.next = node;
            node.pre = end;
            node.next = null;
        }
        end = node;
        if(head == null) {
            head = node;
        }
    }

    class Node {
        public Node pre;
        public Node next;
        public String key;
        public String value;

        public Node(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        LRUArithmetic cache = new LRUArithmetic(5);
        for (int i = 1; i <= 5; i++) {
            cache.put("00"+ i, "用户00"+i+"信息");
        }
        cache.get("002");
        cache.put("004", "用户004信息更新");
        cache.put("006", "用户006信息");
        System.out.println(cache.get("001"));
        System.out.println(cache.get("006"));

    }
}
