package com.comrades.article

data class ArticleResponse(
    val id: Int,
    val authorNickname: String,
    val title: String,
    val description: String,
    val caption: String,
    val contents: String,
    val imageId: Int,
)

object ArticleData{

    fun getArticles() = listOf(
        ArticleResponse(0, "author0","title1", "text text text text" +
                " text text text", "cap0", "text", R.drawable.jojo_main),
        ArticleResponse(1, "author0", "title2", "text text textext text",
            "cap1", "text", R.drawable.jujutsu_main),
        ArticleResponse(2, "author0","title3", "text texext text",
            "cap3", "text", R.drawable.jojo_main),
        ArticleResponse(3,"author0","title1", "text text text text" +
                " text text text", "cap4", "text", R.drawable.jujutsu_main),
        ArticleResponse(4, "author0", "title100", "text text text",
            "cap5", "text", R.drawable.jojo_main)
    )

    fun getArticlesByNickname(nickname: String) =
        getArticles().filter{ it.authorNickname == nickname }

}