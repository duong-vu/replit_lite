package com.dvu.replitlite.formatter.lang

import androidx.annotation.ColorRes
import com.dvu.replitlite.R

private const val PY_KEYWORD_PATTERN =
    "\\b(await|else|import|pass|break|except|in|raise|class|finally|is|return|and|continue|for|lambda|try|as|def|from|nonlocal|while|assert|del|global|not|with|async|elif|if|or|yield)\\b"

private const val PY_FUNCTION_PATTERN = "(?<=[\\s|\\.])([a-zA-Z]+[a-zA-Z_0-9]*)(?=\\()|^([a-zA-Z]+[a-zA-Z_0-9]*)(?=\\()"

private const val PY_VALUE_PATTERN = "(?<=[\b|\\s])(True|False|None)(?=[\b|\\s])"

object LangPython : Lang {

    override val langConfig by lazy {
        mapOf<Regex, @ColorRes Int>(
            Regex(PY_FUNCTION_PATTERN) to R.color.function_highlight,
            Regex(PY_KEYWORD_PATTERN) to R.color.keyword_highlight,
            Regex(PY_VALUE_PATTERN) to R.color.value_highlight
        )
    }
}