public class DeleteNodeFromLLWithoutHead {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }


    // TC - O(1)
    // SC - O(1)
    public static void deleteNodeWithoutHead(ListNode node) {
        if(node == null) {
            return;
        }

        if(node.next == null) {
            node = null;
            return;
        }

        // we are copying the next element
        // and deleting the next element
        node.val = node.next.val;
        node.next = node.next.next;
    }

}
