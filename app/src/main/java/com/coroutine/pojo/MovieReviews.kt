package com.coroutine.pojo

import java.io.Serializable

data class MovieReviews(
    val copyright: String,
    val has_more: Boolean,
    val num_results: Int,
    val results: List<Result>,
    val status: String
): Serializable