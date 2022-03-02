package com.dvu.replitlite.keyboard

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class KeyboardVisibilityViewModel : ViewModel() {

    private val _isKeyboardVisible = MutableStateFlow(false)
    val isKeyboardVisible: StateFlow<Boolean>
        get() = _isKeyboardVisible

    fun onKeyboardVisibilityChanged(isVisible: Boolean) {
        _isKeyboardVisible.value = isVisible
    }
}