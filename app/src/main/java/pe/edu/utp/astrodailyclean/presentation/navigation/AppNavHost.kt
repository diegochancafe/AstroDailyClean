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
import pe.edu.utp.astrodailyclean.presentation.login.LoginScreen
import pe.edu.utp.astrodailyclean.presentation.splash.SplashScreen

@Composable
fun AppNavHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.Splash.route,
        modifier = modifier
    ) {
        composable(Routes.Splash.route) {
            SplashScreen(
                onNavigateToLogin = {
                    navController.navigate(Routes.Login.route) {
                        popUpTo(Routes.Splash.route) { inclusive = true }
                    }
                },
                onNavigateToHome = {
                    navController.navigate(Routes.Astronomy.route) {
                        popUpTo(Routes.Splash.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Routes.Login.route) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Routes.Astronomy.route) {
                        popUpTo(Routes.Login.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Routes.Astronomy.route) {
            AstronomyListScreen(
                onNavigateToDetail = { photo ->
                    navController.currentBackStackEntry
                        ?.savedStateHandle
                        ?.set("photo", photo)
                    navController.navigate(Routes.Detail.route)
                },
                onLogout = {
                    navController.navigate(Routes.Login.route) {
                        popUpTo(Routes.Astronomy.route) { inclusive = true }
                    }
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
