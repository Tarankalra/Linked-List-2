import java.util.Stack;

// TC - next() has amortized time complexity as O(1), because only in certain cases only we are calling the dfs method
// finding the next element takes O(1) time to pop from stack
// SC - O(h) - height of tree by recursive stack
// Constructor intialize elements O(h) which we do not consider in calculation

// We need inorder traversal with controlled recursion
public class BSTIterator {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


    TreeNode root;
    Stack <TreeNode> stack = new Stack<>();
    public BSTIterator(TreeNode root) {
        this.root = root;
        dfs(root);
    }

    // As BST iterator is an inorder traversal
    // so we will put the left side in the stack first using dfs method
    // we return the next available element in the stack
    public int next() {
        TreeNode popped = stack.pop();
        if(popped.right!=null) {
            dfs(popped.right);
        }
        return popped.val;
    }

    // TC - O(1)
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    private void dfs(TreeNode root) {
        while(root!=null) {
            stack.push(root);
            root = root.left;
        }

    }
}
