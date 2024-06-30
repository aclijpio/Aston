package com.github.aclijpio.collections;

import java.util.*;

public class CustomLinkedList<E> implements CustomList<E> {

    private Node<E> first;
    private Node<E> last;
    private int size;

    public CustomLinkedList() {
        this.size = 0;
    }

    /**
     * Добавляет элемент в конец списка.
     *
     * @param element элемент для добавления в список.
     */
    @Override
    public void add(E element) {
        Node<E> node = new Node<>(element, null, last);
        if (last != null) {
            last.next = node;
        }
        last = node;
        if (first == null) {
            first = node;
        }
        size++;
    }


    /**
     * Добавляет элемент в список по указанному индексу.
     *
     * @param index индекс, куда добавляется элемент.
     * @param element элемент для добавления в список.
     * @throws IndexOutOfBoundsException если индекс вне допустимого диапазона.
     */
    @Override
    public void add(int index, E element) {
        validateIndex(index);
        if (index == size) {
            this.add(element);
            return;
        }
        Node<E> currentNode = getNode(index);
        Node<E> newNode = new Node<>(element, currentNode, currentNode.prev);
        if (currentNode.prev != null)
            currentNode.prev.next = newNode;
        else
            first = newNode;

        currentNode.prev = newNode;
        size++;
    }

    /**
     * Добавляет элемент перед всеми элементами в списке.
     * @param element элемент для добавления в список.
     */
    @Override
    public void addFirst(E element){
        Node<E> node = new Node<>(element, first, null);
        if (this.first != null)
            this.first.prev = node;
        first = node;

        if (this.last == null)
            last = node;

        size++;
    }

    /**
     * Добавляет элемент в конец списка.
     * @param element элемент для добавления в список.
     */
    @Override
    public void addLast(E element){
        this.add(element);
    }

    /**
     * Возвращает элемент по указанному индексу.
     *
     * @param index индекс элемента, который нужно вернуть.
     * @return элемент по указанному индексу.
     * @throws IndexOutOfBoundsException если индекс вне допустимого диапазона.
     */
    @Override
    public E get(int index) {
        return getNode(index).value;
    }

    /**
     * Возвращает первый элемент в списке.
     * @return первый элемент в списке.
     */
    public E getFirst(){
        if (this.first == null)
            throw new NoSuchElementException();
        return this.first.value;
    }

    /**
     * Возвращает последний элемент в списке.
     * @return последний элемент в списке.
     */
    public E getLast(){
        if (this.last == null)
            throw new NoSuchElementException();
        return this.last.value;
    }

    /**
     * Удаляет элемент по указанному индексу.
     *
     * @param index индекс элемента, который нужно удалить.
     * @return удаленный элемент.
     * @throws IndexOutOfBoundsException если индекс вне допустимого диапазона.
     */
    @Override
    public E remove(int index) {
        Node<E> node = getNode(index);
        if (node.prev != null)
            node.prev.next = node.next;
        else
            first = node.next;

        if (node.next != null)
            node.next.prev = node.prev;
        else
            last = node.prev;

        size--;
        return node.value;
    }

    /**
     * Очищает список.
     */
    @Override
    public void clear() {
        first = last = null;
        size = 0;
    }

    /**
     * Сортирует список с использованием компаратора.
     *
     * @param comparator компаратор для сортировки.
     */
    @Override
    public void sort(Comparator<? super E> comparator) {
        if (size <= 1) return;

        insertionSort(comparator);
    }

    private void insertionSort(Comparator<? super E> comparator) {
        for (Node<E> current = first.next; current != null; current = current.next) {
            Node<E> prev = current.prev;
            E currentValue = current.value;

            while (prev != null && comparator.compare(prev.value, currentValue) > 0) {
                prev.next.value = prev.value;
                prev = prev.prev;
            }

            if (prev == null) {
                first.value = currentValue;
            } else {
                prev.next.value = currentValue;
            }
        }
    }

    /**
     * Возвращает размер списка.
     *
     * @return размер списка.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Проверяет, пуст ли список.
     *
     * @return true, если список пуст, иначе false.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Возвращает узел по индексу.
     *
     * @param index индекс узла.
     * @return узел по указанному индексу.
     * @throws IndexOutOfBoundsException если индекс вне допустимого диапазона.
     */
    private Node<E> getNode(int index) {
        validateIndex(index);
        Node<E> current;
        if (index < (size >> 1)) {
            current = first;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            current = last;
            for (int i = size - 1; i > index; i--)
                current = current.prev;

        }
        return current;
    }

    /**
     * Проверяет, находится ли индекс в допустимом диапазоне.
     *
     * @param index индекс для проверки
     * @throws IndexOutOfBoundsException если индекс выходит за пределы допустимого диапазона
     */
    protected void validateIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    private static class Node<E> {
        E value;
        Node<E> next;
        Node<E> prev;

        public Node(E value, Node<E> next, Node<E> prev) {
            this.value = value;
            this.next = next;
            this.prev = prev;
        }
    }
}
