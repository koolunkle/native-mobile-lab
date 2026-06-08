package com.example.mycomposeapp

import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mycomposeapp.ui.theme.MyComposeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyComposeAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    /*Surface(color = MaterialTheme.colorScheme.background) {
                        /*Greeting(
                            name = "Android",
                            modifier = Modifier.padding(innerPadding)
                        )*//*val items = (1..100).toList().map {
                            stringResource(
                                id = R.string.item_format, formatArgs = arrayOf("$it")
                            )
                        }*/

                        // MyScreen(contentPadding = innerPadding)

                        /*val navController = rememberNavController()
                        Column(modifier = Modifier.padding(16.dp)) {
                            MyApp(navController)
                        }*/

                        // MyCustomisedElement("Hello, world!")
                    }*/

                    val navController = rememberNavController()
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .padding(horizontal = 16.dp)
                    ) {
                        MyApp(navController)
                    }
                }
            }
        }
    }
}

@Composable
fun MyApp(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "itemCountScreen"
    ) {
        composable("itemCountScreen") {
            ItemCountScreen {
                navController.navigate("itemScreen/?itemCount=$it")
            }
        }
        composable(
            "itemScreen/?itemCount={itemCount}",
            arguments = listOf(navArgument("itemCount") {
                type = NavType.StringType
            })
        ) {
            ItemScreen(it.arguments?.getString("itemCount").orEmpty())
        }
    }
}

@Composable
fun MyCustomisedElement(text: String) {
    AndroidView(factory = { context ->
        TextView(context).apply {
            this.text = text
        }
    })
}

@Composable
fun MyScreen(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    var state by remember { mutableStateOf(MyScreenState()) }
    MyScreenContent(
        myScreenState = state,
        onItemCountChange = { state = state.copy(itemCount = it) },
        onButtonClick = {
            val itemCount = state.itemCount.toIntOrNull() ?: 0
            val newItems = (1..itemCount).toList().map { "$it" }
            state = state.copy(items = newItems)
        },
        modifier = modifier,
        contentPadding = contentPadding
    )
}

@Composable
fun MyScreenContent(
    myScreenState: MyScreenState,
    onItemCountChange: (String) -> Unit,
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(), contentPadding = contentPadding
    ) {
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = stringResource(id = R.string.enter_number))
                TextField(
                    value = myScreenState.itemCount,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    onValueChange = onItemCountChange,
                    modifier = Modifier.fillMaxWidth()
                )
                Button(
                    onClick = onButtonClick, modifier = Modifier.padding(top = 8.dp)
                ) {
                    Text(text = stringResource(id = R.string.click_me))
                }
            }
        }
        items(myScreenState.items) { item ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 4.dp)
            ) {
                Text(text = stringResource(id = R.string.item_format, item))
            }
        }
    }
}

@Composable
fun OnBackgroundParagraphText(text: String) {
    ParagraphText(
        text = text, color = MaterialTheme.colorScheme.onBackground
    )
}

@Composable
fun ParagraphText(text: String, color: Color) {
    Text(
        text = text, style = MaterialTheme.typography.bodySmall, color = color
    )
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!", modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyComposeAppTheme {
        Greeting("Android")
    }
}