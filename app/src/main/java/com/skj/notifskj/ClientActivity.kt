package com.skj.notifskj

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
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
        createNotificationChannel()
        //sendNotification()
    }

    fun createNotification(title: String, desc: String){
        createNotificationChannel()
        sendNotification(title, desc)
    }
    private fun createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name = "Notification Title"
            val desc = "Notification Description"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("01", name, importance).apply {
                description = desc
            }
            val notificationManager:NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun sendNotification(title: String, desc: String){
        val intent = Intent(this, ClientActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
        val builder = NotificationCompat.Builder(this, "01").setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(title)
            .setContentText(desc)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
        with(NotificationManagerCompat.from(this)){
            notify(101, builder.build())
        }
    }
}

@Composable
fun PageMainLayout3() {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(30.dp)
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 30.dp)
            .wrapContentSize(Alignment.Center)){
            Text(text = "You're a client", fontSize = 30.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
        }
        Text(text = "Make sure you're connected in the same Local Network as the server", textAlign = TextAlign.Justify)
        Column(
            Modifier
                .fillMaxWidth()
                .padding(30.dp)
                .wrapContentSize(Alignment.Center)) {
            Text(text = "Input IP from a server")
            CreateEditText(label = "Token")
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Start Communicating")
            }
        }
        Text(text = "You're not connected to any Server!", textAlign = TextAlign.Center)
    }
}