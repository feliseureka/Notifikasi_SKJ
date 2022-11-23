package com.skj.notifskj

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
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
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.skj.notifskj.ui.theme.NotifSKJTheme
import java.io.DataInputStream
import java.io.IOException
import java.net.Socket


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
                    ClientScreen()
                }
            }
        }
        createNotificationChannel()
        //sendNotification()
    }

    fun createNotification(title: String, desc: String) {
        createNotificationChannel()
        sendNotification(title, desc)
    }

    private fun createNotificationChannel() {
        val name = "Notification Title"
        val desc = "Notification Description"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel("01", name, importance).apply {
            description = desc
        }
        val notificationManager: NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    private fun sendNotification(title: String, desc: String) {
        val intent = Intent(this, ClientActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        }
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
        val builder =
            NotificationCompat.Builder(this, "01").setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(title)
                .setContentText(desc)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
        with(NotificationManagerCompat.from(this)) {
            notify(101, builder.build())
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClientScreen() {
    var serverIpAddress by rememberSaveable {
        mutableStateOf("")
    }

    val ctx = LocalContext.current as ClientActivity

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
                text = "You're a client",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
        Text(
            text = "Make sure you're connected in the same Local Network as the server",
            textAlign = TextAlign.Justify
        )
        Column(
            Modifier
                .fillMaxWidth()
                .padding(30.dp)
                .wrapContentSize(Alignment.Center)
        ) {
            Text(text = "Input IP from a server")
            TextField(
                value = serverIpAddress,
                onValueChange = {
                    serverIpAddress = it
                },
                label = { Text("Token") }
            )
            Button({
                try {
                    val socket = Socket(serverIpAddress, 3000)

                    val inputStream = DataInputStream(socket.getInputStream())

                    val bufferedReader = inputStream.bufferedReader()

                    val title = bufferedReader.readLine()
                    val desc = bufferedReader.readLine()

                    ctx.createNotification(title, desc)

                    inputStream.close()
                    socket.close()
                } catch (_: IOException) {}
            }) {
                Text(text = "Start Communicating")
            }
        }
        Text(text = "You're not connected to any Server!", textAlign = TextAlign.Center)
    }
}