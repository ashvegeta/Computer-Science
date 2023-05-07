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

class BinarySearchTree {
    TreeNode root;
    int count = 0;
    int lvl = 0;
    TreeNode deepestNodeParent = null;

    BinarySearchTree() {
    }

    BinarySearchTree(int elem) {
        this.root = new TreeNode(elem);
        this.count += 1;
    }

    int size() {
        return count;
    }

    boolean isLeafNode(TreeNode node) {
        return node.left == null && node.right == null ? true : false;
    }

    TreeNode getParent(TreeNode root, int val) {
        if (root == null
                || ((root.left != null && root.left.val == val) || (root.right != null && root.right.val == val)))
            return root;

        TreeNode found = val < root.val ? getParent(root.left, val) : getParent(root.right, val);

        return found;
    }

    void levelOrder() {
        if (this.root == null)
            return;

        TreeNode temp = null;
        Queue<TreeNode> q = new LinkedList<>();

        q.add(root);

        while (q.peek() != null) {
            temp = q.poll();

            System.out.print(temp.val + " ");

            if (temp.left != null)
                q.add(temp.left);

            if (temp.right != null)
                q.add(temp.right);
        }

    }

    void inOrder(TreeNode root) {
        if (root == null)
            return;

        inOrder(root.left);
        System.out.print(root.val + " ");
        inOrder(root.right);
    }

    void preOrder(TreeNode root) {
        if (root == null)
            return;

        System.out.print(root.val + " ");
        preOrder(root.left);
        preOrder(root.right);
    }

    void postOrder(TreeNode root) {
        if (root == null)
            return;

        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.val + " ");
    }

    void insert(TreeNode root, int elem) {
        TreeNode node = new TreeNode(elem);
        this.count += 1;

        if (root == null) {
            root = node;
            return;
        }

        TreeNode temp = root, prev = null;

        while (temp != null) {
            prev = temp;
            temp = elem < temp.val ? temp.left : temp.right;
        }

        if (elem < prev.val)
            prev.left = node;
        else
            prev.right = node;
    }

    int delete(TreeNode root, int elem) {
        TreeNode parent;

        if (root == null || ((parent = getParent(root, elem)) == null && root.val != elem)) {
            System.out.println("\nDeletionError: node doesn't exist or cant delete null node");
            System.exit(-1);
            return -1;
        }

        TreeNode temp = null;
        int val;

        if (parent == null)
            temp = root;
        else {
            if (parent.left != null && parent.left.val == elem)
                temp = parent.left;
            else
                temp = parent.right;
        }

        val = temp.val;

        if (temp.left == null) {
            if (temp == root)
                root = root.right;

            else if (parent.left == temp)
                parent.left = parent.left.right;
            else
                parent.right = parent.right.right;
        } else {
            TreeNode predec = temp.left, prev = temp;

            while (predec.right != null) {
                prev = predec;
                predec = predec.right;
            }

            if (temp == root)
                root.val = predec.val;
            else if (parent.left == temp)
                parent.left.val = predec.val;
            else
                parent.right.val = predec.val;

            if (prev.left == predec)
                prev.left = prev.left.left;
            else
                prev.right = null;

        }

        return val;
    }

    TreeNode search(TreeNode root, int value) {
        if (root == null || root.val == value)
            return root;

        if (value < root.val)
            return search(root.left, value);

        return search(root.right, value);
    }

    int height(TreeNode root) {
        if (root == null)
            return -1;

        int l = height(root.left);
        int r = height(root.right);

        return Math.max(l, r) + 1;
    }

}

public class BST {
    public static void main(String[] args) {
        BinarySearchTree bt = new BinarySearchTree(7);

        bt.insert(bt.root, 8);
        bt.insert(bt.root, 5);
        bt.insert(bt.root, 6);
        bt.insert(bt.root, 3);
        bt.insert(bt.root, 4);
        bt.insert(bt.root, 2);
        bt.insert(bt.root, 1);

        System.out.print("\nSize of the Tree: " + bt.size());

        System.out.print("\n\nIn order: ");
        bt.inOrder(bt.root);

        System.out.print("\n\nPre order: ");
        bt.preOrder(bt.root);

        System.out.print("\n\nPost order: ");
        bt.postOrder(bt.root);

        System.out.print("\n\nLevel order: ");
        bt.levelOrder();

        System.out.print("\n\nSearching for element 4: ");
        TreeNode s = bt.search(bt.root, bt.root.left.right.val);
        System.out.println(s != null ? "Found" : "node 4 does not exist");

        System.out.println("\n\nHeight: " + bt.height(bt.root));

        System.out.println("\nParent of Node 2: " + bt.getParent(bt.root, bt.root.left.left.val).val);
        System.out.println("\nParent of Node 4: " + bt.getParent(bt.root, bt.root.right.val).val);

        System.out.println("\nDeleting Node: " + bt.delete(bt.root, 2));

        System.out.print("\nLevel order after deletion: ");
        bt.levelOrder();
    }
}
