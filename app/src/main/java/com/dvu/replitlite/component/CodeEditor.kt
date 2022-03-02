package com.dvu.replitlite.component

import android.content.Context
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.AttributeSet
import androidx.annotation.AttrRes
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.dvu.replitlite.R
import com.dvu.replitlite.formatter.CodeHighlighter
import kotlin.math.max
import kotlin.math.min

private const val TAB_SIZE = 2

class CodeEditor @JvmOverloads
constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = android.R.attr.editTextStyle
) : AppCompatEditText(context, attributeSet, defStyleAttr) {

    init {
        setTextColor(ContextCompat.getColor(context, R.color.code_default_color))
        typeface = ResourcesCompat.getFont(context, R.font.consola)
        inputType =
            InputType.TYPE_CLASS_TEXT or
                    InputType.TYPE_TEXT_FLAG_MULTI_LINE or
                    InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS or
                    InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
        background = null

        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) =
                Unit

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit

            override fun afterTextChanged(s: Editable?) {
                s?.let {
                    removeTextChangedListener(this)
                    val cursorPosition = selectionStart
                    setText(CodeHighlighter.highlightPython(context, it))
                    setSelection(cursorPosition)
                    addTextChangedListener(this)
                }
            }
        })
    }

    fun onKeyTapped(key: CodeHelperKey) {
        val start = selectionStart.coerceAtLeast(0)
        val end = selectionEnd.coerceAtLeast(0)
        text?.let {
            val textToInsert = when (key) {
                is CharKey -> key.char.toString()
                TabKey -> " ".repeat(TAB_SIZE)
            }
            it.replace(
                min(start, end), max(start, end),
                textToInsert, 0, textToInsert.length
            )
        }
    }
}