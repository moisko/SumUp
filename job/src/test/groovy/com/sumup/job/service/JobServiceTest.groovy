package com.sumup.job.service

import com.sumup.job.formatter.OutputFormatter
import com.sumup.job.mapper.TaskMapper
import com.sumup.job.model.Task
import spock.lang.Specification

class JobServiceTest extends Specification {

    def service = new JobService(
            new TaskMapper(),
            new OutputFormatter()
    )

    def 'Sorting tasks'() {
        given: 'a set of tasks'
        def tasks = [
                new Task(name: 'task-1', command: "touch /tmp/file1", requires: []),
                new Task(name: 'task-2', command: "cat /tmp/file1", requires: ['task-3']),
                new Task(name: 'task-3', command: "echo 'Hello World' > /tmp/file1", requires: ['task-1']),
                new Task(name: 'task-4', command: "rm /mp/file1", requires: ['task-2', 'task-3']),
        ]

        when: 'a request to sort those tasks is made'
        def result = service.sortTasks(tasks)

        then: 'the output contains tasks in sorted order'
        result == "#!/usr/bin/env bash\n" +
                "\n" +
                "rm /mp/file1\n" +
                "cat /tmp/file1\n" +
                "echo 'Hello World' > /tmp/file1\n" +
                "touch /tmp/file1\n"
    }
}
