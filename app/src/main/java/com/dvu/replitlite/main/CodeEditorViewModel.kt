package com.dvu.replitlite.main

import android.app.Application
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.dvu.replitlite.R
import com.dvu.replitlite.repository.intf.CodeRunnerRepository
import com.dvu.replitlite.repository.model.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class CodeEditorViewModel(
    application: Application,
    private val codeRunnerRepository: CodeRunnerRepository
) : AndroidViewModel(application) {

    private val newCodeAlertColor by lazy {
        ContextCompat.getColor(application, R.color.value_highlight)
    }

    sealed class Event {
        class NewCodeRunnerOutput(val output: CharSequence) : Event()
        object RunError : Event()
    }

    private val eventChannel = Channel<Event>()
    val eventFlow = eventChannel.receiveAsFlow()

    private val _loadingStateFlow = MutableStateFlow(false)
    val loadingStateFlow: StateFlow<Boolean>
        get() = _loadingStateFlow

    fun runCode(code: String, lineCount: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            eventChannel.send(Event.NewCodeRunnerOutput(getNewCodeRunAlertMessage(lineCount)))
            codeRunnerRepository.runCode(code).collect {
                _loadingStateFlow.value = it is Resource.Loading

                when (it) {
                    is Resource.Success -> eventChannel.send(Event.NewCodeRunnerOutput(it.data))
                    is Resource.Error -> eventChannel.send(Event.RunError)
                }
            }
        }
    }

    private fun getNewCodeRunAlertMessage(lineCount: Int): CharSequence {
        return SpannableStringBuilder(
            getApplication<Application>().getString(
                R.string.new_code_run_alert_message,
                lineCount
            )
        ).apply {
            setSpan(
                ForegroundColorSpan(newCodeAlertColor),
                0,
                length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
    }
}