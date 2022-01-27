public class ArrayDeque<T> {
    private int capacity;
    private int front, rare;
    private T[] items;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        front = rare = 0;
        capacity = 8;
    }

    private void reSize() {
        if (size() < 0.25 * capacity) {
            T[] newItems = (T[]) new Object[capacity / 2];
            int i = 0;
            while (front != rare) {
                newItems[i] = items[front];
                front = (front + 1) % capacity;
                i++;
            }
            front = 0;
            rare = i;
            capacity /= 2;
            items = newItems;
        } else {
            T[] newItems = (T[]) new Object[2 * capacity];
            int i = 0;
            while (front != rare) {
                newItems[i] = items[front];
                front = (front + 1) % capacity;
                i++;
            }
            front = 0;
            rare = i;
            capacity *= 2;
            items = newItems;
        }
        return;
    }

    public void addFirst(T item) {
        if ((front - 1 + capacity) % capacity == rare) {
            reSize();
        }
        front = (front - 1 + capacity) % capacity;
        items[front] = item;
        return;
    }

    public void addLast(T item) {
        if ((rare + 1) % capacity == front) {
            reSize();
        }
        items[rare] = item;
        rare = (rare + 1) % capacity;
        return;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return (rare - front + capacity) % capacity;
    }

    public void printDeque() {
        int p = front;
        while (p != rare) {
            System.out.print(items[p]);
            System.out.print(" ");
            p = (p + 1) % capacity;
        }
        return;
    }

    public T removeFirst() {
        if (size() == 0) {
            return null;
        } else {
            T old = items[front];
            front = (front + 1) % capacity;
            if (size() < 0.25 * capacity && capacity >= 16) {
                reSize();
            }
            return old;
        }
    }

    public T removeLast() {
        if (size() == 0) {
            return null;
        } else {
            rare = (rare - 1 + capacity) % capacity;
            T old = items[rare];
            if (size() < 0.25 * capacity && capacity >= 16) {
                reSize();
            }
            return old;
        }
    }

    public T get(int index) {
        if (size() <= index) {
            return null;
        } else {
            return items[(front + index) % capacity];
        }
    }
}
