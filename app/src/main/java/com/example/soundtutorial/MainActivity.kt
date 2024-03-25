package com.example.soundtutorial

import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.soundtutorial.ui.theme.SoundTutorialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SoundTutorialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SoundButton(context = applicationContext)
                }
            }
        }
    }
}

@Composable
fun SoundButton(context: Context) {
    val mediaPlayer = MediaPlayer.create(context, R.raw.gameover)
    Button(
        onClick = {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.seekTo(0)
            } else {
                mediaPlayer.start()
            }
        },
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(60.dp),
        colors = ButtonDefaults.buttonColors(),
        contentPadding = PaddingValues(12.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Text(
            text = "Play Sound",
            color = Color.White,
            fontSize = 18.sp,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
    }
    DisposableEffect(Unit) {
        onDispose {
            mediaPlayer.release()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SoundButtonPreview() {
    SoundTutorialTheme {
        Surface {
            SoundButton(context = LocalContext.current)
        }
    }
}