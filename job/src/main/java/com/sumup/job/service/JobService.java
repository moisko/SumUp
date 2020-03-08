package com.sumup.job.service;

import com.sumup.job.formatter.OutputFormatter;
import com.sumup.job.mapper.TaskMapper;
import com.sumup.job.model.Graph;
import com.sumup.job.model.Task;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    private final TaskMapper taskMapper;
    private final OutputFormatter outputFormatter;

    JobService(TaskMapper taskMapper, OutputFormatter outputFormatter) {
        this.taskMapper = taskMapper;
        this.outputFormatter = outputFormatter;
    }

    public String sortTasks(List<Task> tasks) {
        return outputFormatter.formatOutput(
                Graph.topologicalSort(taskMapper.toGraph(tasks)),
                taskMapper.nameToCommandMap(tasks)
        );
    }
}
