package com.skj.notifskj

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.skj.notifskj.ui.theme.NotifSKJTheme

class ServerActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotifSKJTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PageMainLayout2()
                }
            }
        }
    }
}

@Composable
fun PageMainLayout2() {
    Column(Modifier.width(IntrinsicSize.Max)) {
        Text(text = "You are a server")
        Text(text = "Send this code to your clients")
        //Get ip and change to other e.g. base64
        //Log of clients
        Text(text = "Notification Title")
        //Input
        Text(text = "Notification Description")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    NotifSKJTheme {
        PageMainLayout2()
    }
}