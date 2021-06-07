package linked;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * leet-code(19) 删除链表的倒数第 N 个结点
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 * 进阶：你能尝试使用一趟扫描实现吗？
 *
 * <p>
 * 双指针解答即可
 * </p>
 *
 * @author Ringo
 * @date 2021/6/7 17:54
 */
public class removeNthFromEnd {

    @NoArgsConstructor
    @AllArgsConstructor
    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static ListNode solution(ListNode head, int n) {
        if (head == null || n < 1) return head;

        ListNode lo = head, hi = head;                     // 左右两个指针

        for (int i = 1; i <= n; ++i) {
            hi = hi.next;
            if (hi == null)                                // 超出范围, 证明要删的是首元结点
                return head.next;
        }

        while (hi.next != null) {                          // 两个指针同时向右走
            lo = lo.next;
            hi = hi.next;
        }

        lo.next = lo.next.next;                            // 删除目标结点

        return head;
    }

    public static void main(String[] args) {
//        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        ListNode head = new ListNode(1, new ListNode(2));
        ListNode newHead = solution(head, 1);
        for (ListNode p = newHead; p != null; p = p.next)
            System.out.print(p.val + "\t");
    }

}
