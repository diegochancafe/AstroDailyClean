package pe.edu.utp.astrodailyclean.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pe.edu.utp.astrodailyclean.domain.model.AstronomyPhoto
import pe.edu.utp.astrodailyclean.presentation.astronomy.detail.AstronomyDetailScreen
import pe.edu.utp.astrodailyclean.presentation.astronomy.list.AstronomyListScreen

@Composable
fun AppNavHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.Astronomy.route,
        modifier = modifier
    ) {
        composable(Routes.Astronomy.route) {
            AstronomyListScreen(
                onNavigateToDetail = { photo ->
                    navController.currentBackStackEntry
                        ?.savedStateHandle
                        ?.set("photo", photo)

                    navController.navigate(Routes.Detail.route)
                }
            )
        }

        composable(Routes.Detail.route) {
            val photo = remember {
                navController.previousBackStackEntry
                    ?.savedStateHandle
                    ?.get<AstronomyPhoto>("photo")
            }

            photo?.let {
                AstronomyDetailScreen(
                    photo = it,
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}