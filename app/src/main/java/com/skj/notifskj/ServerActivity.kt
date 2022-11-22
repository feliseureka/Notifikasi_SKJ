package com.skj.notifskj

import android.content.Context
import android.net.ConnectivityManager
import android.net.LinkProperties
import android.net.wifi.WifiManager
import android.os.Bundle
import android.text.format.Formatter
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import com.skj.notifskj.ui.theme.NotifSKJTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext

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
fun CreateEditText(label: String){
    var text by rememberSaveable { mutableStateOf("Text") }

    TextField(
        value = text,
        onValueChange = {
            text = it
        },
        label = { Text(label) }
    )
}

@Composable
fun PageMainLayout2() {
    Column(Modifier.width(IntrinsicSize.Max)) {
        Text(text = "You are a server")
        Text(text = "Make sure you're connected in the same Local Network as the clients")
        Text(text = "Send this code to your clients")
        val connectivityManager = LocalContext.current.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var link = connectivityManager.getLinkProperties(connectivityManager.activeNetwork) as LinkProperties
        val ip = link.linkAddresses[0].address.hostAddress
        if (ip != null) {
            Text("Code : $ip")
        }
        Text("Clients Logged : ")
        Text("Send Notification")
        CreateEditText(label = "Title")
        CreateEditText(label = "Description")
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Send Notification")
        }
    }
}