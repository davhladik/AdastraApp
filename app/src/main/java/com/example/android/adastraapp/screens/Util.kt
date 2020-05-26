package com.example.android.adastraapp.screens

import android.os.Build
import android.text.Html
import android.text.Spanned
import androidx.core.text.HtmlCompat

fun getStatus(status: String): Spanned {

    val sb = StringBuilder()
    sb.apply{
        append("Status: $status")
    }

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        return Html.fromHtml(sb.toString(), Html.FROM_HTML_MODE_LEGACY)
    } else {
        return HtmlCompat.fromHtml(sb.toString(), HtmlCompat.FROM_HTML_MODE_LEGACY)
    }

}