package com.vmo.ecom.data.util

open class AppException(
    val title: String = "",
    override val message: String = "",
    cause: Throwable = Throwable("")
) : Throwable(message, cause)