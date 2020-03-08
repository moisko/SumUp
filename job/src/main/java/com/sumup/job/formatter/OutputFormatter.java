package com.sumup.job.formatter;

import com.sumup.job.model.Graph;
import org.springframework.stereotype.Component;

import java.util.Deque;
import java.util.Map;

@Component
public class OutputFormatter {

    public String formatOutput(Deque<Graph.Vertex> vertices, Map<String, String> nameToCommandMap) {
        StringBuilder sb = new StringBuilder();

        sb.append("#!/usr/bin/env bash");
        sb.append("\n");
        sb.append("\n");

        vertices.forEach(v -> {
            sb.append(nameToCommandMap.get(v.getLabel()));
            sb.append("\n");
        });

        return sb.toString();
    }
}
