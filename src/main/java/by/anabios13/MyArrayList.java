package by.anabios13;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * Класс MyArrayList представляет простую реализацию динамического массива (ArrayList)
 * для хранения элементов в Java. Этот класс обеспечивает возможность добавить элемент,
 * добавить элемент по индексу, получить элемент,удалить элемент, очистить всю коллекцию,
 * отсортировать, заменить элемент по индексу.
 * <p>
 * Этот класс является не потокобезопасным и предназначен для использования в однопотоковых
 * сценариях. Он также включает алгоритм сортировки quicksort для сортировки элементов списка.
 *
 * @param <E> тип элементов, которые хранит MyArrayList
 */
public class MyArrayList<E> {
    private final int INIT_SIZE = 16;
    private final int CUT_RATE = 4;
    private Object[] array = new Object[INIT_SIZE];
    private int size = 0;


    /**
     * Пегрузка метода toString() для более наглядного вывода MyArrayList.
     * С помощью Stream API преобразует массив array в список и далее в строку
     * В строке удаляет фигурные скобки
     *
     * @return возвращает строку с представлением MyArrayList
     */
    @Override
    public String toString() {
        String outputString = Arrays.stream(array).filter(x -> x != null).collect(Collectors.toList()).toString();
        outputString = outputString.substring(1, outputString.length() - 1);
        return "MyArrayList {" +
                outputString +
                '}';
    }

    /**
     * Добавляет новый элемент в список. При достижении размера внутреннего
     * массива происходит его увеличение в два раза.
     *
     * @param item
     */
    public void add(E item) {
        if (size == array.length - 1)
            resize(array.length * 2); // увеличу в 2 раза, если достигли границ
        array[size++] = item;
    }

    /**
     * Добавляет новый элемент в список по индексу. При достижении размера внутреннего
     * массива происходит его увеличение в два раза.
     *
     * @param index   индекс по которому добавится новый элемент
     * @param element элемент который необходимо добавить по данному индексу
     */
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        if (size >= array.length - 1) {
            resize(array.length * 2);
        }
        for (int i = size; i >= index; i--) {
            array[i + 1] = array[i];
        }
        array[index] = element;
        size++;
    }

    /**
     * Возвращает элемент списка по индексу.
     *
     * @param index индекс для получения элемента
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        return (E) array[index];
    }

    /**
     * Удаляет элемент списка по индексу. Все элементы справа от удаляемого
     * перемещаются на шаг налево. Если после удаления элемента количество
     * элементов стало в CUT_RATE раз меньше чем размер внутреннего массива,
     * то внутренний массив уменьшается в два раза, для экономии занимаемого
     * места.
     */
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        for (int i = index; i < size; i++) {
            array[i] = array[i + 1];
        }
        array[size] = null;
        size--;
        if (array.length > INIT_SIZE && size < array.length / CUT_RATE) {
            resize(array.length / 2); // если элементов в CUT_RATE раз меньше чем
            // длина массива, то уменьшу в два раза
        }
    }

    /**
     * @Return Возвращает количество элементов в списке
     */
    public int size() {
        return size;
    }

    /**
     * Очищает ArrayList от всех элементов
     * Размер массива array меняется на INIT_SIZE
     * size возвращается к значению ноль
     */
    public void clear() {
        array = new Object[INIT_SIZE];
        size = 0;
    }

    /**
     *  Заменяет элемент по заданному индексу.
     * @param index   индекс по которому заменится новый элемент
     * @param element элемент который необходимо заменить по данному индексу
     */
    public void replace(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        array[index] = element;
    }

    private void resize(int newLength) {
        array = Arrays.copyOf(array, newLength);
    }


    /**
     * Сортирует список с использованием алгоритма quicksort.
     * @param comparator компаратор для сравнения элементов
     */
    public void quickSort(Comparator<? super E> comparator) {
        quickSort(0, size - 1, comparator);
    }

    private void quickSort(int low, int high, Comparator<? super E> comparator) {
        if (low < high) {
            int pivotIndex = partition(low, high, comparator);
            quickSort(low, pivotIndex - 1, comparator);
            quickSort(pivotIndex + 1, high, comparator);
        }
    }

    private int partition(int low, int high, Comparator<? super E> comparator) {
        E pivot = get(high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (comparator.compare(get(j), pivot) <= 0) {
                i++;
                swap(i, j);
            }
        }
        swap(i + 1, high);
        return i + 1;
    }

    private void swap(int i, int j) {
        E temp = get(i);
        array[i] = get(j);
        array[j] = temp;
    }
}
