import com.prep.interview.linkedlists.LinkedList;
import com.prep.interview.linkedlists.Node;

public class Main {

    public static void main(String[] args) {

        LinkedList linkedList = new LinkedList();
        linkedList.appendToTail(10);
        linkedList.appendToTail(20);
        linkedList.appendToTail(30);
        linkedList.appendToTail(30);
        linkedList.appendToTail(30);
        linkedList.appendToTail(20);
        linkedList.appendToTail(10);

        linkedList.printLinkedList();

        System.out.println(linkedList.isPalindrome());



    }
}