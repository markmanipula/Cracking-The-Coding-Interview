import com.prep.interview.linkedlists.LinkedList;
import com.prep.interview.linkedlists.Node;

public class Main {

    public static void main(String[] args) {

        LinkedList linkedList = new LinkedList();
        linkedList.appendToTail(10);
        linkedList.appendToTail(20);
        linkedList.appendToTail(30);
        linkedList.appendToTail(40);
        linkedList.appendToTail(50);
        linkedList.appendToTail(60);

        linkedList.printLinkedList();

        Node tobeDeleted = linkedList.findNode(30);
        linkedList.deleteMiddleNode(tobeDeleted);

        linkedList.printLinkedList();



    }
}