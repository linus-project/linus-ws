package project.linus.util.generic;

public class ObjectList<T> {
    private T[] array;

    private int elementNumber;


    public ObjectList(int size) {
        array = (T[]) new Object[size];
        elementNumber = 0;
    }

    public void add(T element) {
        if (elementNumber >= array.length) {
            throw new IllegalStateException();
        }
        else {
            array[elementNumber++] = element;
        }
    }

    public int search(T element) {
        for (int i = 0; i < elementNumber; i++) {
            if (array[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    public boolean removeByIndex(int index) {
        if (index < 0 || index >= elementNumber) {
            System.out.println("\nInvalid index!");
            return false;
        }

        for (int i = index; i < elementNumber -1; i++) {
            array[i] = array[i+1];
        }

        elementNumber--;
        return true;
    }

    public boolean removeElement(T element) {
        return removeByIndex(search(element));
    }

    public int getSize() {
        return elementNumber;
    }

    public T getElement(int index) {
        if (index < 0 || index >= elementNumber) {
            return null;
        }
        else {
            return array[index];
        }
    }

    public void clear() {
        elementNumber = 0;
    }

    public void show() {
        if (elementNumber == 0) {
            System.out.println("\nThe list is empty.");
        }
        else {
            System.out.println("\nList elements:");
            for (int i = 0; i < elementNumber; i++) {
                System.out.println(array[i]);
            }
        }
    }
    public T[] getArray() {
        return array;
    }
}

