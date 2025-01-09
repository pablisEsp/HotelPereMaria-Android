import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.Placeholder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hotelperemaria.navigation.AppScreens

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.FirstScreen.route ){
        composable(route = AppScreens.FirstScreen.route) {
            Text("Aqui va la primera pantalla")
        }
        composable(route = AppScreens.SecondScreen.route) {
            Text("Aqui va la segunda pantalla")
        }

    }
}