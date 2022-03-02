package com.dvu.replitlite

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import androidx.appcompat.app.AppCompatActivity
import com.dvu.replitlite.keyboard.KeyboardVisibilityViewModel
import com.dvu.replitlite.main.CodeEditorFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private val keyboardVisibilityViewModel: KeyboardVisibilityViewModel by viewModel()

    private lateinit var rootLayout: ViewGroup
    private var keyboardListenersAttached = false

    private val keyboardLayoutListener = OnGlobalLayoutListener {
        var navigationBarHeight = 0
        var resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android")
        if (resourceId > 0) {
            navigationBarHeight = resources.getDimensionPixelSize(resourceId)
        }

        // status bar height
        var statusBarHeight = 0
        resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            statusBarHeight = resources.getDimensionPixelSize(resourceId)
        }

        // display window size for the app layout
        val rect = Rect()
        window.decorView.getWindowVisibleDisplayFrame(rect)

        // screen height - (user app height + status + nav). if non-zero, then there is a soft keyboard
        val keyboardHeight =
            rootLayout.rootView.height - (statusBarHeight + navigationBarHeight + rect.height())

        keyboardVisibilityViewModel.onKeyboardVisibilityChanged(keyboardHeight > 0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, CodeEditorFragment()).commit()

        attachKeyboardListener()
    }

    private fun attachKeyboardListener() {
        if (keyboardListenersAttached) {
            return
        }
        rootLayout = findViewById<View>(R.id.root_layout) as ViewGroup
        rootLayout.viewTreeObserver.addOnGlobalLayoutListener(keyboardLayoutListener)
        keyboardListenersAttached = true
    }

    override fun onDestroy() {
        super.onDestroy()
        if (keyboardListenersAttached) {
            rootLayout.viewTreeObserver.removeOnGlobalLayoutListener(keyboardLayoutListener)
        }
    }
}