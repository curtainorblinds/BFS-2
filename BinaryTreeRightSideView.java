import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Leetcode 199. Binary Tree Right Side View
 * Link: https://leetcode.com/problems/binary-tree-right-side-view/description/
 */
//------------------------------------ Solution 1 -----------------------------------
public class BinaryTreeRightSideView {
    /**
     * BFS solution - process level by level and at each level add the last node value
     * to the result
     *
     * TC: O(n) SC: O(n) worst case for a complete binary tree total leaf nodes are n/2
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()) {
            int size = queue.size();
            TreeNode curr = null;
            for (int i = 0; i < size; i++) {
                curr = queue.poll();
                if (curr.left != null) {
                    queue.add(curr.left);
                }
                if (curr.right != null) {
                    queue.add(curr.right);
                }
            }
            result.add(curr.val);
        }
        return result;
    }
}

//------------------------------------ Solution 2 -----------------------------------
class BinaryTreeRightSideView2 {
    /**
     * DFS solution - visit tree in traditional order left then right and pass current level
     * as dfs recursion parameter. For each level add/set the value of the node at its level
     * index inside the result. This will naturally take care of printing right most node val
     * in result as we are overriding the values.
     *
     * TC: O(n) Auxiliary SC: O(1)
     * Recursive stack SC: O(h) h -> height of tree, worst case O(n), complete BST O(log n)
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        dfs(root, 0, result);
        return result;
    }

    private void dfs(TreeNode root, int level, List<Integer> result) {
        //base
        if (root == null) {
            return;
        }

        //logic
        if (level == result.size()) {
            result.add(root.val);
        } else {
            result.set(level, root.val);
        }
        dfs(root.left, level + 1, result);
        dfs(root.right, level + 1, result);
    }
}

//------------------------------------ Solution 3 -----------------------------------
class BinaryTreeRightSideView3 {
    /**
     * DFS solution - but process right child first, and in preorder level in recursion logic check if
     * result already has val for the current level. size() is used for this. This eliminates the need
     * to override result and first encounter at a level will be the right most for that level
     *
     * TC: O(n) Auxiliary SC: O(1)
     * Recursive stack SC: O(h) h -> height of tree, worst case O(n), complete BST O(log n)
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        dfs(root, 0, result);
        return result;
    }

    private void dfs(TreeNode root, int level, List<Integer> result) {
        //base
        if (root == null) {
            return;
        }

        //logic
        if (level == result.size()) {
            result.add(root.val);
        }
        dfs(root.right, level + 1, result);
        dfs(root.left, level + 1, result);
    }
}