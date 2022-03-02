package com.dvu.replitlite.formatter

import android.content.Context
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.util.Log
import androidx.core.content.ContextCompat
import com.dvu.replitlite.formatter.lang.Lang
import com.dvu.replitlite.formatter.lang.LangPython

object CodeHighlighter {

    fun highlightPython(context: Context, code: CharSequence): CharSequence {
        return highlight(context, code, LangPython)
    }

    private fun highlight(context: Context, code: CharSequence, lang: Lang): CharSequence {
        val builder = SpannableStringBuilder(code).apply {
            clearSpans()
        }
        lang.langConfig.forEach { entry ->
            val regex = entry.key
            val color = ContextCompat.getColor(context, entry.value)

            regex.findAll(code).forEach { matchResult ->
                val range = matchResult.range
                builder.setSpan(
                    ForegroundColorSpan(color),
                    range.first,
                    range.last + 1,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
        }

        return builder
    }
}