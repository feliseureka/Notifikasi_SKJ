package com.skj.notifskj

import android.os.StrictMode
import android.widget.Toast
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
import androidx.navigation.NavController
import java.io.DataInputStream
import java.io.IOException
import java.net.Socket


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Client(navController: NavController) {
    var serverIpAddress by rememberSaveable {
        mutableStateOf("")
    }

    val ctx = LocalContext.current as MainActivity

    Scaffold { paddingValues ->
        Column(
            Modifier
                .fillMaxWidth()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(30.dp)
                    .wrapContentSize(Alignment.Center)
            ) {
                Text(
                    text = "You're a client",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
            Text(
                "Make sure you're connected in the same Local Network as the server",
                Modifier.fillMaxWidth(0.9f),
                textAlign = TextAlign.Justify
            )
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(30.dp)
                    .wrapContentSize(Alignment.Center)
            ) {
                Text(text = "Input IP from a server")
                TextField(value = serverIpAddress, onValueChange = {
                    serverIpAddress = it
                }, label = { Text("Token") })
                Button({
                    var isSuccess = false

                    val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
                    StrictMode.setThreadPolicy(policy)
                    val t = Thread {
                        try {
                            val socketClient = Socket(serverIpAddress, 3000)

                            if (!socketClient.isConnected) {
                                Thread.currentThread().interrupt()
                            }

                            isSuccess = true

                            val inputStream = DataInputStream(socketClient.getInputStream())

                            val bufferedReader = inputStream.bufferedReader()

                            val title = bufferedReader.readLine()
                            val desc = bufferedReader.readLine()

                            ctx.createNotification(title, desc)


                            inputStream.close()
                            socketClient.close()
                        } catch (_: IOException) {
                        }

                    }
                    t.start()
                    t.join()

                    if (!isSuccess) {
                        Toast.makeText(ctx, "Failed to Connect", Toast.LENGTH_SHORT).show()
                        isSuccess = false
                    }

                    navController.navigate("home")
                }) {
                    Text("Start Communicating")
                }
            }
            Text(text = "You're not connected to any Server!", textAlign = TextAlign.Center)
        }
    }
}