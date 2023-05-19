import java.util.Queue;
import java.util.LinkedList;

class MinHeap {
    int arr[];
    int size;

    MinHeap() {
        arr = new int[2];
        size = 0;
    }

    MinHeap(int heapsize) {
        arr = new int[heapsize];
        size = 0;
    }

    int parent(int idx) {
        return Math.max((idx - 1) / 2, 0);
    }

    int left(int idx) {
        return 2 * idx + 1;
    }

    int right(int idx) {
        return 2 * idx + 2;
    }

    void swap(int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    void HeapEmptyError() {
        System.out.println("Heap Empty!!");
        System.exit(-1);
    }

    int getMin() {
        if (size <= 0)
            HeapEmptyError();

        return arr[0];
    }

    int extractMin() {
        if (size <= 0)
            HeapEmptyError();

        int key = arr[0];
        arr[0] = arr[--size];
        int idx = 0;

        while (idx < size) {
            if (left(idx) >= size && right(idx) >= size)
                break;
            else if (right(idx) >= size) {
                if (arr[idx] <= arr[left(idx)])
                    break;
                if (arr[idx] > arr[left(idx)]) {
                    swap(idx, left(idx));
                    break;
                }
            } else {
                if (arr[idx] <= arr[left(idx)] || arr[idx] <= arr[right(idx)])
                    break;
                if (arr[left(idx)] <= arr[right(idx)] && arr[left(idx)] < arr[idx]) {
                    swap(idx, left(idx));
                    idx = left(idx);
                } else if (arr[right(idx)] < arr[left(idx)] && arr[right(idx)] < arr[idx]) {
                    swap(idx, right(idx));
                    idx = right(idx);
                }
            }
        }

        return key;
    }

    void resize() {
        int temp[] = new int[2 * size];

        for (int i = 0; i < size; i++)
            temp[i] = arr[i];

        arr = temp;
    }

    void levelOrder() {
        if (size <= 0)
            HeapEmptyError();

        Queue<Integer> q = new LinkedList<>();
        int temp;

        q.add(0);

        while (q.peek() != null) {
            temp = q.poll();
            System.out.println(arr[temp]);

            if (left(temp) < size)
                q.add(left(temp));
            if (right(temp) < size)
                q.add(right(temp));

        }

        System.out.println("");
    }

    void insert(int key) {
        if (size == arr.length)
            resize();

        arr[size] = key;
        int idx = size;

        while (arr[idx] < arr[parent(idx)]) {
            swap(idx, parent(idx));
            idx = parent(idx);
        }

        size += 1;
    }
}

public class MinHeaps {
    public static void main(String[] args) {
        MinHeap hp = new MinHeap(2);

        hp.insert(5);
        hp.insert(2);
        System.out.println("min: " + hp.getMin());
        hp.insert(3);
        hp.insert(1);
        System.out.println("min: " + hp.extractMin());
        System.out.println("min: " + hp.getMin());
    }
}
