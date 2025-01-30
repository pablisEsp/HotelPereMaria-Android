package com.example.hotelperemaria.utils

import android.provider.CalendarContract
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KingBed
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.KingBed
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEachIndexed
import com.example.hotelperemaria.ui.theme.whitesmoke

data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
)

@Composable
fun BottomAppBarCustom() {
    var selectedItemIndex by rememberSaveable {
        mutableIntStateOf(1)
    }
    val items = listOf(
        BottomNavigationItem(
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home
        ),
        BottomNavigationItem(
            title = "Search",
            selectedIcon = Icons.Filled.KingBed,
            unselectedIcon = Icons.Outlined.KingBed
        ),
        BottomNavigationItem(
            title = "User",
            selectedIcon = Icons.Filled.Face,
            unselectedIcon = Icons.Outlined.Face
        )
    )
    NavigationBar (
        modifier = Modifier
            .fillMaxWidth(),
        containerColor = Color.Black,
        contentColor = whitesmoke,
        ){
        items.fastForEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItemIndex == index,
                onClick = {
                    selectedItemIndex = index
                    /*
                    when (index) {
                        0 -> navController.navigate(AppScreens.BookRoomsScreen.route) // Search
                        1 -> navController.navigate(AppScreens.homeScreen.route) // Home
                        2 -> {
                            // Si tienes una pantalla de usuario, agrégala aquí
                        }
                    */
                },
                label ={ Text(item.title) } ,
                icon = {
                    Icon(
                        imageVector = if (index == selectedItemIndex) {
                            item.selectedIcon
                        } else {
                            item.unselectedIcon
                        },
                        contentDescription = item.title
                    )
                }
            )
        }
    }

}


@Preview
@Composable
fun BottomAppBarCustomPreview() {
    BottomAppBarCustom()
}