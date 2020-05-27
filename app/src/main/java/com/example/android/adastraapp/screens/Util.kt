package com.example.android.adastraapp.screens

import android.os.Build
import android.text.Html
import android.text.Spanned
import android.util.Log
import androidx.core.text.HtmlCompat
import com.example.android.adastraapp.database.Boosters

fun getSerial(serial: String): Spanned {

    val sb = StringBuilder()
    sb.apply{
        append("Serial: $serial")
    }

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        return Html.fromHtml(sb.toString(), Html.FROM_HTML_MODE_LEGACY)
    } else {
        return HtmlCompat.fromHtml(sb.toString(), HtmlCompat.FROM_HTML_MODE_LEGACY)
    }

}

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

fun getLaunchDate(date: String): Spanned {
    val sb = StringBuilder()
    if (date == "NONE"){
        sb.apply {
            append("Launch date: ")
//            append("<br>")
            append(date)
        }
    }else{
        val year = date.subSequence(0,4)
        val month = date.subSequence(5,7)
        val day = date.subSequence(8,10)

        val hour = date.subSequence(11,13)
        val min = date.subSequence(14,16)

        sb.apply{
            append("Launch date: ")
//            append("<br>")
            append("$day.$month.$year")
        }
    }



    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        return Html.fromHtml(sb.toString(), Html.FROM_HTML_MODE_LEGACY)
    } else {
        return HtmlCompat.fromHtml(sb.toString(), HtmlCompat.FROM_HTML_MODE_LEGACY)
    }

}

//fun getReuseCount(count: Int): Spanned {
//
//    val sb = StringBuilder()
//    sb.apply{
//        append("Reuse count: $count")
//    }
//
//    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//        return Html.fromHtml(sb.toString(), Html.FROM_HTML_MODE_LEGACY)
//    } else {
//        return HtmlCompat.fromHtml(sb.toString(), HtmlCompat.FROM_HTML_MODE_LEGACY)
//    }
//
//}


fun detailBoosterSerial(item: Boosters?):Spanned {
    val sb = StringBuilder()
    item?.let {
        sb.apply {
            append(item.core_serial)
        }
    }
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        return Html.fromHtml(sb.toString(), Html.FROM_HTML_MODE_LEGACY)
    } else {
        return HtmlCompat.fromHtml(sb.toString(), HtmlCompat.FROM_HTML_MODE_LEGACY)
    }
}

fun detailBoosterStatus(item: Boosters?):Spanned {
    val sb = StringBuilder()
    item?.let {
        sb.apply {
            append(item.status)
        }
    }
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        return Html.fromHtml(sb.toString(), Html.FROM_HTML_MODE_LEGACY)
    } else {
        return HtmlCompat.fromHtml(sb.toString(), HtmlCompat.FROM_HTML_MODE_LEGACY)
    }
}

fun detailBoosterReuseCount(item: Boosters?):Spanned {
    val sb = StringBuilder()
    item?.let {
        sb.apply {
            append(item.reuse_count)
        }
    }
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        return Html.fromHtml(sb.toString(), Html.FROM_HTML_MODE_LEGACY)
    } else {
        return HtmlCompat.fromHtml(sb.toString(), HtmlCompat.FROM_HTML_MODE_LEGACY)
    }
}

fun detailBoosterBlock(item: Boosters?):Spanned {
    val sb = StringBuilder()

    item?.let {
        if (item.block == null){
            sb.apply {
                append("-")
            }
        }else{
            sb.apply {
                append(item.block)
            }
        }
    }
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        return Html.fromHtml(sb.toString(), Html.FROM_HTML_MODE_LEGACY)
    } else {
        return HtmlCompat.fromHtml(sb.toString(), HtmlCompat.FROM_HTML_MODE_LEGACY)
    }
}

fun detailBoosterLaunchDate(item: Boosters?):Spanned {

    val sb = StringBuilder()
    item?.let {
        val date = (item.original_launch ?: "NONE")

        if (date != "NONE"){
            val year = date.subSequence(0,4)
            val month = date.subSequence(5,7)
            val day = date.subSequence(8,10)

            val hour = date.subSequence(11,13)
            val min = date.subSequence(14,16)
                sb.apply {
                    append("$day.$month.$year  $hour:$min")
                }
        }else{
                sb.apply {
                    append("NONE")
                }
        }
    }

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        return Html.fromHtml(sb.toString(), Html.FROM_HTML_MODE_LEGACY)
    } else {
        return HtmlCompat.fromHtml(sb.toString(), HtmlCompat.FROM_HTML_MODE_LEGACY)
    }
}

fun detailBoosterDetails(item: Boosters?):Spanned {
    val sb = StringBuilder()
    item?.let {
        if (item.details == null){
            sb.apply {
                append("-")
            }
        }else{
            sb.apply {
                append(item.details)
            }
        }
    }
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        return Html.fromHtml(sb.toString(), Html.FROM_HTML_MODE_LEGACY)
    } else {
        return HtmlCompat.fromHtml(sb.toString(), HtmlCompat.FROM_HTML_MODE_LEGACY)
    }
}