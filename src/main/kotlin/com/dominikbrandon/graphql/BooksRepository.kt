package com.dominikbrandon.graphql

import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.stereotype.Component

@Component
open class BooksRepository(private val mongoTemplate: MongoTemplate) {

    fun get(): List<Book> {
        return mongoTemplate.findAll(Book::class.java)
    }

    fun add(book: Book) {
        mongoTemplate.save(book)
    }
}
