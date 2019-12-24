import java.util.Arrays;
import java.util.LinkedList;

public class TestProgram {
    public static void main(String[] args) throws MyListIsEmptyException, MyListIndexException {
        LinkedList list = new LinkedList();
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);

//        list.add(10, 2);

        for (Object item:list)
            System.out.println(item.toString());
        System.out.println("===============");

        MyLinkedList l = new MyLinkedList();
        l.addFirst(0);
        l.addFirst(1);
        l.addFirst(2);
        l.add(20);
        try {
            l.add(3,5);
            System.out.println("Deleted: " + l.removeAt(4).toString());
        } catch (MyListIndexException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 4; ++i) {
            System.out.println(l.get(i));
        }

//        MyLinkedList p = new MyLinkedList();
//        try {
//            System.out.println(p.getFirst());
//        } catch (MyListIsEmptyException e) {
//            System.out.println(e.toString());
//        }

//        System.out.println(l.getLast());

    }
}
