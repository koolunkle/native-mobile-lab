package com.example.mycomposeapp.ui.theme

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun OnBackgroundTitleText(text: String) {
    TitleText(
        text = text,
        color = MaterialTheme.colorScheme.onBackground
    )
}

@Composable
fun TitleText(text: String, color: Color) {
    Text(
        text = text,
        style = MaterialTheme.typography.headlineMedium,
        color = color
    )
}

@Composable
fun OnBackgroundItemText(text: String) {
    ItemText(
        text = text,
        color = MaterialTheme.colorScheme.onBackground
    )
}

@Composable
fun ItemText(text: String, color: Color) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyLarge,
        color = color
    )
}

@Composable
fun PrimaryTextButton(text: String, onClick: () -> Unit) {
    TextButton(
        text = text,
        textColor = Color.Red,
        onClick = onClick
    )
}

@Composable
fun TextButton(text: String, textColor: Color, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(contentColor = textColor)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge
        )
    }
}