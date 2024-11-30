package com.prep.interview.graphs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // Step 1: Create an adjacency list and in-degree array
        List<Integer>[] graph = new ArrayList[numCourses];
        int[] inDegree = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<>();
        }

        // Step 2: Populate graph and in-degree array
        for (int[] prereq : prerequisites) {
            int course = prereq[0];
            int prereqCourse = prereq[1];
            graph[prereqCourse].add(course);
            inDegree[course]++;
        }

        // Step 3: Initialize a queue with courses that have no prerequisites (in-degree == 0)
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        // Step 4: Process the courses using BFS
        int processedCourses = 0;

        while (!queue.isEmpty()) {
            int currentCourse = queue.poll();
            processedCourses++;

            // For each course, reduce the in-degree of its neighbors
            for (int neighbor : graph[currentCourse]) {
                inDegree[neighbor]--;

                // If a neighbor's in-degree becomes 0, add it to the queue
                if (inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        // Step 5: If we've processed all courses, there's no cycle, and we can finish all courses
        return processedCourses == numCourses;
    }

    public GraphNode cloneGraph(GraphNode node) {
        if (node == null) return null;

        Map<GraphNode, GraphNode> map = new HashMap<>();
        Queue<GraphNode> queue = new LinkedList<>();
        map.put(node, new GraphNode(node.getData()));
        queue.offer(node);

        while (!queue.isEmpty()) {
            GraphNode current = queue.poll();

            for (GraphNode child : current.getChildren()) {
                if (!map.containsKey(child)) {
                    map.put(child, new GraphNode(child.getData()));
                    queue.offer(child);
                }
                GraphNode clonedNode = map.get(current);
                GraphNode clonedChild = map.get(child);
                clonedNode.getChildren().add(clonedChild);
            }
        }
        return map.get(node);
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


