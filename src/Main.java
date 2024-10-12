import com.prep.interview.linkedlists.LinkedList;
import com.prep.interview.linkedlists.Node;

public class Main {

    public static void main(String[] args) {

        LinkedList linkedList = new LinkedList();
        linkedList.appendToTail(1);
        linkedList.appendToTail(1);
        linkedList.appendToTail(9);
        linkedList.appendToTail(4);
        linkedList.appendToTail(1);
        linkedList.appendToTail(1);
        linkedList.appendToTail(2);
        linkedList.appendToTail(5);
        linkedList.appendToTail(1);
        linkedList.appendToTail(6);
        linkedList.appendToTail(3);

        linkedList.printLinkedList();
        linkedList.printNode(linkedList.partition(3));

    }
}