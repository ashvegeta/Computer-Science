class Queue {
    int[] queue;
    int front, back, size;

    Queue() {
        queue = new int[2];
        front = back = size = 0;
    }

    int size() {
        return size;
    }

    boolean isEmpty() {
        return size()==0 ? true : false;
    }

    boolean isFull() {
        return size()==queue.length ? true : false;
    }

    void resize() {
        System.out.println("\nResizing Array ....");

        int curr_size = size();
        int[] temp = new int[2*curr_size];

        for(int i=front; i< front + curr_size; i++) temp[i%(2*curr_size)] = queue[i%curr_size];

        queue = temp; 
        back = (front + size())%queue.length;

        return;
    }

    int peek() {
        if(isEmpty()) {
            System.out.println("Queue Empty: No element to peek");
            System.exit(-1);
            return -1;
        } 
        
        return queue[front];     
    }
 
    int pop() {
        if(isEmpty()) {
            System.out.println("Queue Empty: No element to pop");
            System.exit(-1);
            return -1;
        }

        int elem = queue[front];
        front = (front+1)%queue.length;
        size--;

        return elem;
    }

    void add(int element) {
        if(isFull()) resize();
        
        queue[back] = element;
        back = (back+1)%queue.length;
        size++;
    }
}

public class queues {
    public static void main(String args[]) {
        Queue queue = new Queue();

        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);
        System.out.println(queue.pop());
        System.out.println(queue.peek());
        queue.add(5);
        queue.add(6);
        System.out.println(queue.pop());
        System.out.println(queue.peek());
        queue.add(7);
        queue.add(8);
        System.out.println(queue.peek());
    }
}