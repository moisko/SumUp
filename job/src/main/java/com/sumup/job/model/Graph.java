package com.sumup.job.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Graph {
    @Data
    public static class Vertex {
        private final String label;

        Vertex(String label) {
            this.label = label;
        }
    }

    private final Map<Vertex, List<Vertex>> adjVertices;

    public Graph() {
        this.adjVertices = new HashMap<>();
    }

    public void addVertex(String label) {
        adjVertices.putIfAbsent(new Vertex(label), new ArrayList<>());
    }

    public void addDirectedEdge(String label1, String label2) {
        Vertex v1 = new Vertex(label1);
        Vertex v2 = new Vertex(label2);
        adjVertices.get(v1).add(v2);
    }

    public void addDirectedEdges(String label1, List<String> labels2) {
        labels2.forEach(label2 -> addDirectedEdge(label1, label2));
    }

    public List<Vertex> getOutgoingVertices(Vertex v) {
        return adjVertices.get(v);
    }

    public Set<Vertex> getAllVertices() {
        return adjVertices.keySet();
    }

    public static Deque<Vertex> topologicalSort(Graph graph) {
        Deque<Vertex> ordering = new LinkedList<>();
        Set<Vertex> visited = new HashSet<>();
        for (Vertex v : graph.getAllVertices()) {
            if (!visited.contains(v)) {
                dfs(graph, v, visited, ordering);
            }
        }

        return ordering;
    }

    private static void dfs(Graph graph,
                            Vertex vertex,
                            Set<Vertex> visited,
                            Deque<Vertex> ordering
    ) {
        visited.add(vertex);
        for (Vertex v : graph.getOutgoingVertices(vertex)) {
            if (!visited.contains(v)) {
                dfs(graph, v, visited, ordering);
            }
        }

        ordering.addFirst(vertex);
    }
}
