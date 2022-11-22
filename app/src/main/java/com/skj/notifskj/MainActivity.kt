package com.skj.notifskj

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .padding(30.dp)
                            .wrapContentSize(Alignment.Center)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentSize(Alignment.Center)
                                .padding(vertical = 30.dp)
                        ) {
                            Text(
                                text = "Notifikasi 4 SKJ",
                                fontSize = 30.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                        }
                        Box(
                            Modifier
                                .fillMaxWidth()
                                .wrapContentSize(Alignment.Center)
                                .padding(vertical = 10.dp)
                        ) {
                            Button(onClick = {
                                startActivity(
                                    Intent(
                                        this@MainActivity,
                                        ServerActivity::class.java
                                    )
                                )
                            }) {
                                Text(text = "Start as Server")
                            }
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentSize(Alignment.Center)
                        ) {
                            Button(onClick = {
                                startActivity(
                                    Intent(
                                        this@MainActivity,
                                        ClientActivity::class.java
                                    )
                                )
                            }) {
                                Text(text = "Start as Client")
                            }
                        }
                    }
                }
            }
        }
    }
}