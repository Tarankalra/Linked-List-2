// TC - O(m+n)
// SC - O(1)

// Method 2- using hashset
// traverse through LL1, put its elements in hashset
// traverse through LL2, and check if the element is present in the set, then return that node if found

public class IntersectionOfTwoLists {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        if(headA == null || headB == null) {
            return null;
        }

        int lenA = 1;
        int lenB = 1;
        ListNode temp1 = headA;
        ListNode temp2 = headB;

        // find the length of A and B lists
        while(temp1!=null) {
            temp1 = temp1.next;
            lenA++;
        }
        while(temp2!=null) {
            temp2 = temp2.next;
            lenB++;
        }
        int diff = lenA-lenB; // find diff between their lengths
        if(diff < 0) {
            // if B is greater, temp1 is assigned to headB otherwise headA
            temp1 = headB;
            temp2 = headA;
        } else {
            temp1 = headA;
            temp2 = headB;
        }

        int count = Math.abs(diff);
        int i =0;

        // moving the temp1 pointer count steps further, so that their ditance left to be travelled remain same
        while(i<count && temp1!=null) {
            temp1 = temp1.next;
            i++;
        }

        // traversing both the lists till end, if any node is same - we found the intersection
        while(temp1!=null && temp2!=null) {
            if(temp1 == temp2) {
                break;
            }

            temp1 = temp1.next;
            temp2 = temp2.next;
        }
        // will return the intersection node or null(that means node not found)
        return temp1;
    }
}
