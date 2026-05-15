package pe.edu.utp.astrodailyclean.presentation.navigation

sealed class Routes(val route: String) {
    object Splash : Routes("splash")
    object Login : Routes("login")
    object Home : Routes("home")
    object Astronomy : Routes("astronomy")
    object Detail : Routes("detail")
}
