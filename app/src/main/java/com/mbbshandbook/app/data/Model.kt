package com.mbbshandbook.app.data

/** A single renderable piece of topic content. */
sealed class ContentBlock {
    data class Heading(val text: String) : ContentBlock()
    data class Paragraph(val text: String) : ContentBlock()
    data class Bullets(val items: List<String>) : ContentBlock()
    data class Tip(val text: String) : ContentBlock()
}

data class Topic(
    val id: String,
    val title: String,
    val summary: String,
    val blocks: List<ContentBlock>
)

data class Section(
    val id: String,
    val title: String,
    val description: String,
    val topics: List<Topic>
)
