package pe.edu.utp.astrodailyclean.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HeaderSection() {
    Column(modifier = Modifier.padding(20.dp)) {
        Text(
            text = "Explore the",
            style = TextStyle(
                fontSize = 42.sp
            )
        )

        Text(
            text = "Universe 🚀",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 42.sp
            )
        )

        Spacer(modifier = Modifier.height(18.dp))

        Text(
            text = "Discover NASA’s Astronomy Picture of the Day — a curated collection of the most fascinating images of our universe, explained by astronomers.",
            color = Color.Gray,
            style = TextStyle(
                fontSize = 24.sp
            )
        )
    }
}