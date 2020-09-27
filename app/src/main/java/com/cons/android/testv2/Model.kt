package com.cons.android.testv2

import com.google.gson.annotations.SerializedName

class Model {
    class Title {
        @SerializedName("title")
        var title: Content? = null
        @SerializedName("id")
        var id: Int? = null
        @SerializedName("author")
        var author: Author? = null
    }
    class Result {
        @SerializedName("content")
        var content: Content? = null
        @SerializedName("_links")
        var links: Links? = null
    }
    class Links (
        @SerializedName("self") val self : List<Self>,
        @SerializedName("collection") val collection : List<Collection>,
        @SerializedName("about") val about : List<About>,
        @SerializedName("author") val author : List<Author>,
        @SerializedName("replies") val replies : List<Replies>,
        @SerializedName("version-history") val versionHistory : List<VersionHistory>,
        @SerializedName("predecessor-version") val predecessorVersion : List<PredecessorVersion>,
        @SerializedName("wp:attachment") val wpAttachment : List<WpAttachment>,
        @SerializedName("wp:term") val wpTerm : List<WpTerm>,
        @SerializedName("curies") val curies : List<Curies>
    )
    class About (
        @SerializedName("href") val href : String
    )
    class Author (
        @SerializedName("embeddable") val embeddable : Boolean,
        @SerializedName("href") val href : String
    )
    class Collection (
        @SerializedName("href") val href : String
    )
    class Content (
        @SerializedName("rendered") val rendered : String,
        @SerializedName("protected") val protected : Boolean
    )
    class Curies (
        @SerializedName("name") val name : String,
        @SerializedName("href") val href : String,
        @SerializedName("templated") val templated : Boolean
    )
    class PredecessorVersion (
        @SerializedName("id") val id : Int,
        @SerializedName("href") val href : String
    )
    class Replies (
        @SerializedName("embeddable") val embeddable : Boolean,
        @SerializedName("href") val href : String
    )
    class Self (
        @SerializedName("href") val href : String
    )
    class VersionHistory (
        @SerializedName("count") val count : Int,
        @SerializedName("href") val href : String
    )
    class WpAttachment (
        @SerializedName("href") val href : String
    )
    class WpTerm (
        @SerializedName("taxonomy") val taxonomy : String,
        @SerializedName("embeddable") val embeddable : Boolean,
        @SerializedName("href") val href : String
    )
    class ValResult {
        @SerializedName("code")
        var code: String? = null
        @SerializedName("message")
        var message: String? = null
        @SerializedName("data")
        var data: Status? = null
        @SerializedName("token")
        var token: String? = null
        @SerializedName("user_email")
        var mail: String? = null
        @SerializedName("user_nicename")
        var nicename: String? = null
        @SerializedName("user_display_name")
        var userDisplayName: String? = null
    }
    class Status {
        @SerializedName("status")
        var status: Int? = null
    }
}