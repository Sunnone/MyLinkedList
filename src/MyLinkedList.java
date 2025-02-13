import java.util.Collection;

public class MyLinkedList<E>{

    int size = 0;

    Node<E> first;

    Node<E> last;

    //Конструктор по умолчанию
    public MyLinkedList(){
    }

    //Конструктор с параметрами
    public MyLinkedList(Collection<? extends E> c){
        this();
        addAll(c);
    }

    //Метод обертка для вызова addAll
    public boolean addAll(Collection<? extends E> c){
        return addAll(size, c);
    }

    //Метод вставки в начало
    private void linkFirst(E e){
        final Node<E> f = first;
        final Node<E> newNode = new Node<>(null, e, f);
        first = newNode;
        if (f == null){
            last = newNode;
        } else {
            f.prev = newNode;
        }
        size++;
    }

    //Метод для вставки в конец
    void linkLast(E e){
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, e, null);
        last = newNode;
        if(l == null){
            first = newNode;
        } else {
            l.next = newNode;
        }
    }

    //Метод для вставки перед определенным элементом
    void linkBefore(E e, Node<E> succ){
        final Node<E> pred = succ.prev;
        final Node<E> newNode = new Node<>(pred, e, succ);
        succ.prev = newNode;
        if( pred == null){
            first = newNode;
        } else {
            pred.next = newNode;
        }
        size++;
    }

    //Метод для удаления первого элемента
    private E unlinkFirst(Node<E> f){
        final E element = f.item;
        final Node<E> next = f.next;
        f.item = null;
        f.next = null;
        first = next;
        if (next == null){
            last = null;
        } else {
            next.prev = null;
        }
        size--;
        return element;
    }

    //Метод для удаления последленего элемента
    private E unlinkLast(Node<E> l){
        final E element = l.item;
        final Node<E> prev = l.prev;
        l.item = null;
        l.prev = null;
        last = prev;
        if (prev == null){
            first = null;
        } else {
            prev.next = null;
        }
        size--;
        return element;
    }

    //Метод для удаления любого элемента
    E unlink(Node<E> x){
        final E element = x.item;
        final Node<E> next = x.next;
        final Node<E> prev = x.prev;

        if(prev == null){
            first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if(next == null){
            last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }

        x.item = null;
        size--;
        return element;
    }

    //Метод добавляющий все элементы переданной коллекции
    public boolean addAll(int index, Collection<? extends E> c){
        Object[] a = c.toArray();
        int arrLenght = a.length;
        if (arrLenght == 0)
            return false;

        Node<E> pred, succ;
        if(index == size){
            succ = null;
            pred = last;
        } else {
            succ = node(index);
            pred = succ.prev;
        }

        for(Object o : a){
            E e = (E) o;
            Node<E> newNode = new Node<>(pred, e, null);
            if(pred == null){
                first = newNode;
            } else {
                pred.next = newNode;
            }
            pred = newNode;
        }

        if(succ == null){
            last = pred;
        } else {
            pred.next = succ;
            succ.prev = pred;
        }

        size += arrLenght;
        return true;
    }

    // Возвращает указанную в индексе ноду
    Node<E> node(int index){
        if(index < (size / 2)){
            Node<E> x = first;
            for(int i = 0; i < index; i++){
                x = x.next;
            }
            return x;
        } else {
            Node<E> x = last;
            for(int i = size - 1; i > index; i--){
                x = x.prev;
            }
            return x;
        }
    }

    void helloWorld(){
        System.out.println("Hello Wordl!");
    }

    //Внутренний класс Node - где Node это один из элементов массива
    private static class Node<E>{
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next){
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
}

