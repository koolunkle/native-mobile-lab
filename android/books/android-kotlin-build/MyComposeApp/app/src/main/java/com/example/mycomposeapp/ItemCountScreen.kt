package com.example.mycomposeapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.example.mycomposeapp.ui.theme.OnBackgroundTitleText
import com.example.mycomposeapp.ui.theme.PrimaryTextButton

data class ItemCountScreenState(
    val itemCount: String = ""
)

@Composable
fun ItemCountScreen(onButtonClick: (String) -> Unit) {
    var state by remember { mutableStateOf(ItemCountScreenState()) }
    ItemCountScreenContent(
        itemCountScreenState = state,
        onItemCountChange = {
            state = state.copy(itemCount = it)
        },
        onButtonClick = {
            onButtonClick(state.itemCount)
        }
    )
}

@Composable
fun ItemCountScreenContent(
    itemCountScreenState: ItemCountScreenState,
    onItemCountChange: (String) -> Unit,
    onButtonClick: () -> Unit
) {
    Column {
        OnBackgroundTitleText(text = stringResource(id = R.string.enter_number))
        TextField(
            value = itemCountScreenState.itemCount,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            onValueChange = onItemCountChange
        )
        PrimaryTextButton(
            text = stringResource(id = R.string.click_me),
            onClick = onButtonClick
        )
    }
}