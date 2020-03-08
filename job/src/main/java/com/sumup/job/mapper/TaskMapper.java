package com.sumup.job.mapper;

import com.sumup.job.model.Graph;
import com.sumup.job.model.Task;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class TaskMapper {

    public Graph toGraph(List<Task> tasks) {
        Graph graph = new Graph();
        tasks
                .stream()
                .map(Task::getName)
                .forEach(graph::addVertex);
        tasks
                .stream()
                .collect(Collectors.toMap(Task::getName, Task::getRequires))
                .forEach(graph::addDirectedEdges);
        return graph;
    }

    public Map<String, String> nameToCommandMap(List<Task> tasks) {
        return tasks
                .stream()
                .collect(Collectors.toMap(Task::getName, Task::getCommand));
    }
}
