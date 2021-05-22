package com.sf.jetpack.mymov.utils

fun String.loadVideo(): String {
    return "<body style=\"margin:0;padding:0;\"><iframe class=\"youtube-player\" type=\"text/html\" width=\"100%\" allowfullscreen=\"true\" allowscriptaccess=\"always\" height=\"100%\" scrolling=\"no\" src=\"https://www.youtube.com/embed/${this}\" frameborder=\"0\"></body>"
}