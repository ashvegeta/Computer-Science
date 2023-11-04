class trieNode {
    boolean terminal;
    trieNode[] children;

    trieNode() {
        terminal = false;
        children = new trieNode[256];

        for (int i = 0; i < 256; i++)
            children[i] = null;
    }

    trieNode(int CHARS) {
        terminal = false;
        children = new trieNode[CHARS];

        for (int i = 0; i < CHARS; i++)
            children[i] = null;
    }
}

class Trie {
    trieNode root;
    int CHARS;

    Trie() {
        root = new trieNode();
        CHARS = 256;
    }

    Trie(int chars) {
        root = new trieNode(CHARS);
        CHARS = chars;
    }

    String search(String s) {
        if (root == null) {
            System.out.println("Tree is Empty!!");
            return "";
        }

        trieNode temp = root;
        int val;

        for (int i = 0; i < s.length(); i++) {
            val = (int) s.charAt(i);

            if (temp.children[val] == null) {
                System.out.println("String \"" + s + "\" does not exist!!");
                return "";
            }

            temp = temp.children[val];
        }

        if (temp.terminal == false) {
            System.out.println("String \"" + s + "\" does not exist!!");
            return "";
        }

        System.out.println("String \"" + s + "\" found!!");
        return s;
    }

    void insert(String s) {
        if (root == null) {
            root = new trieNode();
        }

        trieNode temp = root;
        int val;

        for (int i = 0; i < s.length(); i++) {
            val = (int) s.charAt(i);

            if (temp.children[val] == null)
                temp.children[val] = new trieNode();

            temp = temp.children[val];
        }

        if (temp.terminal) {
            System.out.println("The String \"" + s + "\" already Exists !!");
            return;
        }

        temp.terminal = true;
        System.out.println("The String \"" + s + "\" is Successfully Inserted !!");
    }

    void freeNodes(trieNode node) {
        if (node == null)
            return;

        for (int i = 0; i < node.children.length; i++) {
            freeNodes(node.children[i]);
            node.children = null;
        }
    }

    String delete(String s) {
        if (root == null) {
            System.out.println("Tree is Empty!!");
            return "";
        }

        trieNode temp = root, onway = null;
        int val;

        for (int i = 0; i < s.length(); i++) {
            val = (int) s.charAt(i);

            if (temp.children[val] == null) {
                System.out.println("String \"" + s + "\" does not exist!!");
                return "";
            }

            if (i != s.length() - 1 && temp.terminal)
                onway = temp;

            temp = temp.children[val];
        }

        if (!temp.terminal) {
            System.out.println("String \"" + s + "\" does not exist!!");
            return "";
        }

        temp.terminal = false;
        System.out.println("String \"" + s + "\" deleted successfully!!");

        if (temp.children == null && onway != null)
            freeNodes(onway);

        return s;
    }

}

public class Tries {
    public static void main(String[] args) {
        Trie tr = new Trie();

        tr.insert("hello world");
        tr.insert("hello");
        tr.insert("hello world, ashik");
        tr.delete("hello world");

        tr.search("hello");
        tr.search("hello world, ashik");
    }
}
