import java.util.LinkedList;
import java.util.Queue;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    int height;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
        this.height = 1;
        this.left = this.right = null;
    }
}

class AVLTree {
    TreeNode root;
    int count;

    AVLTree() {
    }

    int size() {
        return count;
    }

    int height(TreeNode node) {
        return node != null ? node.height : 0;
    }

    int getBalance(TreeNode node) {
        return node != null ? height(node.left) - height(node.right) : 0;
    }

    TreeNode smallestNode(TreeNode node) {
        TreeNode temp = node;

        while (temp.left != null)
            temp = temp.left;

        return temp;
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

    TreeNode leftRotate(TreeNode node) {
        TreeNode rTree = node.right;
        TreeNode rTreeChild = rTree.left;

        rTree.left = node;
        node.right = rTreeChild;

        node.height = Math.max(height(node.left), height(node.right));
        rTree.height = Math.max(height(rTree.left), height(rTree.right));

        return rTree;
    }

    TreeNode rightRotate(TreeNode node) {
        TreeNode lTree = node.left;
        TreeNode lTreeChild = lTree.right;

        lTree.right = node;
        node.left = lTreeChild;

        node.height = Math.max(height(node.left), height(node.right));
        lTree.height = Math.max(height(lTree.left), height(lTree.right));

        return lTree;
    }

    TreeNode insert(TreeNode root, int elem) {
        if (root == null)
            return new TreeNode(elem);

        if (elem < root.val)
            root.left = insert(root.left, elem);
        else if (elem > root.val)
            root.right = insert(root.right, elem);
        else {
            System.out.println("Element aready exists");
            return root;
        }

        root.height = 1 + Math.max(height(root.left), height(root.right));

        int balance = getBalance(root);

        if (balance > 1) {
            if (elem < root.left.val)
                return rightRotate(root);
            else {
                root.left = leftRotate(root.left);
                return rightRotate(root);
            }
        }

        else if (balance < -1) {
            if (elem > root.right.val)
                return leftRotate(root);
            else {
                root.right = rightRotate(root.right);
                return leftRotate(root);
            }
        }

        return root;
    }

    TreeNode delete(TreeNode root, int elem) {
        if (root == null)
            return root;

        if (elem < root.val)
            root.left = delete(root.left, elem);
        else if (elem > root.val)
            root.right = delete(root.right, elem);
        else {
            if (root.left == null || root.right == null) {
                TreeNode temp = root.left != null ? root.left : root.right;

                if (temp == null) {
                    temp = root;
                    root = null;
                } else
                    root = temp;

            } else {
                TreeNode succNode = smallestNode(root.right);
                root.val = succNode.val;
                root.right = delete(root.right, succNode.val);
            }
        }

        if (root == null)
            return root;

        root.height = 1 + Math.max(height(root.left), height(root.right));

        int balance = getBalance(root);

        if (balance > 1) {
            if (getBalance(root.left) >= 0)
                return rightRotate(root);
            else {
                root.left = leftRotate(root.left);
                return rightRotate(root);
            }
        }

        else if (balance < -1) {
            if (getBalance(root.right) <= 0)
                return leftRotate(root);
            else {
                root.right = rightRotate(root.right);
                return leftRotate(root);
            }
        }

        return root;
    }
}

public class AVLTrees {
    public static void main(String[] args) {
        AVLTree bt = new AVLTree();

        bt.root = bt.insert(bt.root, 2);
        bt.root = bt.insert(bt.root, 3);
        bt.root = bt.insert(bt.root, 4);
        bt.root = bt.insert(bt.root, 5);
        bt.root = bt.insert(bt.root, 6);
        bt.root = bt.insert(bt.root, 7);
        bt.root = bt.insert(bt.root, 8);

        bt.root = bt.delete(bt.root, 5);
        bt.root = bt.delete(bt.root, 7);

        System.out.print("\n\nIn order: ");
        bt.inOrder(bt.root);

    }
}