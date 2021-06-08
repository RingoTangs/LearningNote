package linked;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * leet-code(21) 合并两个有序链表
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 *
 * @author Ringo
 * @date 2021/6/8 14:25
 */
public class MergeTwoLists {

    @NoArgsConstructor
    @AllArgsConstructor
    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static ListNode merge(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode head = new ListNode(8848, null);
        ListNode cur = head;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        cur.next = l1 != null ? l1 : l2;
        return head.next;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1, new ListNode(2, new ListNode(4, null)));
        ListNode l2 = new ListNode(1, new ListNode(3, new ListNode(4, null)));
        ListNode l3 = merge(l1, l2);
        for(ListNode p = l3; p != null; p = p.next) {
            System.out.print(p.val + "\t");
        }
    }
}