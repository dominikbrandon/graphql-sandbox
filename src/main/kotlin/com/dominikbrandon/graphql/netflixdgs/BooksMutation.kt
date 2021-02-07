package com.dominikbrandon.graphql.netflixdgs

import com.dominikbrandon.graphql.Book
import com.dominikbrandon.graphql.BooksRepository
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsData
import com.netflix.graphql.dgs.InputArgument

@DgsComponent
class BooksMutation(private val booksRepository: BooksRepository) {

    @DgsData(parentType = "Mutation", field = "createBook")
    fun createBook(@InputArgument("book") book: Book) = booksRepository.add(book)
}
