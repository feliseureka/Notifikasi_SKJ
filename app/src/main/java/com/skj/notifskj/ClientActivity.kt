package com.skj.notifskj

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.skj.notifskj.ui.theme.NotifSKJTheme

class ClientActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotifSKJTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PageMainLayout3()
                }
            }
        }
    }
}

@Composable
fun PageMainLayout3() {
    Column(Modifier.width(IntrinsicSize.Max)) {
        Text(text = "You're a client")
        Text(text = "Input token from a server")
        //Input Token
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Start Communicating")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview3() {
    NotifSKJTheme {
        PageMainLayout3()
    }
}