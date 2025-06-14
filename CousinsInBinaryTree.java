import java.util.LinkedList;
import java.util.Queue;

/**
 * Leetcode 993. Cousins in Binary Tree
 * Link: https://leetcode.com/problems/cousins-in-binary-tree/description/
 */
//------------------------------------ Solution 1 -----------------------------------
public class CousinsInBinaryTree {
    /**
     * BFS solution - process nodes level by level and each level inspect if x and y are children
     * of current node at that level, if not add children in bfsQueue for next level. Also check
     * if at the current level if both x and y are found or only one of them found
     *
     * TC: O(n) SC: O(n)
     */
    public boolean isCousins(TreeNode root, int x, int y) {
        if (root == null) {
            return false;
        }

        Queue<TreeNode> bfsQueue = new LinkedList<>();
        bfsQueue.add(root);

        while(!bfsQueue.isEmpty()) {
            int size = bfsQueue.size();
            boolean xFound = false;
            boolean yFound = false;

            for (int i = 0 ; i < size; i++) {
                TreeNode curr = bfsQueue.poll();
                if (curr.val == x) {
                    xFound = true;
                }
                if (curr.val == y) {
                    yFound = true;
                }
                if (xFound && yFound) {
                    return true;
                }
                if (curr.left != null && curr.right != null
                        && (curr.left.val == x || curr.left.val == y)
                        && (curr.right.val == x || curr.right.val == y)) {
                    return false; //both x and y are from same parent
                }
                if (curr.left != null) {
                    bfsQueue.add(curr.left);
                }
                if (curr.right != null) {
                    bfsQueue.add(curr.right);
                }
            }
            if (xFound || yFound) {
                return false;
            }
        }
        return false;
    }
}

//------------------------------------ Solution 2 -----------------------------------
class CousinsInBinaryTree2 {
     /**
      * DFS Solution void based - recursively check if current node's children are x and y then
      * return false, otherwise use global variables to note the level at which x and y are found
      * At the end if both levels are same they are cousins
      *
      * TC: O(n) Auxiliary SC: O(1)
      * Recursive stack space: O(h) h -> height of tree, worst case O(n), complete BST O(log n)
      *
      * NOTE: the logic can be checked at either pre,post or inorder. Prelevel in theory will result
      * in less number of recursive calls if both x and y are children of same node.
      */
     int xLevel;
     int yLevel;
     boolean sameParent;
     public boolean isCousins(TreeNode root, int x, int y) {
         if (root == null) {
             return false;
         }

         this.xLevel = -1;
         this.yLevel = -1;
         dfs(root, x,  y, 0);

         if (sameParent) {
             return false;
         }
         return xLevel == yLevel;
     }

     private void dfs(TreeNode root, int x, int y, int level) {
         //base
         if (root == null || sameParent) {
             return;
         }
         if (xLevel != -1 && yLevel != -1) { // both x and y are found
             return;
         }

         //logic
         if (root.val == x) {
             xLevel = level;
         }
         if (root.val == y) {
             yLevel = level;
         }
         if (root.left != null && root.right != null
                 && (root.left.val == x || root.left.val == y)
                 && (root.right.val == x || root.right.val == y)) {
             sameParent = true;
         }

         dfs(root.left, x, y, level + 1);
         dfs(root.right, x, y, level + 1);
     }
 }
