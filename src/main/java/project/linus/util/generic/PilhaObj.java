package project.linus.util.generic;

public class PilhaObj<T> {

    private T[] pilha;
    private int topo;

    public PilhaObj(int capacity) {
        pilha = (T[]) new Object[capacity];
        topo = -1;
    }

    public Boolean isEmpty() {
        return topo == -1;
    }

    public Boolean isFull() {
        return topo == pilha.length -1;
    }

    public void push(T info) {
        if (isFull()) {
            throw new IllegalStateException();
        }
        pilha[++topo] = info;
    }

    public T pop() {
        return pilha[topo--];
    }

    public T peek() {
        return pilha[topo];
    }

    public void exibe() {

    }
}
