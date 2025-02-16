package com.example.hotelperemaria.userProfile.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.hotelperemaria.userProfile.viewModel.UserProfileViewModel
import com.example.hotelperemaria.utils.Config


@Composable
fun UserProfileScreen(viewModel: UserProfileViewModel = hiltViewModel(), onProfileSuccess: () -> Unit) {

    val userProfileState = viewModel.userProfile.collectAsState()

    val userProfile = userProfileState.value
    //val userProfile = viewModel.userProfile

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {

        if (userProfile != null) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp)
                    .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(16.dp))
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Profile Icon",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(80.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Perfil de Usuario",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(24.dp))

                Config._id = userProfile.id

                UserProfileField(label = "Nombre", value = userProfile.nombre)
                UserProfileField(label = "Apellido 1", value = userProfile.apellido1)
                UserProfileField(label = "Email", value = userProfile.email)
                UserProfileField(label = "Móvil", value = userProfile.movil)
                UserProfileField(label = "DNI/Pasaporte", value = userProfile.dniPasaporte)
                UserProfileField(label = "Fecha de Nacimiento", value = userProfile.fechaNacimiento)

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = { onProfileSuccess() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Text(text = "Vovler al menu", fontSize = 18.sp)
                }
            }
        } else {
            CircularProgressIndicator()
        }
    }
}

@Composable
fun UserProfileField(label: String, value: String) {
    Column(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
        Text(text = label, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.Gray)
        Spacer(modifier = Modifier.height(2.dp))
        Text(text = value, fontSize = 18.sp, color = Color.Black)
    }
}

@Preview(showBackground = true, name = "Login Preview", showSystemUi = true)
@Composable
fun PreviewLoginScreen() {
    MaterialTheme {
        UserProfileScreen {  }
    }
}


