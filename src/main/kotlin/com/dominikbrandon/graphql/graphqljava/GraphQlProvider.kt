package com.dominikbrandon.graphql.graphqljava

import com.dominikbrandon.graphql.Book
import com.dominikbrandon.graphql.BooksRepository
import com.fasterxml.jackson.databind.ObjectMapper
import graphql.GraphQL
import graphql.schema.DataFetchingEnvironment
import graphql.schema.GraphQLSchema
import graphql.schema.idl.RuntimeWiring
import graphql.schema.idl.SchemaGenerator
import graphql.schema.idl.SchemaParser
import graphql.schema.idl.TypeRuntimeWiring.newTypeWiring
import graphql.spring.web.servlet.GraphQLEndpointConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.context.annotation.Profile
import org.springframework.util.ResourceUtils

@Configuration
@Profile("graphql-java")
@Import(GraphQLEndpointConfiguration::class)
class GraphQlProvider(private val booksRepository: BooksRepository, private val objectMapper: ObjectMapper) {

    @Bean
    fun graphQl(): GraphQL {
        val graphQlSchema = buildSchema()
        return GraphQL.newGraphQL(graphQlSchema).build()
    }

    private fun buildSchema(): GraphQLSchema {
        val schemaFile = ResourceUtils.getFile("classpath:schema/schema.graphqls")
        val typeRegistry = SchemaParser().parse(schemaFile)
        val runtimeWiring = buildWiring()
        return SchemaGenerator().makeExecutableSchema(typeRegistry, runtimeWiring)
    }

    private fun buildWiring() = RuntimeWiring.newRuntimeWiring()
        .type(
            newTypeWiring("Query")
                .dataFetcher("allBooks", allBooksDataFetcher())
        ).type(
            newTypeWiring("Mutation")
                .dataFetcher("createBook", addBook())
        )
        .build()

    private fun allBooksDataFetcher() = { _: DataFetchingEnvironment -> booksRepository.get() }

    private fun addBook() = { env: DataFetchingEnvironment ->
        val rawBook = env.getArgument<Map<String, Any>>("book")
        val book = objectMapper.convertValue(rawBook, Book::class.java)
        booksRepository.add(book)
    }
}