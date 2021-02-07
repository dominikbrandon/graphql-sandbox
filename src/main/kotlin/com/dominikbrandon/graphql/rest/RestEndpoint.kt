package com.dominikbrandon.graphql.rest

import com.dominikbrandon.graphql.Book
import com.dominikbrandon.graphql.BooksRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rest")
class RestEndpoint(private val booksRepository: BooksRepository) {

    @GetMapping
    fun get() = booksRepository.get()

    @PostMapping
    fun save(@RequestBody book: Book) = booksRepository.add(book)
}
