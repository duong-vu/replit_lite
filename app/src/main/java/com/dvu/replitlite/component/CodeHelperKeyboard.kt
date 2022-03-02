package com.dvu.replitlite.component

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.AttrRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private val DEFAULT_HELPER_KEYS = listOf(
    TabKey,
    CharKey(':'),
    CharKey('('),
    CharKey(')'),
    CharKey('\''),
    CharKey('\"'),
)

class CodeHelperKeyboard @JvmOverloads
constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : RecyclerView(context, attributeSet, defStyleAttr) {

    var onKeyTappedListener: ((CodeHelperKey) -> Unit)? = null

    private val keyboardAdapter = CodeHelperKeyboardAdapter {
        onKeyTappedListener?.invoke(it)
    }

    init {
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        adapter = keyboardAdapter

        keyboardAdapter.useKeys(DEFAULT_HELPER_KEYS)
    }
}