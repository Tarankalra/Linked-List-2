import java.util.Stack;

public class ReorderList {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    // TC - O(n), 1 pass
    // SC - O(n) - recursive stack
    public void reorderList(ListNode head) {
        ListNode [] left =  new ListNode[1];
        // it will create in heap memory space, as is passed by value and a reference will be given to it,
        // because we want each recursive call to have its updated value and not local value in the recursion call.
        // option2 - we can also take global variable as well
        left[0]  =  head;
        reorder(left,head);

    }

    // left pointer will be created in heap and right pointer will be created in stack
    public void reorder(ListNode[] left, ListNode right){
        if(right==null){
            return ;
        }
        reorder(left,right.next);

        // in post area of recursion right pointer coming back(because of function remove from recursion stack)
        // and we move left pointer forward
        if(left[0].next!=null){
            ListNode temp =  left[0].next;
            left[0].next =  right;
            right.next =  temp;
            left[0] =  temp;
        }

        // as we need to  merge till left pointer behind the right pointer
        // when we reach the mid, the left pointer will be equal to the right pointer,
        // so we need to end the list
        if(left[0].next == right){
            left[0].next =  null;
        }
    }

    // TC - O(n), 1.5 pass
    // SC - O(n) - stack used, extra space
    public void reorderList2(ListNode head) {
        if(head == null || head.next == null) {
            return;
        }

        Stack <ListNode> stack = new Stack<>();
        int count = 0;
        ListNode temp = head;
        // Pushing each element into the stack, and finding the length of LL
        while(temp!= null) {
            count++;
            stack.push(temp);
            temp = temp.next;
        }

        int midIndex = count/2;

        int i=0;
        temp = head;
        ListNode popped;
        ListNode temp2;
        // we will iterate till the mid index
        // we connect the element from stack to the element in LL from beginning
        while(i < midIndex) {
            popped = stack.pop();
            temp2 = temp.next;
            temp.next = popped;
            temp = temp2;
            popped.next = temp;
            i++;
        }

        temp.next = null;
    }

    // TC -O(n)
    // SC - O(1)
    public void reorderList3(ListNode head) {
        if(head == null) {
            return;
        }
        // breaking the LL from mid
        ListNode midNode = findMidNode(head);
        ListNode midNodeNext = midNode.next;

        midNode.next = null;

        // then reversing the second one
        ListNode reversedNode = reverse(midNodeNext);

        ListNode temp1 = head;
        ListNode temp2 = temp1.next;

        // traversing through both the list and making connnections
        while(reversedNode!=null) {
            temp1.next = reversedNode;
            temp1 = reversedNode;
            reversedNode = temp2;
            temp2 = temp1.next;
        }
    }

    // reversing the linked list using three pointers
    // TC - O(n)
    private ListNode reverse(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }

        ListNode prev = null;
        ListNode curr = head;
        ListNode next = curr.next;

        while(curr!=null) {
            curr.next = prev;
            prev = curr;
            curr = next;
            if(next!=null) {
                next = next.next;
            }
        }
        return prev;
    }

    // finding mid node, TC - O(n/2) --> O(n), using two pointers
    private ListNode findMidNode(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }

        ListNode slow = head;
        ListNode fast = head;

        // incase of even count list, we are returning the first node
        // hence the condition is made like this
        while(fast.next!=null && fast.next.next!=null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
