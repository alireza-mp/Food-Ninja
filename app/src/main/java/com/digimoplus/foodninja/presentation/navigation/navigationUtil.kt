import androidx.navigation.NavController

fun NavController.navigateWithSaveState(route: String) {
    this.navigate(route) {
        this@navigateWithSaveState.currentDestination?.route?.let { route ->
            popUpTo(route) {
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}

fun NavController.navigateAndClean(route: String, popUpRoute: String) {
    this.navigate(route) {
        popUpTo(popUpRoute) {
            inclusive = true
        }
    }
}
