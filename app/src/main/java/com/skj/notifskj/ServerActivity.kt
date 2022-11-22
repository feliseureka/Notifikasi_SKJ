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
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
    var text by rememberSaveable { mutableStateOf("") }

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
    Column(
        Modifier
            .fillMaxWidth()
            .padding(30.dp)) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 30.dp)
            .wrapContentSize(Alignment.Center)){
            Text(text = "You are a server", fontSize = 30.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
        }
        Text(text = "Make sure you're connected in the same Local Network as the clients", textAlign = TextAlign.Justify)
        val connectivityManager = LocalContext.current.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var link = connectivityManager.getLinkProperties(connectivityManager.activeNetwork) as LinkProperties
        val ip = link.linkAddresses[0].address.hostAddress
        Column(modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center).padding(vertical = 10.dp)){
            Text(text = "Send this to your clients")
            if (ip != null) {
                Text("IP : $ip")
            }else{
                Text(text = "Connect to your Local Network")
            }
        }
        Box(modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center)
            .padding(10.dp)){
            Text("Clients Logged : ")
        }
        Column(
            Modifier
                .padding(30.dp)
                .wrapContentSize(Alignment.Center)
                .fillMaxWidth()) {
            Text("Send Notification", fontWeight = FontWeight.Bold, fontSize = 20.sp, textAlign = TextAlign.Center)
            Box(modifier = Modifier.padding(20.dp)){
                CreateEditText(label = "Title")
            }
            Box(modifier = Modifier.padding(20.dp)){
                CreateEditText(label = "Description")
            }
            Box(modifier = Modifier.fillMaxWidth().wrapContentSize(Alignment.Center)){
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Send Notification")
                }
            }
        }
    }
}