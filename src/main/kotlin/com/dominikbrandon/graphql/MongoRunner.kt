package com.dominikbrandon.graphql

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.mongo.MongoClientSettingsBuilderCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.utility.DockerImageName

@Configuration
open class MongoRunner {
    private companion object {
        val logger: Logger = LoggerFactory.getLogger(MongoRunner::class.java)
    }

    @Bean
    open fun customMongoPropertiesCustomizer(): MongoClientSettingsBuilderCustomizer {
        val mongoUrl = run()
        return MongoUrlOverwriter(mongoUrl)
    }

    private fun run(): String {
        val mongoContainer = runContainer()
        val mongoUrl = mongoContainer.replicaSetUrl
        logger.info("Started mongo testcontainer at {}", mongoUrl)
        return mongoUrl
    }

    private fun runContainer(): MongoDBContainer {
        val mongoDBContainer = MongoDBContainer(DockerImageName.parse("mongo:4"))
        mongoDBContainer.start()
        return mongoDBContainer
    }

    private class MongoUrlOverwriter(private val mongoUrl: String) : MongoClientSettingsBuilderCustomizer {

        override fun customize(clientSettingsBuilder: MongoClientSettings.Builder) {
            clientSettingsBuilder.applyConnectionString(ConnectionString(mongoUrl))
        }
    }
}