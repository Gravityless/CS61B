public class LinkedListDeque<T> {
    private int size;
    private Node sentinel;

    private class Node {
        private Node prev, next;
        private T _item;

        Node() {
            prev = this;
            next = this;
        }

        Node(T item) {
            _item = item;
        }
    }

    public LinkedListDeque() {
        size = 0;
        sentinel = new Node();
    }

    public void addFirst(T item) {
        Node p = new Node(item);
        p.prev = sentinel;
        p.next = sentinel.next;
        sentinel.next.prev = p;
        sentinel.next = p;
        size++;
    }

    public void addLast(T item) {
        Node p = new Node(item);
        p.prev = sentinel.prev;
        p.next = sentinel;
        sentinel.prev.next = p;
        sentinel.prev = p;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node p = sentinel.next;
        while (p != sentinel) {
            System.out.print(p._item);
            System.out.print(" ");
            p = p.next;
        }
        return;
    }

    public T removeFirst() {
        T p;
        if (sentinel.next == sentinel) {
            p = null;
        } else {
            p = sentinel.next._item;
            sentinel.next.next.prev = sentinel;
            sentinel.next = sentinel.next.next;
            size--;
        }
        return p;
    }

    public T removeLast() {
        if (sentinel.next == sentinel) {
            return null;
        } else {
            T p = sentinel.prev._item;
            sentinel.prev.prev.next = sentinel;
            sentinel.prev = sentinel.prev.prev;
            size--;
            return p;
        }
    }

    public T get(int index) {
        Node p = sentinel;
        if (index > size - 1) {
            return null;
        } else {
            while (index >= 0) {
                p = p.next;
                index--;
            }
        }
        return p._item;
    }

    public T getRecursive(int index) {
        Node p = sentinel.next;
        if (index > size - 1) {
            return null;
        } else {
            p = getRecursive(p, index);
        }
        return p._item;
    }

    private Node getRecursive(Node p, int index) {
        if (index == 0) {
          return p;
        } else {
            return getRecursive(p.next, index - 1);
        }
    }
}
