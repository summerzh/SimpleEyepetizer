package com.gyt.simplereader.base

data class SearchBookBean(
        val books: List<Book>,
        val ok: Boolean,
        val total: Int
) {
    data class Book(
            val _id: String,
            val aliases: String,
            val allowMonthly: Boolean,
            val author: String,
            val banned: Int,
            val cat: String,
            val contentType: String,
            val cover: String,
            val hasCp: Boolean,
            val highlight: Highlight,
            val lastChapter: String,
            val latelyFollower: Int,
            val retentionRatio: Double,
            val shortIntro: String,
            val site: String,
            val sizetype: Int,
            val superscript: String,
            val title: String,
            val wordCount: Int
    ) {
        data class Highlight(
                val author: List<String>,
                val title: List<String>
        )
    }
}