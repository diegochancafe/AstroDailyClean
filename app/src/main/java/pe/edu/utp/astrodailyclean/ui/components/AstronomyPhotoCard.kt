package pe.edu.utp.astrodailyclean.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import pe.edu.utp.astrodailyclean.domain.model.AstronomyPhoto


@Composable
fun AstronomyPhotoCard(
    modifier: Modifier,
    photo: AstronomyPhoto,
    titleStyle: TextStyle,
    dateStyle: TextStyle,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(24.dp))
            .clickable { onClick() }
    ) {

        // Image
        AsyncImage(
            model = photo.url,
            contentDescription = photo.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Gradient
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black.copy(alpha = 0.7f)
                        )
                    )
                )
        )

        // Photo title and date
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
        ) {
            Text(
                text = photo.title,
                style = titleStyle
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = photo.date,
                style = dateStyle
            )
        }
    }
}