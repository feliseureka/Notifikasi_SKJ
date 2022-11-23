package com.skj.notifskj

import android.content.Context
import android.net.ConnectivityManager
import android.net.LinkProperties
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skj.notifskj.ui.theme.NotifSKJTheme
import java.io.DataOutputStream
import java.io.IOException
import java.net.ServerSocket

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
                    ServerScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ServerScreen() {
    var title by rememberSaveable {
        mutableStateOf("")
    }
    var desc by rememberSaveable {
        mutableStateOf("")
    }

    Column(
        Modifier
            .fillMaxWidth()
            .padding(30.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 30.dp)
                .wrapContentSize(Alignment.Center)
        ) {
            Text(
                text = "You are a server",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
        Text(
            text = "Make sure you're connected in the same Local Network as the clients",
            textAlign = TextAlign.Justify
        )
        val connectivityManager =
            LocalContext.current.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val link =
            connectivityManager.getLinkProperties(connectivityManager.activeNetwork) as LinkProperties
        val ip = link.linkAddresses[0].address.hostAddress
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center)
                .padding(vertical = 10.dp)
        ) {
            Text(text = "Send this to your clients")
            if (ip != null) {
                Text("IP : $ip")
            } else {
                Text(text = "Connect to your Local Network")
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center)
                .padding(10.dp)
        ) {
            Text("Clients Logged : ")
        }
        Column(
            Modifier
                .padding(30.dp)
                .wrapContentSize(Alignment.Center)
                .fillMaxWidth()
        ) {
            Text(
                "Send Notification",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
            Box(modifier = Modifier.padding(20.dp)) {
                TextField(
                    value = title,
                    onValueChange = {
                        title = it
                    },
                    label = { Text("Title") }
                )
            }
            Box(modifier = Modifier.padding(20.dp)) {
                TextField(
                    value = desc,
                    onValueChange = {
                        desc = it
                    },
                    label = { Text("Description") }
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center)
            ) {
                Button({
                    Thread {
                        try {
                            val serverSocket = ServerSocket(3000)

                            val socket = serverSocket.accept()

                            val outputStream = DataOutputStream(socket.getOutputStream())

                            val bufferedWriter = outputStream.bufferedWriter()

                            bufferedWriter.appendLine(title)

                            bufferedWriter.appendLine(desc)

                            outputStream.close()

                            socket.close()
                        } catch (_: IOException) { }
                    }.start()

                    title = ""
                    desc = ""
                }) {
                    Text(text = "Send Notification")
                }
            }
        }
    }
}