package com.mart.composeproduct.ui.common

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SimpleButton(text: String, onClick: () -> Unit) {
    Button(onClick = onClick, modifier = Modifier.padding(top = 16.dp)) {
        Text(text, Modifier.padding(horizontal = 32.dp))
    }
}