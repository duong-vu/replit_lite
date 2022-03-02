package com.dvu.replitlite.component

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.dvu.replitlite.R

class CodeHelperKeyboardAdapter(private val onKeyTapped: ((CodeHelperKey) -> Unit)?) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val keys = mutableListOf<CodeHelperKey>()

    fun useKeys(keys: List<CodeHelperKey>) {
        this.keys.apply {
            clear()
            addAll(keys)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.code_helper_keyboard_item, parent, false)
        return KeyViewHolder(layout)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as KeyViewHolder).bindKey(keys[position])
    }

    override fun getItemCount(): Int {
        return keys.size
    }

    private inner class KeyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindKey(key: CodeHelperKey) {
            (itemView as Button).text = key.displayName
            itemView.setOnClickListener { onKeyTapped?.invoke(key) }
        }
    }
}