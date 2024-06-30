package com.github.aclijpio.collections;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * Кастомная реализация динамического массива.
 *
 * @param <E> тип элементов в списке
 */
public class CustomArrayList<E> implements CustomList<E> {

    private final static int DEFAULT_SIZE = 10;

    private int size;
    private Object[] elements;

    /**
     * Конструктор по умолчанию, инициализирующий список с заданной емкостью.
     */
    public CustomArrayList() {
        this.elements = new Object[DEFAULT_SIZE];
        this.size = 0;
    }

    /**
     * Конструктор, инициализирующий список с заданной начальной емкостью.
     *
     * @param size начальная емкость списка
     */
    public CustomArrayList(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("Illegal size: " + size);
        }
        this.elements = new Object[size];
        this.size = 0;
    }

    /**
     * Добавляет элемент в конец списка.
     *
     * @param element элемент, который нужно добавить
     */
    @Override
    public void add(E element) {
        if (size == elements.length) {
            increaseSize();
        }
        elements[size++] = element;
    }

    /**
     * Добавляет элемент в указанный индекс.
     *
     * @param index   индекс, в который нужно добавить элемент
     * @param element элемент, который нужно добавить
     * @throws IndexOutOfBoundsException если индекс выходит за пределы допустимого диапазона
     */
    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        if (size == elements.length) {
            increaseSize();
        }
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
    }

    @Override
    public void addFirst(E element) {
        this.add(0, element);
    }

    @Override
    public void addLast(E element) {
        add(element);
    }

    /**
     * Возвращает элемент, находящийся по указанному индексу.
     *
     * @param index индекс элемента, который нужно вернуть
     * @return элемент, находящийся по указанному индексу
     * @throws IndexOutOfBoundsException если индекс выходит за пределы допустимого диапазона
     */
    @SuppressWarnings("unchecked")
    @Override
    public E get(int index) {
        validateIndex(index);
        return (E) this.elements[index];
    }

    /**
     * Возвращает первый элемент в списке.
     * @return первый элемент в списке.
     */
    @Override
    public E getFirst() {
        if (isEmpty()) throw new NoSuchElementException("List is empty");

        return get(0);
    }

    /**
     * Возвращает последний элемент в списке.
     * @return последний элемент в списке.
     */
    @Override
    public E getLast() {
        if (isEmpty()) throw new NoSuchElementException("List is empty");

        return get(this.size - 1);
    }

    /**
     * Удаляет элемент по указанному индексу.
     *
     * @param index индекс элемента, который нужно удалить
     * @return удаленный элемент
     * @throws IndexOutOfBoundsException если индекс выходит за пределы допустимого диапазона
     */

    @SuppressWarnings("unchecked")
    @Override
    public E remove(int index) {
        validateIndex(index);
        E oldValue = (E) elements[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elements, index + 1, elements, index, numMoved);
        }
        elements[--size] = null;
        return oldValue;
    }

    /**
     * Очищает список.
     */
    @Override
    public void clear() {
        for (int i = 0; i < this.size; i++) {
            this.elements[i] = null;
        }
        this.size = 0;
    }


    /**
     * Сортирует список с использованием заданного компаратора.
     *
     * @param comparator компаратор, который будет использоваться для сортировки списка
     */
    @Override
    public void sort(Comparator<? super E> comparator) {
        bubbleSort(comparator);
    }

    /**
     * Реализация пузырьковой сортировки.
     *
     * @param comparator компаратор для сравнения элементов
     */
    @SuppressWarnings("unchecked")
    private void bubbleSort(Comparator<? super E> comparator) {
        boolean swapped;
        for (int i = 0; i < size - 1; i++) {
            swapped = false;
            for (int j = 0; j < size - 1 - i; j++) {
                if (comparator.compare((E) elements[j], (E) elements[j + 1]) > 0) {
                    E temp = (E) elements[j];
                    elements[j] = elements[j + 1];
                    elements[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    /**
     * Возвращает размер списка.
     *
     * @return текущий размер списка
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Проверяет, пуст ли список.
     *
     * @return true, если список пуст, иначе false
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Проверяет, находится ли индекс в допустимом диапазоне.
     *
     * @param index индекс для проверки
     * @throws IndexOutOfBoundsException если индекс выходит за пределы допустимого диапазона
     */
    private void validateIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    /**
     * Увеличивает размер массива элементов.
     */
    private void increaseSize() {
        int newSize = elements.length * 2;

        Object[] newArray = new Object[newSize];
        System.arraycopy(elements, 0, newArray, 0, size);

        elements = newArray;
    }
}
