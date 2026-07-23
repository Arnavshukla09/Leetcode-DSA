class Solution {
    public ListNode swapNodes(ListNode head, int k) {
        int counter = 0;
        ListNode cur = head;
        ListNode fir = head;
        ListNode sec = head;

        // Count nodes
        while (cur != null) {
            counter++;
            cur = cur.next;
        }

        // Move fir to kth node from beginning
        for (int i = 1; i < k; i++) {
            fir = fir.next;
        }

        // Move sec to kth node from end
        for (int i = 1; i <= counter - k; i++) {
            sec = sec.next;
        }

        // Swap values
        int temp = fir.val;
        fir.val = sec.val;
        sec.val = temp;

        return head;
    }
}