package pe.edu.utp.astrodailyclean.presentation.astronomy.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pe.edu.utp.astrodailyclean.domain.model.AstronomyPhoto
import pe.edu.utp.astrodailyclean.ui.components.AstronomyPhotoCard

@Composable
fun AstronomyDetailScreen(
    photo: AstronomyPhoto,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Box {
            AstronomyPhotoCard(
                modifier = Modifier.height(450.dp),
                titleStyle = TextStyle.Default.copy(
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                ),
                dateStyle = MaterialTheme.typography.bodySmall.copy(
                    color = Color.LightGray,
                    fontSize = 18.sp
                ),
                photo = photo,
            )

            IconButton(
                onClick = onBack,
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.TopStart)
                    .background(Color.Black.copy(0.3f), shape = CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = "About this image",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = photo.explanation
            )
        }

    }

}