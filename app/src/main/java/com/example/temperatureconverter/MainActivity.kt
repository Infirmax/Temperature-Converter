package com.example.temperatureconverter

import android.os.Bundle
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.temperatureconverter.ui.theme.TemperatureConverterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val seekBarC = findViewById<SeekBar>(R.id.seekBarC)
        val textViewC = findViewById<TextView>(R.id.celsiusNum)
        val seekBarF = findViewById<SeekBar>(R.id.seekBarF)
        val textViewF = findViewById<TextView>(R.id.fahrenheitNum)

        seekBarC.progress = 0
        seekBarF.progress = 32
        seekBarC.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // Update the TextView with the current progress
                textViewF.text = (progress * 9/5 + 32).toString()
                textViewC.text = progress.toString()
                val fahrenheit = (progress * 9 / 5) + 32
                seekBarF.progress = fahrenheit

                if (progress in 0..20) {
                    // Display a message when progress falls within the interval
                    Toast.makeText(applicationContext, "I wish it was warmer", Toast.LENGTH_SHORT).show()
                } else if (progress in 80..100) {
                    // Display another message for a different interval
                    Toast.makeText(applicationContext, "I wish it was colder", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        seekBarF.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                seekBar: SeekBar?,
                progress: Int,
                fromUser: Boolean
            ) {
                // Update the TextView with the current progress
                val celsius = ((progress - 32) * 5 / 9)
                textViewC.text = celsius.toString()
                textViewF.text = progress.toString()
                seekBarC.progress = celsius
                if (progress < 32) {
                    seekBarF.progress = 32
                }
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TemperatureConverterTheme {
        Greeting("Android")
    }
}