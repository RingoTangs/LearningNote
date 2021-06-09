package linked;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * leet-code(23) 合并K个升序链表
 * 给你一个链表数组，每个链表都已经按升序排列。
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 *
 * @author Ringo
 * @date 2021/6/9 14:47
 */
public class MergeKSortedLists {

    @NoArgsConstructor
    @AllArgsConstructor
    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode dummyHead = new ListNode(8848, null);
        ListNode cur = dummyHead;

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
        return dummyHead.next;
    }

    public static ListNode mergeKSortedLists(ListNode[] lists) {
        ListNode ans = null;            // 用来保存上一次的结果
        int len = lists.length;
        for (int i = 0; i < len; ++i) {
            ans = mergeTwoLists(ans, lists[i]);
        }
        return ans;
    }

}
