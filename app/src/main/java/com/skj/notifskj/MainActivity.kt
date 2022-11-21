package com.skj.notifskj

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.skj.notifskj.ui.theme.NotifSKJTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotifSKJTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PageMainLayout()
                }
            }
        }
    }
}

@Composable
fun PageMainLayout() {
    Column(Modifier.width(IntrinsicSize.Max)) {
        Text(text = "Notifikasi 4 SKJ uwu")
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Start as Server")
        }
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Start as Client")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NotifSKJTheme {
        PageMainLayout()
    }
}