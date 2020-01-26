package io.github.ovso.hometraining.data.model

import com.google.gson.annotations.SerializedName

data class Video(
    @SerializedName(value = "items")
    val items: List<Item> = listOf()
)

data class Item(
    @SerializedName("id")
    val id: Id = Id(),
    @SerializedName("snippet")
    val snippet: Snippet = Snippet()
)

data class Id(
    @SerializedName("kind")
    val kind: String = "",
    @SerializedName("videoId")
    val videoId: String = ""
)

data class Snippet(
    @SerializedName("thumbnails")
    val thumbnails: Thumbnails = Thumbnails(),
    @SerializedName("title")
    val title: String = ""
)

data class Thumbnails(
    @SerializedName("medium")
    val medium: Medium = Medium()
)

data class Medium(
    @SerializedName("height")
    val height: Int = 0,
    @SerializedName("url")
    val url: String = "",
    @SerializedName("width")
    val width: Int = 0
)
