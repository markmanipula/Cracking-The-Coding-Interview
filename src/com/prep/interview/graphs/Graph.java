package com.prep.interview.graphs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Graph {

    private List<GraphNode> nodes;

    public void addNode(GraphNode node) {
        nodes.add(node);
    }

    public void bfsPrintGraph(GraphNode start) {
        if (start == null) return;

        Queue<GraphNode> queue = new LinkedList<>();
        queue.offer(start);
        start.setState(State.VISITING);

        while (!queue.isEmpty()) {
            GraphNode currentParent = queue.poll();
            System.out.println(currentParent.getData());

            for (GraphNode child : currentParent.getChildren()) {
                if (child.getState().equals(State.UNVISITED)) {
                    child.setState(State.VISITING);
                    queue.offer(child);
                }
            }
            currentParent.setState(State.VISITED);
        }
    }

    public boolean bfsSearch(GraphNode start, GraphNode end) {
        if (start == end) return true;

        Queue<GraphNode> queue = new LinkedList<>();
        queue.offer(start);
        start.setState(State.VISITED);

        while (!queue.isEmpty()) {
            GraphNode current = queue.poll();
            for (GraphNode node : current.getChildren()) {
                if (node == end) {
                    return true;
                }
                if (!node.getState().equals(State.VISITED)) {
                    node.setState(State.VISITED);
                    queue.offer(node);
                }
            }
        }
        return false;
    }

    public boolean dfsSearch(GraphNode start, GraphNode end) {
        return false;
    }

}


