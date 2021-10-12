package com.coroutine.pojo

import java.io.Serializable

data class Link(
    val suggested_link_text: String,
    val type: String,
    val url: String
): Serializable