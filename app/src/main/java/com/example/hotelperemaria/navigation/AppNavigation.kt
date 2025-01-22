import androidx.compose.runtime.Composable
import androidx.compose.ui.text.Placeholder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hotelperemaria.home.HomeScreen
import com.example.hotelperemaria.navigation.AppScreens
import com.example.hotelperemaria.search_room.screens.BookRoomsScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.homeScreen.route) {
        composable(route = AppScreens.homeScreen.route) {
            HomeScreen(navController)
        }
        composable(route = AppScreens.BookRoomsScreen.route) {
            BookRoomsScreen(navController = navController)
            //lele
        }

    }
}
