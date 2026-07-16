/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
};
*/

class Solution {

    public Node flatten(Node head) {
        if (head == null)
            return null;

        dfs(head);
        return head;
    }

    // Returns the tail of the flattened list
    private Node dfs(Node head) {

        Node curr = head;
        Node last = null;

        while (curr != null) {

            Node next = curr.next;

            if (curr.child != null) {

                Node childHead = curr.child;
                Node childTail = dfs(childHead);

                // Connect current node with child
                curr.next = childHead;
                childHead.prev = curr;
                curr.child = null;

                // Connect child tail with original next
                if (next != null) {
                    childTail.next = next;
                    next.prev = childTail;
                }

                last = childTail;
            } else {
                last = curr;
            }

            curr = next;
        }

        return last;
    }
}