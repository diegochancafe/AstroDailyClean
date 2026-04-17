package pe.edu.utp.astrodailyclean.presentation.astronomy.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import pe.edu.utp.astrodailyclean.domain.model.AstronomyPhoto
import pe.edu.utp.astrodailyclean.ui.components.AstronomyPhotoCard
import pe.edu.utp.astrodailyclean.ui.components.HeaderSection

@Composable
fun AstronomyListScreen(
    modifier: Modifier = Modifier,
    onNavigateToDetail: (AstronomyPhoto) -> Unit = {},
    viewModel: AstronomyListViewModel = hiltViewModel(),
) {
    val photos by viewModel.photos.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    Column(modifier = modifier.fillMaxSize()) {
        when {
            isLoading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            }

            error != null -> {
                Text(
                    text = error ?: "Error desconocido",
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }

            else -> {
                Column {
                    Spacer(modifier = Modifier.height(12.dp))
                    HeaderSection()
                    Spacer(modifier = Modifier.height(18.dp))
                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(photos) { photo ->
                            AstronomyPhotoCard(
                                modifier = Modifier
                                    .width(220.dp)
                                    .height(300.dp),
                                photo = photo,
                                titleStyle = MaterialTheme.typography.titleMedium.copy(
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold
                                ),
                                dateStyle = MaterialTheme.typography.bodySmall.copy(
                                    color = Color.LightGray
                                ),
                                onClick = { onNavigateToDetail(photo) }
                            )
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AstronomyListScreenPreview() {
    AstronomyListScreen()
}