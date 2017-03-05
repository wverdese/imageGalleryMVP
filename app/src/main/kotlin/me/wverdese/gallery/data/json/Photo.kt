package me.wverdese.gallery.data.json

import com.google.gson.annotations.SerializedName

/**
 * Data representation of a Photo, as it's returned from the API endpoint.
 * It also annotates each field with serialized names to parse/unparse a JSON string.
 */
data class Photo(
        @SerializedName("albumId") val albumId: Int,
        @SerializedName("id") val id: Int,
        @SerializedName("title") val title: String,
        @SerializedName("url") val imageUrl: String,
        @SerializedName("thumbnailUrl") val thumbnailUrl: String
)