import com.prep.interview.arrays.Arrays;
import com.prep.interview.linkedlists.LinkedList;
import com.prep.interview.queues.AnimalShelter;
import com.prep.interview.queues.models.Cat;
import com.prep.interview.queues.models.Dog;
import com.prep.interview.strings.Strings;
import com.prep.interview.trees.Tree;

import java.util.Stack;

public class Main {

    public static void main(String[] args) {

        Arrays arrays = new Arrays();
        Strings strings = new Strings();
        LinkedList linkedList = new LinkedList();
        java.util.Stack<Integer> stack = new Stack<>();
        com.prep.interview.stacks.SortStack myStack = new com.prep.interview.stacks.SortStack();
        AnimalShelter animalShelter = new AnimalShelter();
        Tree tree = new Tree();

        tree.createBinarySearchTree(new int []{2,4,6,8,10,12,14,16});
    }
}