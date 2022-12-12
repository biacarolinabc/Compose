package com.example.lio_pam

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lio_pam.SampleData.conversationSample
import com.example.lio_pam.ui.theme.Lição_pamTheme
import androidx.compose.material3.Surface as Surface1

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            Lição_pamTheme() {

                Conversation(SampleData.conversationSample)
            }
        }
    }
}

data class Message(val author: String, val body: String)


@Composable
fun Conversation(messages: List<Message>){
    LazyColumn{

        items(messages){ message ->
            MessageCard(message)
        }
    }
}

@Composable
fun MessageCard(msg: Message){
    Row (modifier = Modifier.padding(all = 8.dp)){

        Image(
            painter = painterResource(id = R.drawable.profile_picture),

            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape)

        )

        Spacer(modifier = Modifier.width(8.dp))

        var isExpanded by remember{ mutableStateOf(false) }

        val surfaceColor by animateColorAsState(if(isExpanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary)

        Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {

            Text(
                text = msg.author,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleSmall
            )

            Spacer(modifier = Modifier.height(4.dp))

            Surface1(shadowElevation = 1.dp, color = surfaceColor) {

                Text(
                    text = msg.body,
                    modifier = Modifier.padding(all = 4.dp),
                    maxLines = if(isExpanded) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewConversation(){
    Lição_pamTheme(darkTheme = true) {
        Conversation(conversationSample)
    }
}

@Preview
@Composable
fun PreviewMessageCard(){
    Lição_pamTheme(darkTheme = true) {
        Surface1 {
            MessageCard(msg = Message("Lexi", "Hey, take a look at Jetpack Compose, it's great!"))
        }
    }
}