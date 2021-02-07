package com.dominikbrandon.graphql.netflixdgs

import com.dominikbrandon.graphql.BooksRepository
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsData
import org.springframework.context.annotation.Profile

@DgsComponent
@Profile("netflix-dgs")
class BooksDataFetcher(private val booksRepository: BooksRepository) {

    @DgsData(parentType = "Query", field = "allBooks")
    fun get() = booksRepository.get()
}
