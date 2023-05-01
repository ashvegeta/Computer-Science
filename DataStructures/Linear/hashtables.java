class Node {
    String key;
    int val;
    Node next;

    Node(String key, int val) {
        this.key = key;
        this.val = val;
        this.next = null;
    }
}

class HashTable {
    Node[] hashtable;

    HashTable(int maxsize) {
        hashtable = new Node[maxsize];
    }

    // function to calculate hash code given a key
    int Hash(String key) {
        int code = 0;

        for(int i=0; i<key.length(); i++) code += (i+1) * (key.charAt(i) - '\0');

        return code%hashtable.length;
    }

    // get value of a key
    int get(String key) {
        Node temp = hashtable[Hash(key)];

        while(temp!=null) {
            if(temp.key==key) return temp.val;
            temp = temp.next;
        }

        System.out.printf("key %s does not exist: Get\n",key);
        System.exit(-1);
        return -1;
    }

    // set value of a key
    void set(String key, int value) {
        int hashkey = Hash(key);
        Node temp = hashtable[hashkey], prev = null;

        if(temp==null) {
            hashtable[hashkey] = new Node(key,value);
            return;
        }

        while(temp!=null) {
            if(temp.key==key) {
                temp.val = value;
                return;
            }
            prev = temp;
            temp = temp.next;
        }

        prev.next = new Node(key, value);
    }

    //  remove the key from hash table
    int remove(String key) {
        Node temp = hashtable[Hash(key)], prev = null;
        boolean skip = false;

        if(temp==null) skip = true;

        if(temp!=null && temp.next==null && !skip) {
            if(temp.key==key) {
                hashtable[Hash(key)] = null;
                return temp.val;  
            }
            skip = true;
        }

        while(temp!=null && !skip) {
            if(temp.key==key) {
                int elem = temp.val;
                if(prev!=null) prev.next = prev.next.next;
                else hashtable[Hash(key)] = temp.next;
                return elem;
            }

            prev = temp;
            temp = temp.next;
        }

        System.out.printf("key %s does not exist: Remove\n",key);
        System.exit(-1);
        return -1;
    }
}

public class hashtables {
    public static void main(String[] args) {
        HashTable map = new HashTable(100);

        map.set("ashik",40);
        map.set("ashik",50);
        map.set("chinmay",60);
    
        System.out.println(map.get("ashik"));
        System.out.println(map.remove("ashik"));
        System.out.println(map.get("chinmay"));
    }
}