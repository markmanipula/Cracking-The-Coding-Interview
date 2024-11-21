package com.prep.interview.graphs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GraphNode {

    private char data;
    private State state;
    private List<GraphNode> children;

}
