import com.prep.interview.linkedlists.LinkedList;
import com.prep.interview.linkedlists.Node;
import com.prep.interview.strings.Strings;

public class Main {

    public static void main(String[] args) {

        LinkedList linkedList = new LinkedList();
        linkedList.appendToTail(1);
        linkedList.appendToTail(9);
        linkedList.appendToTail(4);

        Node node1 = linkedList.findNode(9);

        LinkedList linkedList2 = new LinkedList();
        linkedList2.appendToTail(2);
        linkedList2.appendToTail(5);
        linkedList2.appendToTail(1);
        linkedList2.appendToTail(9);
        linkedList2.appendToTail(3);
        Node node2 = linkedList.findNode(9);

        linkedList.printLinkedList();
        linkedList2.printLinkedList();

        LinkedList linkedList3 = new LinkedList();

        Strings string = new Strings();

        System.out.println(string.firstUnique("abaadb"));

        linkedList3.printNode(linkedList.intersection(node1, node2));

    }
}