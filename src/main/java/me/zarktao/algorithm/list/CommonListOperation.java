package me.zarktao.algorithm.list;

/**
 * Created by Tao on 2017/3/15.
 */
public class CommonListOperation {

    public static class LinkedList<T> {
        Node<T> first;

        /**
         * 单向链表翻转
         * */
        LinkedList reverse() {
            LinkedList<T> result = new LinkedList<T>();

            Node head = first;
            Node point1 = first.next;
            Node point2 = point1.next;
            first.next = null;

            while (point2 != null) {
                point1.next = head;
                head = point1;
                point1 = point2;
                point2 = point1.next;
            }

            return result;
        }
    }

    private static class Node<T> {
        T value;
        Node next;
    }
}
