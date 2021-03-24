package batch;

import java.util.Deque;
import java.util.LinkedList;

public class Worker {
    private Deque<Integer> elements = new LinkedList<>();
    private int size = 0;
    private int total = 0;

    public void addLast(int a) {
        elements.addLast(a);
        size++;
        total += a;
    }

    public void addFirst(int a) {
        elements.addFirst(a);
        size++;
        total += a;
    }

    public void removeLast() {
        int a = elements.removeLast();
        size--;
        total -= a;
    }

    public void removeFirst() {
        int a = elements.removeFirst();
        size--;
        total -= a;
    }

    public int getLast() {
        return elements.getLast();
    }

    public int getFirst() {
        return elements.getFirst();
    }

    public int getSize() {
        return size;
    }

    public int getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "elements=" + elements +
                ", size=" + size +
                ", total=" + total +
                '}';
    }
}
