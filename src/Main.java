import com.prep.interview.arrays.Arrays;
import com.prep.interview.graphs.Graph;
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
        Graph graph = new Graph();
        Tree tree = new Tree();

        System.out.println(graph.canFinish(6, new int[][]{{1, 0}, {2, 1}, {3, 2}, {1, 3}}));
    }
}