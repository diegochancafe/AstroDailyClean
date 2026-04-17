package pe.edu.utp.astrodailyclean.presentation.navigation

sealed class Routes(val route: String) {

    object Astronomy : Routes("astronomy")

    object Detail : Routes("detail")

}