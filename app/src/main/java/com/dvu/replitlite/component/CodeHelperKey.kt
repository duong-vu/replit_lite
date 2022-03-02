package com.dvu.replitlite.component

sealed class CodeHelperKey(val displayName: String)

object TabKey : CodeHelperKey("tab")

class CharKey(val char: Char): CodeHelperKey(char.toString())