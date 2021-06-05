package linked;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * leet-code(2) 两数相加: 给出两个非空的链表用来表示两个非负的整数。
 * 其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * <p>
 * 详情请看 https://github.com/MisterBooo/LeetCodeAnimation
 * </p>
 *
 * @author Ringo
 * @date 2021/6/2 12:41
 */
public class TwoAdd {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    private static class Node {
        private int value;
        private Node next;
    }

    public static Node addTwoNumbers(Node p1, Node p2) {
        Node dummyHead = new Node(-1, null);
        Node cur = dummyHead;
        int carried = 0;
        while (p1 != null || p2 != null) {
            int a = p1 != null ? p1.getValue() : 0;
            int b = p2 != null ? p2.getValue() : 0;
            cur.setNext(new Node((a + b + carried) % 10, null));
            carried = (a + b + carried) / 10;

            cur = cur.getNext();
            p1 = p1 != null ? p1.getNext() : null;
            p2 = p2 != null ? p2.getNext() : null;
        }

        // 注意: 做题时这里没考虑到
        if (carried > 0)
            cur.setNext(new Node(carried, null));

        Node ret = dummyHead.getNext();
        dummyHead = null;       // 将引用指向为null等待GC回收
        return ret;
    }

    public static void main(String[] args) {
        Node p1 = new Node(9, new Node(9, new Node(9, null)));
        Node p2 = new Node(9, new Node(9, null));
        Node ret = addTwoNumbers(p1, p2);
        while (ret != null) {
            System.out.print(ret.getValue() + "\t");
            ret = ret.getNext();
        }
    }

}
