import java.util.LinkedList;
import java.util.Queue;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}

class binaryTree {
    TreeNode root;
    int count = 0;
    int lvl = 0;
    TreeNode deepestNodeParent = null;

    binaryTree() {
    }

    binaryTree(int elem) {
        this.root = new TreeNode(elem);
        this.count += 1;
    }

    int size() {
        return count;
    }

    boolean isLeafNode(TreeNode node) {
        return node.left == null && node.right == null ? true : false;
    }

    void helper(TreeNode root, int level) {
        if (root == null)
            return;

        if (level >= lvl && (root.left != null || root.right != null)) {
            deepestNodeParent = root;
            lvl = level;
        }

        helper(root.left, level + 1);
        helper(root.right, level + 1);
    }

    TreeNode getParentOfDeepestNode() {
        lvl = 0;
        deepestNodeParent = null;

        helper(root, lvl);

        return deepestNodeParent;
    }

    TreeNode getParent(TreeNode root, TreeNode node) {
        if (node == null || root == null)
            return null;
        if (root.left == node || root.right == node)
            return root;

        TreeNode parent = getParent(root.left, node);

        return parent != null ? parent : getParent(root.right, node);
    }

    TreeNode levelOrder(boolean print, TreeNode... find) {
        if (this.root == null)
            return null;

        TreeNode temp = null;
        Queue<TreeNode> q = new LinkedList<>();

        q.add(root);

        while (q.peek() != null) {
            temp = q.poll();

            if (print)
                System.out.print(temp.val + " ");

            if (find.length > 0 && find[0] == root)
                return root;

            if (temp.left != null)
                q.add(temp.left);

            if (temp.right != null) {
                q.add(temp.right);
                temp = temp.left;
            }

        }

        return temp;
    }

    TreeNode inOrder(TreeNode root, TreeNode... find) {
        if (root == null)
            return null;

        TreeNode l = inOrder(root.left, find);
        if (find.length > 0) {
            if (find[0] == root)
                return root;
        } else
            System.out.print(root.val + " ");
        TreeNode r = inOrder(root.right, find);

        return l != null ? l : r;
    }

    TreeNode preOrder(TreeNode root, TreeNode... find) {
        if (root == null)
            return null;

        if (find.length > 0 && find[0] == root)
            return root;
        else
            System.out.print(root.val + " ");
        preOrder(root.left, find);
        preOrder(root.right, find);

        return null;
    }

    TreeNode postOrder(TreeNode root, TreeNode... find) {
        if (root == null)
            return null;

        postOrder(root.left, find);
        postOrder(root.right, find);
        if (find.length > 0 && find[0] == root)
            return root;
        else
            System.out.print(root.val + " ");

        return null;
    }

    TreeNode traverse(int type, boolean print, TreeNode... find) {
        if (find.length > 0 && find[0] == null) {
            System.out.println("Cant Traverse Null Node");
            return null;
        }

        TreeNode node = null;

        if (type == 0)
            node = inOrder(this.root, find);
        else if (type == 1)
            node = preOrder(this.root, find);
        else if (type == 2)
            node = postOrder(this.root, find);
        else if (type == 3)
            node = levelOrder(print, find);

        if (print)
            System.out.println("");

        return node;
    }

    void insert(int elem) {
        this.count += 1;

        if (this.root == null) {
            this.root = new TreeNode(elem);
            return;
        }

        Queue<TreeNode> q = new LinkedList<>();
        TreeNode node;
        q.add(root);

        while (q.peek() != null) {
            node = q.poll();

            if (node.left == null) {
                node.left = new TreeNode(elem);
                break;
            } else
                q.add(node.left);

            if (node.right == null) {
                node.right = new TreeNode(elem);
                break;
            } else
                q.add(node.right);
        }

        return;
    }

    int delete(TreeNode node) {
        TreeNode parent = null;
        int val = 0, temp = 0;

        if (root == null || node == null || (node != root && (parent = getParent(root, node)) == null)) {
            System.out.println("\nDeletionError: node doesn't exist or cant delete null node");
            System.exit(-1);
            return -1;
        }

        TreeNode deepestParent = getParentOfDeepestNode();

        if (deepestParent == null) {
            val = root.val;
            root = null;
        } else {
            if (deepestParent.right != null) {
                temp = deepestParent.right.val;
                deepestParent.right = null;
            } else {
                temp = deepestParent.left.val;
                deepestParent.left = null;
            }

            if (parent == null) {
                val = root.val;
                root.val = temp;
                return val;
            }

            if (deepestParent == parent)
                return temp;

            if (parent.left == node) {
                val = parent.left.val;
                parent.left.val = temp;
            } else {
                val = parent.right.val;
                parent.right.val = temp;
            }
        }

        this.count -= 1;

        return val;
    }

    void search(int traverseType, TreeNode node) {
        TreeNode search = traverse(traverseType, false, new TreeNode[] { node });

        if (search != null)
            System.out.println("Element " + search.val + " Found");
        else
            System.out.println("Element" + node.val + " Not Found");
    }

    int height(TreeNode root) {
        if (root == null)
            return -1;

        int l = height(root.left);
        int r = height(root.right);

        return Math.max(l, r) + 1;
    }

}

public class BinaryTrees {
    public static void main(String[] args) {
        binaryTree bt = new binaryTree(1);

        bt.insert(2);
        bt.insert(3);
        bt.insert(4);
        bt.insert(5);
        bt.insert(6);
        bt.insert(7);
        bt.insert(8);

        System.out.print("\nSize of the Tree: " + bt.size() + "\n");

        System.out.print("\nIn order: ");
        bt.traverse(0, true);

        System.out.print("\nPre order: ");
        bt.traverse(1, true);

        System.out.print("\nPost order: ");
        bt.traverse(2, true);

        System.out.print("\nLevel order: ");
        bt.traverse(3, true);

        System.out.print("\nSearch: ");
        bt.search(0, bt.root.left.right);

        System.out.println("\nHeight: " + bt.height(bt.root));

        System.out.println("\nParent of Node 4: " + bt.getParent(bt.root, bt.root.left.left).val);
        System.out.println("\nParent of Node 5: " + bt.getParent(bt.root, bt.root.left.right).val);

        System.out.println("\nDeleting Node: " + bt.delete(bt.root.left.right));

        System.out.print("\nLevel order after deletion: ");
        bt.traverse(3, true);

    }

}