package com.sumup.job.controller;

import com.sumup.job.model.Tasks;
import com.sumup.job.service.JobService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/job", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @RequestMapping(value = "/sort", method = RequestMethod.POST)
    public ResponseEntity<String> sort(@RequestBody Tasks tasks) {
        return ResponseEntity.ok(jobService.sortTasks(tasks.getTasks()));
    }
}
