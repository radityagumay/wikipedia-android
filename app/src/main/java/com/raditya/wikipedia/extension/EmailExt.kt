package com.raditya.wikipedia.extension

import java.util.regex.Pattern

private val VALID_EMAIL_ADDRESS_REGEX: Pattern by lazy {
    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE)
}

fun String.isValidEmail(): Boolean {
    val matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(this)
    return matcher.find()
}