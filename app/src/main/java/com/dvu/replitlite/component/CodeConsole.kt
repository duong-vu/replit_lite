package com.dvu.replitlite.component

import android.content.Context
import android.text.SpannableStringBuilder
import android.text.method.ScrollingMovementMethod
import android.util.AttributeSet
import android.view.Gravity
import androidx.annotation.AttrRes
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.dvu.replitlite.R

class CodeConsole @JvmOverloads
constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = android.R.attr.textViewStyle
) : AppCompatTextView(context, attributeSet, defStyleAttr) {

    init {
        setTextColor(ContextCompat.getColor(context, R.color.code_default_color))
        typeface = ResourcesCompat.getFont(context, R.font.consola)

        gravity = Gravity.BOTTOM
        movementMethod = ScrollingMovementMethod()
    }

    fun clear() {
        text = null
    }

    fun print(output: CharSequence) {
        val builder = SpannableStringBuilder(text)
        builder.append(output)
        builder.append("\n")
        text = builder
    }
}