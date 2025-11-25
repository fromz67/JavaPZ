package org.lab6;

import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * A doubly-linked list implementation of {@link List} for elements that extend {@link RollingStock}.
 *
 * <p>This list has:
 * <ul>
 *     <li>Internal doubly-linked node structure.</li>
 *     <li>Three constructors: empty, with one element, and from a collection.</li>
 * </ul>
 *
 * @param <E> type of elements stored in this list; must extend {@link RollingStock}
 */
public class RollingStockLinkedList<E extends RollingStock<?>> implements List<E> {

    /**
     * Node of doubly-linked list.
     *
     * @param <E> element type
     */
    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.prev = prev;
            this.next = next;
        }
    }

    private Node<E> head;
    private Node<E> tail;
    private int size;
    private int modCount;

    /**
     * Creates an empty {@code RollingStockLinkedList}.
     */
    public RollingStockLinkedList() {
        // empty list by default
    }

    /**
     * Creates a list containing a single element.
     *
     * @param element element to add to this list
     * @throws NullPointerException if element is null
     */
    public RollingStockLinkedList(E element) {
        if (element == null) {
            throw new NullPointerException("Element must not be null");
        }
        linkLast(element);
    }

    /**
     * Creates a list containing elements of the specified collection
     * in the order returned by the collection's iterator.
     *
     * @param collection collection whose elements are to be placed into this list
     * @throws NullPointerException if collection or any of its elements is null
     */
    public RollingStockLinkedList(Collection<? extends E> collection) {
        if (collection == null) {
            throw new NullPointerException("Collection must not be null");
        }
        for (E e : collection) {
            if (e == null) {
                throw new NullPointerException("Collection must not contain null elements");
            }
            linkLast(e);
        }
    }

    // ================== helper internal methods ==================

    private void linkLast(E element) {
        Node<E> oldTail = tail;
        Node<E> newNode = new Node<>(oldTail, element, null);
        tail = newNode;
        if (oldTail == null) {
            head = newNode;
        } else {
            oldTail.next = newNode;
        }
        size++;
        modCount++;
    }

    private void linkBefore(E element, Node<E> successor) {
        final Node<E> predecessor = successor.prev;
        Node<E> newNode = new Node<>(predecessor, element, successor);
        successor.prev = newNode;
        if (predecessor == null) {
            head = newNode;
        } else {
            predecessor.next = newNode;
        }
        size++;
        modCount++;
    }

    private E unlink(Node<E> node) {
        final E element = node.item;
        final Node<E> next = node.next;
        final Node<E> prev = node.prev;

        if (prev == null) {
            head = next;
        } else {
            prev.next = next;
        }

        if (next == null) {
            tail = prev;
        } else {
            next.prev = prev;
        }

        node.item = null;
        node.next = null;
        node.prev = null;

        size--;
        modCount++;
        return element;
    }

    private Node<E> node(int index) {
        checkElementIndex(index);
        if (index < (size >> 1)) {
            Node<E> current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            return current;
        } else {
            Node<E> current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
            return current;
        }
    }

    private void checkElementIndex(int index) {
        if (!isElementIndex(index)) {
            throw new IndexOutOfBoundsException("Index: " + index + ", size: " + size);
        }
    }

    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index)) {
            throw new IndexOutOfBoundsException("Index: " + index + ", size: " + size);
        }
    }

    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }

    // ================== List interface implementation ==================

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new ListItr(0);
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        int i = 0;
        for (Node<E> x = head; x != null; x = x.next) {
            array[i++] = x.item;
        }
        return array;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T[] toArray(T[] a) {
        if (a == null) {
            throw new NullPointerException("Target array must not be null");
        }
        if (a.length < size) {
            a = (T[]) java.lang.reflect.Array.newInstance(
                    a.getClass().getComponentType(), size);
        }
        int i = 0;
        Object[] result = a;
        for (Node<E> x = head; x != null; x = x.next) {
            result[i++] = x.item;
        }
        if (a.length > size) {
            a[size] = null;
        }
        return a;
    }

    @Override
    public boolean add(E e) {
        if (e == null) {
            throw new NullPointerException("Element must not be null");
        }
        linkLast(e);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            return false;
        }
        for (Node<E> x = head; x != null; x = x.next) {
            if (o.equals(x.item)) {
                unlink(x);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Collection must not be null");
        }
        for (Object element : c) {
            if (!contains(element)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (c == null) {
            throw new NullPointerException("Collection must not be null");
        }
        boolean modified = false;
        for (E e : c) {
            add(e);
            modified = true;
        }
        return modified;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        checkPositionIndex(index);
        if (c == null) {
            throw new NullPointerException("Collection must not be null");
        }
        if (c.isEmpty()) {
            return false;
        }

        Node<E> successor = (index == size) ? null : node(index);
        Node<E> predecessor = (index == 0) ? null : (successor != null ? successor.prev : tail);

        for (E e : c) {
            Node<E> newNode = new Node<>(predecessor, e, null);
            if (predecessor == null) {
                head = newNode;
            } else {
                predecessor.next = newNode;
            }
            predecessor = newNode;
            size++;
        }

        if (successor == null) {
            tail = predecessor;
        } else {
            predecessor.next = successor;
            successor.prev = predecessor;
        }

        modCount++;
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Collection must not be null");
        }
        boolean modified = false;
        Iterator<E> it = iterator();
        while (it.hasNext()) {
            if (c.contains(it.next())) {
                it.remove();
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Collection must not be null");
        }
        boolean modified = false;
        Iterator<E> it = iterator();
        while (it.hasNext()) {
            if (!c.contains(it.next())) {
                it.remove();
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public void clear() {
        Node<E> current = head;
        while (current != null) {
            Node<E> next = current.next;
            current.item = null;
            current.prev = null;
            current.next = null;
            current = next;
        }
        head = null;
        tail = null;
        size = 0;
        modCount++;
    }

    @Override
    public E get(int index) {
        return node(index).item;
    }

    @Override
    public E set(int index, E element) {
        if (element == null) {
            throw new NullPointerException("Element must not be null");
        }
        Node<E> node = this.node(index);
        E oldValue = node.item;
        node.item = element;
        return oldValue;
    }

    @Override
    public void add(int index, E element) {
        checkPositionIndex(index);
        if (element == null) {
            throw new NullPointerException("Element must not be null");
        }
        if (index == size) {
            linkLast(element);
        } else {
            linkBefore(element, node(index));
        }
    }

    @Override
    public E remove(int index) {
        return unlink(node(index));
    }

    @Override
    public int indexOf(Object o) {
        int index = 0;
        if (o == null) {
            return -1;
        }
        for (Node<E> x = head; x != null; x = x.next) {
            if (o.equals(x.item)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int index = size - 1;
        if (o == null) {
            return -1;
        }
        for (Node<E> x = tail; x != null; x = x.prev) {
            if (o.equals(x.item)) {
                return index;
            }
            index--;
        }
        return -1;
    }

    @Override
    public ListIterator<E> listIterator() {
        return new ListItr(0);
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        checkPositionIndex(index);
        return new ListItr(index);
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        /*
         * For simplicity of implementation, this method returns
         * an independent new list, not a view.
         */
        if (fromIndex < 0 || toIndex > size || fromIndex > toIndex) {
            throw new IndexOutOfBoundsException(
                    "fromIndex: " + fromIndex + ", toIndex: " + toIndex + ", size: " + size);
        }
        RollingStockLinkedList<E> result = new RollingStockLinkedList<>();
        int i = 0;
        for (Node<E> x = head; x != null; x = x.next) {
            if (i >= fromIndex && i < toIndex) {
                result.add(x.item);
            }
            if (i >= toIndex) {
                break;
            }
            i++;
        }
        return result;
    }

    // ================== List iterator implementation ==================

    private class ListItr implements ListIterator<E> {

        private Node<E> lastReturned;
        private Node<E> next;
        private int nextIndex;
        private int expectedModCount = modCount;

        ListItr(int index) {
            next = (index == size) ? null : node(index);
            nextIndex = index;
        }

        private void checkForComodification() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException("List was modified concurrently");
            }
        }

        @Override
        public boolean hasNext() {
            return nextIndex < size;
        }

        @Override
        public E next() {
            checkForComodification();
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            lastReturned = next;
            next = next.next;
            nextIndex++;
            return lastReturned.item;
        }

        @Override
        public boolean hasPrevious() {
            return nextIndex > 0;
        }

        @Override
        public E previous() {
            checkForComodification();
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            if (next == null) {
                next = tail;
            } else {
                next = next.prev;
            }
            lastReturned = next;
            nextIndex--;
            return lastReturned.item;
        }

        @Override
        public int nextIndex() {
            return nextIndex;
        }

        @Override
        public int previousIndex() {
            return nextIndex - 1;
        }

        @Override
        public void remove() {
            checkForComodification();
            if (lastReturned == null) {
                throw new IllegalStateException("No element to remove");
            }
            Node<E> lastNext = lastReturned.next;
            RollingStockLinkedList.this.unlink(lastReturned);
            if (next == lastReturned) {
                next = lastNext;
            } else {
                nextIndex--;
            }
            lastReturned = null;
            expectedModCount = modCount;
        }

        @Override
        public void set(E e) {
            if (e == null) {
                throw new NullPointerException("Element must not be null");
            }
            checkForComodification();
            if (lastReturned == null) {
                throw new IllegalStateException("No element to set");
            }
            lastReturned.item = e;
        }

        @Override
        public void add(E e) {
            if (e == null) {
                throw new NullPointerException("Element must not be null");
            }
            checkForComodification();
            lastReturned = null;
            if (next == null) {
                RollingStockLinkedList.this.linkLast(e);
            } else {
                RollingStockLinkedList.this.linkBefore(e, next);
            }
            nextIndex++;
            expectedModCount = modCount;
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("RollingStockLinkedList[");
        Node<E> current = head;
        while (current != null) {
            builder.append(current.item);
            current = current.next;
            if (current != null) {
                builder.append(", ");
            }
        }
        builder.append(']');
        return builder.toString();
    }
}
