package com.sdzyc.arithmetic.interview;

/**
 *  判断链表是否有环
 *  2 -> 3 -> 5 -> 6 -> 1 -> 7 -> 3..
 * LinkedLoop class
 *
 * @author yanchuan
 * @module com.sdzyc.arithmetic.interview
 * @blame yanchuan
 * @since 20/04/16 13:06
 */
public class LinkedLoop {


    public static boolean isLoop(Node head) {
        Node p1 = head;
        Node p2 = head;
        while (null != p2 && null != p2.next) {
            p1 = p1.next;
            p2 = p2.next.next;
            if(p1 == p2) {
                return true;
            }
        }
        return false;
    }


    public static int loopLen(Node head) {
        Node p1 = head;
        Node p2 = head;
        boolean iscount = false;
        int count = 0;
        while (null != p2 && null != p2.next) {
            p1 = p1.next;
            p2 = p2.next.next;

            if(p1 == p2) {
                if(!iscount){
                    iscount = true;
                }else {
                    return count;
                }

            }
            if(iscount) {
                count++;
            }

        }
        return count;
    }

    public static Node getLoopNode(Node head) {
        Node p1 = head;
        Node p2 = head;
        boolean isMeet = false;
        while (null != p2 && null != p2.next) {
            p1 = p1.next;
            if(isMeet) {
                p2 = p2.next;
            }else {
                p2 = p2.next.next;
            }

            if(p1 == p2) {
                if(isMeet) {
                    return p1;
                }else {
                    isMeet = true;
                }
                p1 = head;

            }
        }
        return null;
    }

    /**节点*/
    private static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "[node:{\"data\":"+data+"}]";
        }
    }

    /**
     *  5 -> 8 -> 7 -> 2 -> 6 -> 3 -> 2....
     * @param args
     */
    public static void main(String[] args) {
        Node n1 = new Node(5);
        Node n2 = new Node(8);
        Node n3 = new Node(7);
        Node n4 = new Node(2);
        Node n5 = new Node(6);
        Node n6 = new Node(3);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n4;

        boolean isloop = isLoop(n1);
        System.out.println("链表是否有环：" + isloop);
        int len = loopLen(n1);
        System.out.println("链表环的长度：" + len);
        Node node = getLoopNode(n1);
        System.out.println("链表入环节点：" + node);
    }
}
