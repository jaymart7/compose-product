package com.mart.composeproduct.ui.common

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

open class TextFieldState {
    var text by mutableStateOf("")
}

