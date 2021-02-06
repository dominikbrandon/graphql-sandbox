package com.dominikbrandon.graphql

import java.util.UUID

data class Book(
    val id: UUID = UUID.randomUUID(),
    val title: String
)
