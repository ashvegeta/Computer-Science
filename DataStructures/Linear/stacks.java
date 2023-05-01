class Stack {
    int[] stack;
    int top=-1;

    Stack() {
        stack = new int[2];
    }

    int size() {
        return top+1;
    }

    boolean isEmpty() {
        return size()<=0 ? true : false;
    }

    boolean isFull() {
        return size()>=stack.length ? true : false;
    }

    void resize() {
        int[] temp = new int[2*size()];

        for(int i=0; i<size(); i++) temp[i] = stack[i];
        stack = temp;

        return;
    }

    int peek() {
        try {
            return stack[top];
        } catch(ArrayIndexOutOfBoundsException E) {
            System.out.println("Stack Empty: No element to peek");
            System.exit(-1);
        }

        return -1;
    }
 
    int pop() {
        try {
            return stack[top--];
        } catch(ArrayIndexOutOfBoundsException E) {
            System.out.println("Stack Empty: No element to pop");
            System.exit(-1);
        }

        return -1;
    }

    void add(int element) {
        if(isFull()) resize();
        
        stack[++top] = element;
    }
}

public class stacks {
    public static void main(String args[]) {
        Stack stack = new Stack();

        stack.add(1);
        stack.add(2);
        stack.add(3);
        stack.add(4);
        stack.add(5);
        stack.pop();
        stack.pop();
        System.out.println(stack.peek());
        stack.pop();
        stack.pop();
        stack.pop();
        System.out.println(stack.peek());
    }
}