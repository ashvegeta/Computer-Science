class Node {
    int val;
    Node next;
    
    Node(int elem) {
        this.val = elem;
        this.next = null;
    }
}

class LinkedList {
    Node head;

    LinkedList() {}

    void add(int elem) {

        if(head==null) {
            head = new Node(elem);
            return;
        }

        Node temp = head;
        while(temp!=null && temp.next!=null) temp=temp.next;

        temp.next = new Node(elem);

        return;
    }

    int pop() {
        if(head==null) {
            System.out.println("List Empty: No element to pop");
            System.exit(-1);
            return -1;
        }

        Node temp = head;
        int elem;

        while(temp!=null && temp.next!=null && temp.next.next!=null) temp=temp.next;
        
        if(temp==head) {
            elem = temp.val;
            head = null;
        } else {
            elem = temp.next.val;
            temp.next = null;
        }

        return elem;
    }

    void insert(int elem,int pos) {
        if(head==null) {
            head = new Node(elem);
            return;
        }

        int idx = 0;
        Node temp = head;

        while(temp.next!=null && idx<pos) {
            temp=temp.next;
            idx+=1;
        }

        if(idx==pos) {
            Node tempNode = temp.next;
            temp.next = new Node(elem);
            temp.next.next = tempNode;
        } else {
            System.out.println("Index Out of Bounds: " + pos);
        }

        return;
    }

    int delete(int pos) {
        if(head==null) {
            System.out.println("List Empty: No element to delete");
            System.exit(-1);
            return -1;
        }

        Node temp = head;
        int elem, idx=0;

        while(temp!=null && temp.next!=null && temp.next.next!=null && idx<pos-1) {
            temp=temp.next;
            idx+=1;
        }
        
        if(idx<pos-1) {
            System.out.println("Index ouf of Bounds: " + pos);
            System.exit(-1);
            return -1;
        }

        if(temp==head && temp.next==null) {
            elem = temp.val;
            head = null;
        } else {
            elem = temp.next.val;
            temp.next = temp.next.next;
        }

        return elem;
    }

    void printList() {
        Node temp = head;

        System.out.println("");
        
        while(temp.next!=null) {
            System.out.print(temp.val + "->");
            temp = temp.next;
        }
        
        System.out.print(temp.val + "\n");
        
    }
}

public class LinkedLists {
    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.insert(5,3);
        list.insert(6,4);
        list.insert(7,5);
        list.insert(8,6);
        list.pop();
        list.pop();
        list.delete(4);
        list.printList();
    }
}