package com.belajarubic.mystatemanagement

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.belajarubic.mystatemanagement.ui.theme.MyStateManagementTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyStateManagementTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Scaffold {paddingContent ->
                        MyForm(
                            modifier = Modifier.padding(paddingContent)
                        )
                    }
                }
            }
        }
    }
}

class FormInputState(initialInput: String) {
    var input by mutableStateOf(initialInput)
}

@Composable
fun rememberFormInputState(input: String): FormInputState =
    remember(input) {
        FormInputState(input)
    }

@Composable
fun MyForm(
    modifier: Modifier = Modifier,
) {
    val input = rememberFormInputState("")
    FormInput(
        state = input,
        modifier = modifier,
    )
}

@Composable
fun FormInput(
    modifier: Modifier = Modifier,
    state: FormInputState = rememberFormInputState(""),
) {
    Column(modifier = modifier) {
        OutlinedTextField(
            value = state.input,
            onValueChange = { state.input = it },
            label = { Text("Nama") },
            modifier = Modifier.padding(8.dp).fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(state.input, modifier = Modifier.align(Alignment.CenterHorizontally))
    }
}

@Preview(showBackground = true)
@Composable
fun FormInputPreview() {
    MyForm()
}