package com.example.lemonade

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonadeApp()
            }
        }
    }
}

@Composable
fun LemonadeApp(modifier: Modifier = Modifier) {

    var clickCount by remember { mutableIntStateOf(0) }
    var squeezeTime = 1
    val randomTime = (2..4).random()

    val imageResource = when (clickCount) {
        0 -> R.drawable.lemon_tree
        1 -> R.drawable.lemon_squeeze
        2 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }

    val stringResources = when (clickCount) {
        0 -> R.string.lemon_content_description
        1 -> R.string.keep_tapping
        2 -> R.string.tap_lemonade
        else -> R.string.tap_empty_glass
    }

    val contentDescriptions = when (clickCount) {
        0 -> R.string.lemon_tree
        1 -> R.string.lemon
        2 -> R.string.glass_of_lemonade
        else -> R.string.empty_glass
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = {
            if (clickCount == 1) {
                if (squeezeTime != randomTime) {
                    clickCount = 1
                    squeezeTime++
                } else {
                    clickCount++
                    squeezeTime = 1
                }
            } else {
                clickCount++
                squeezeTime = 1
            }

            if ( clickCount > 3 ) {
                clickCount = 0
            }
        }) {
            Image(
                painter = painterResource(id = imageResource),
                contentDescription = contentDescriptions.toString(),
                modifier = modifier.border(4.dp, Color(105, 205, 216), shape = RoundedCornerShape(4.dp))
            )
        }
        Spacer(modifier = modifier.height(16.dp))
        Text(text = stringResource(id = stringResources), fontSize = 18.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadeAppPreview() {
    LemonadeApp()
}