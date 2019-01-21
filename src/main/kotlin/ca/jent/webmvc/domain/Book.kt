package ca.jent.webmvc.domain

import java.time.Instant

data class Book(
        val id: String,
        var title: String,
        var author: String,
        var publishedDate: Instant?
)