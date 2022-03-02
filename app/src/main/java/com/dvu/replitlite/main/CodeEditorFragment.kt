package com.dvu.replitlite.main

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.dvu.replitlite.R
import com.dvu.replitlite.keyboard.KeyboardVisibilityViewModel
import com.dvu.replitlite.util.collect
import com.dvu.replitlite.util.hideKeyboard
import kotlinx.android.synthetic.main.code_editor_fragment.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CodeEditorFragment : Fragment(R.layout.code_editor_fragment) {

    private val codeEditorViewModel: CodeEditorViewModel by viewModel()

    private val keyboardVisibilityViewModel: KeyboardVisibilityViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        collect(codeEditorViewModel.eventFlow) {
            when (it) {
                is CodeEditorViewModel.Event.NewCodeRunnerOutput -> output_window.print(it.output)
                is CodeEditorViewModel.Event.RunError -> {
                    Toast.makeText(
                        context,
                        R.string.error_message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        collect(keyboardVisibilityViewModel.isKeyboardVisible) {
            if (it) {
                output_container.visibility = View.GONE
                helper_keyboard.visibility = View.VISIBLE
            } else {
                output_container.visibility = View.VISIBLE
                helper_keyboard.visibility = View.GONE
            }
        }

        collect(codeEditorViewModel.loadingStateFlow) {
            run_button.isEnabled = !it
            if (it) {
                run_button.text = getString(R.string.running_label)
            } else {
                run_button.text = getString(R.string.run_label)
            }
        }

        run_button.setOnClickListener {
            val code = code_editor.text
            if (code != null && code.isNotBlank()) {
                it.hideKeyboard()
                codeEditorViewModel.runCode(code.toString(), code_editor.lineCount)
            }
        }

        clear_button.setOnClickListener {
            output_window.clear()
        }

        helper_keyboard.onKeyTappedListener = {
            code_editor.onKeyTapped(it)
        }
    }
}