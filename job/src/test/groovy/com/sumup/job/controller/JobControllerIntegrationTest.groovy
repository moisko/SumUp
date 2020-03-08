package com.sumup.job.controller


import com.sumup.job.service.JobService
import groovy.json.JsonOutput
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.web.context.WebApplicationContext
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup

@SpringBootTest
class JobControllerIntegrationTest extends Specification {

    @Autowired
    WebApplicationContext context

    @Autowired
    JobService jobService

    MockMvc mockMvc

    def setup() {
        mockMvc = webAppContextSetup(context).build()
    }

    def 'Sorting tasks'() {
        given: 'tasks to be sorted'

        def tasks = JsonOutput.toJson([
                tasks: [
                        [name: 'task-1', command: "touch /tmp/file1", requires: []],
                        [name: 'task-2', command: "cat /tmp/file1", requires: ['task-3']],
                        [name: 'task-3', command: "echo 'Hello World' > /tmp/file1", requires: ['task-1']],
                        [name: 'task-4', command: "rm /mp/file1", requires: ['task-2', 'task-3']],
                ]
        ])

        when: 'a call to sort the tasks is made'
        def response = mockMvc.perform(post('/job/sort')
                .content(tasks)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andReturn()
                .response
                .contentAsString

        then: 'it is successful'
        response
    }
}
