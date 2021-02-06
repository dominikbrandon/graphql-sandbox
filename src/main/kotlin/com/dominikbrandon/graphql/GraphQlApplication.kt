package com.dominikbrandon.graphql

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class GraphQlApplication

fun main(args: Array<String>) {
    runApplication<GraphQlApplication>(*args)
}
