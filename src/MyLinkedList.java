class MyListIndexException extends Exception {
    String body = "MyListException occurred";
    String s;

    MyListIndexException() {
        s = "index is out of range";
    }

    public String toString() {
        if (s != null)
            return (body + ": " + s);
        else
            return (body);
    }
}

class MyListIsEmptyException extends Exception {
    String body = "MyListException occurred";
    String s;

    MyListIsEmptyException() {
        s = "list is empty";
    }

    public String toString() {
        if (s != null)
            return (body + ": " + s);
        else
            return (body);
    }
}

public class MyLinkedList{
    private MyNode head;
    private int size = 0;

    private boolean IsIndexInRange(int index) {
        return index < size;
    }

    public void add(Object o) {
        this.addLast(o);
    }

    public void addLast(Object o) {
        MyNode node = new MyNode();
        node.setValue(o);
        MyNode tmp = head;

        if (head == null) {
            head = node;
            size++;
        } else {
            while (tmp.getNextNode() != null) {
                tmp = tmp.getNextNode();
            }
            tmp.updateLink(node);
            size++;
        }
    }

    public void addFirst(Object o) {
        MyNode node = new MyNode();
        node.setValue(o);

        if (head != null) {
            node.updateLink(head);
        }
        head = node;
        size++;
    }

    public void add(int index, Object o) throws MyListIndexException, NullPointerException {
        MyNode node = new MyNode();
        node.setValue(o);

        if (!IsIndexInRange(index))
            throw new MyListIndexException();
        if (o == null)
            throw new NullPointerException();
        if (head == null) {
            head = node;
        } else if (index == 0) {
            node.updateLink(head);
            head = node;
        } else {
            MyNode current = head;
            int i = 0;
            while (i != (index - 1)) {
                current = current.getNextNode();
                ++i;
            }
            node.updateLink(current.getNextNode());
            current.updateLink(node);
        }
        size++;
    }

    public Object get(int index) throws MyListIsEmptyException, MyListIndexException {
        MyNode tmp = head;
        int i = 0;

        if (head == null)
            throw new MyListIsEmptyException();
        if (!IsIndexInRange(index))
            throw new MyListIndexException();
        while (i != index) {
            tmp = tmp.getNextNode();
            ++i;
        }
        return (tmp.getValue());
    }

    public Object getFirst() throws MyListIsEmptyException {
        if (head == null)
            throw new MyListIsEmptyException();
        return (head.getValue());
    }

    public Object getLast() throws MyListIsEmptyException {
        if (head == null)
            throw new MyListIsEmptyException();
        MyNode tmp = head;
        while (tmp.getNextNode() != null) {
            tmp = tmp.getNextNode();
        }
        return (tmp.getValue());
    }

    public Object removeLast() throws MyListIsEmptyException {
        Object elem;
        MyNode cur = head;
        MyNode prev = head;

        if (head == null)
            throw new MyListIsEmptyException();
        if (head.getNextNode() == null) {
            elem = head.getValue();
            head = null;
            return (elem);
        }
        while (cur.getNextNode() != null) {
            prev = cur;
            cur = cur.getNextNode();
        }
        elem = cur.getValue();
        prev.updateLink(null);
        return (elem);
    }

    public Object removeFirst() throws MyListIsEmptyException {
        Object elem;
        MyNode cur = head;

        if (head == null)
            throw new MyListIsEmptyException();
        elem = cur.getValue();
        head = cur.getNextNode();
        return (elem);
    }

    public Object removeAt(int index) throws MyListIsEmptyException, MyListIndexException {
        Object elem;
        int i = 0;
        MyNode cur = head;
        MyNode prev = head;

        if (!IsIndexInRange(index))
            throw new MyListIndexException();
        if (head == null)
            throw new MyListIsEmptyException();
        while (i != index) {
            prev = cur;
            cur = cur.getNextNode();
            i++;
        }
        elem = cur.getValue();
        prev.updateLink(cur.getNextNode());
        return (elem);
    }
}
