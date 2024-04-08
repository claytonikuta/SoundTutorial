

[YouTube Video - WRwOT70VpCI](https://www.youtube.com/watch?v=WRwOT70VpCI)(YouTube video for future review).

### Step 1: Create a New Kotlin Project
Create a new empty activity. Name it SoundTutorial (if you name it something else change the SoundTutorialTheme to (your chosen name)Theme.

### Step 2: Import Audio File
[﻿drive.google.com/file/d/1Pqi4yzn0s3wNJ4UjveBsbUw4ZUh6ODS2/view?usp=sharing](https://drive.google.com/file/d/1Pqi4yzn0s3wNJ4UjveBsbUw4ZUh6ODS2/view?usp=sharing) 

Download the above file.

Add a new directory to the res directory and name it raw. Place `gameover.wav` in the `res/raw` directory of your project.

### Step 3: Import required libraries
In the MainAcitivity.kt:

```kotlin
import android.content.Context
import android.media.MediaPlayer
import androidx.compose.material3.Button
```
### Step 4: Create the SoundButton Composable function
```kotlin
@Composable
fun SoundButton(context: Context) {
    
}
```
### Step 5: Initialize MediaPlayer
Add this to your SoundButton composable function.

```kotlin
val mediaPlayer = MediaPlayer.create(context, R.raw.gameover) 
```
### Step 6: Create a Button
Create a button inside the SoundButton composable function.

```kotlin
Button(
    onClick = { mediaPlayer.start() },
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
```
Add the below imports if they don't auto import. (Color will tell you there is multiple imports use the below import or choose (Color (androidx.compose.ui.graphics)).

```kotlin
import androidx.compose.foundation.layout.PaddingValues 
import androidx.compose.foundation.layout.fillMaxWidth 
import androidx.compose.foundation.layout.height 
import androidx.compose.foundation.layout.padding 
import androidx.compose.material3.ButtonDefaults 
import androidx.compose.runtime.DisposableEffect 
import androidx.compose.ui.platform.LocalContext 
import androidx.compose.ui.unit.dp 
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
```
### Step 7: Modify the onClick Function
This ensures if the button is pressed while the sound is playing it will restart the sound.

```kotlin
onClick = {
    if (mediaPlayer.isPlaying) {
        mediaPlayer.seekTo(0)
    } else {
        mediaPlayer.start()
    }
},
```
### Step 8: Add Cleanup
Place this inside the button under the other code to cleanup the resources. 

```kotlin
DisposableEffect(Unit) {
    onDispose {
        mediaPlayer.release()
    }
}
```
### Step 9: Create a Preview
```kotlin
@Preview(showBackground = true)
@Composable
fun SoundButtonPreview() {
    SoundTutorialTheme {
        Surface {
            SoundButton(context = LocalContext.current)
        }
    }
}
```
### Step 10: Set Content in MainActivity
```kotlin
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
```
### Step 11: Test it
Launch the app and make sure it is playing correctly



### Other Notes
Sound effect was found for free here:

[﻿mixkit.co/free-sound-effects/](https://mixkit.co/free-sound-effects/) 

Altogether the code should look like this.

```kotlin
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
```


