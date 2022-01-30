// TODO: Make sure to make this class a part of the synthesizer package
package synthesizer;
import java.io.IOException;
import java.util.Iterator;

//TODO: Make sure to make this class and all of its methods public
//TODO: Make sure to make this class extend AbstractBoundedQueue<t>
public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        rb = (T[]) new Object[capacity];
        this.capacity = capacity;
        first = last = fillCount = 0;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    public void enqueue(T x) {
        if (last == first && capacity == fillCount) {
            throw new RuntimeException("Ring buffer overflow");
        }
        rb[last] = x;
        last = (last + 1) % capacity;
        fillCount++;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        if (last == first && capacity != fillCount) {
            throw new RuntimeException("Ring buffer underflow");
        }
        T p = rb[first];
        first = (first + 1) % capacity;
        fillCount--;
        return p;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        if (last == first && capacity != fillCount) {
            throw new RuntimeException("Ring buffer underflow");
        }
        return rb[first];
    }

    public Iterator<T> iterator() {
        return new ArrayBufferIterator();
    }

    private class ArrayBufferIterator implements Iterator<T> {
        private int pos;
        private int n;

        public ArrayBufferIterator() {
            pos = first;
            n = 0;
        }

        public boolean hasNext() {
            return n != fillCount;
        }

        public T next() {
            T p = rb[pos];
            pos = (pos + 1) % capacity;
            n++;
            return p;
        }
    }
}
