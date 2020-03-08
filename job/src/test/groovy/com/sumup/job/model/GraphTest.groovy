package com.sumup.job.model

import spock.lang.Specification

class GraphTest extends Specification {

    def 'Topological sorting of a graph'() {
        given: 'a graph'
        def graph = new Graph()
        graph.addVertex('task-1')
        graph.addVertex('task-2')
        graph.addVertex('task-3')
        graph.addVertex('task-4')

        graph.addDirectedEdge('task-2', 'task-3')
        graph.addDirectedEdge('task-3', 'task-1')
        graph.addDirectedEdge('task-4', 'task-2')
        graph.addDirectedEdge('task-4', 'task-3')

        when: 'a request to sort topologically this graph is made'
        def order = Graph.topologicalSort(graph)

        then: 'the vertices are ordered so that for every edge the source comes before the destination'
        order.findIndexOf { it.label == 'task-4' } < order.findIndexOf { it.label == 'task-2' }
        order.findIndexOf { it.label == 'task-4' } < order.findIndexOf { it.label == 'task-1' }
        order.findIndexOf { it.label == 'task-4' } < order.findIndexOf { it.label == 'task-3' }

        order.findIndexOf { it.label == 'task-2' } < order.findIndexOf { it.label == 'task-1' }
        order.findIndexOf { it.label == 'task-2' } < order.findIndexOf { it.label == 'task-3' }

        order.findIndexOf { it.label == 'task-3' } < order.findIndexOf { it.label == 'task-1' }
    }
}
