package com.example.dialercompose

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.example.dialercompose.ui.theme.DialerComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DialerScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialerScreen() {
    //hold the entered phone number
    var phoneNumber by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Text field to display the entered number
        TextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Create buttons for numbers (1-9)
        for (i in 1..3) {
            Row(horizontalArrangement = Arrangement.Center) {
                for (j in (i - 1) * 3 + 1 until i * 3 + 1) {
                    NumberButton(j) {
                        phoneNumber += it.toString()
                    }
                }
            }
        }
    // Row for 0 and dial button
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        )    {
            NumberButton(0) {
                phoneNumber += it.toString()
            }
            Spacer(modifier = Modifier.width(16.dp))
            DialButton(phoneNumber)
        }
    }
}

@Composable
fun NumberButton(number: Int, onNumberClick: (Int) -> Unit) {
    // Button to enter a number
    Button(
        onClick = {
            //Add the clicked number to the phone number string
            onNumberClick(number)
        },
        modifier = Modifier.size(50.dp),
    ) {
        // what is displayed on the button in the screen
        Text(
            text= number.toString(),
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}

@Composable
fun DialButton(phoneNumber: String) {
    // button to initiate the phone call
    Button(
        onClick = {
//            // use intent to initiate a phone call
//            val intent = Intent(Intent.ACTION_DIAL).apply {
//                data = Uri.parse("tel:$phoneNumber")
//            }
//            if (intent.resolveActivity(packageManager) != null) {
//                startActivity(intent)
//            }
        },
        modifier = Modifier.size(50.dp),
    ){
        Text(text = "Dial")
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DialerScreen()
}