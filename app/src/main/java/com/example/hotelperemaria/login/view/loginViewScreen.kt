import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

import com.example.hotelperemaria.login.viewModel.LoginState
import com.example.hotelperemaria.login.viewModel.LoginViewModel
import com.example.hotelperemaria.navigation.AppScreens


@Composable
fun LoginScreen(viewModel: LoginViewModel = hiltViewModel(), onLoginSuccess: (String) -> Unit,
                onRegisterClick: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val loginState by viewModel.loginState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.surface)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Bienvenido",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Usuario") },
                leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) },
                singleLine = true,
                textStyle = TextStyle(color = Color.Black),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Contraseña") },
                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                shape = RoundedCornerShape(12.dp),
                singleLine = true,
                textStyle = TextStyle(color = Color.Black),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { viewModel.login(email, password) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text(text = "Acceder", fontSize = 18.sp)
            }
            Spacer(modifier = Modifier.height(50.dp))

            Text(
                text = "¿No tienes cuenta? Regístrate aquí",
                color = Color.Blue,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { onRegisterClick() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text(text = "Registrarse", fontSize = 18.sp)
            }

            // Mostrar estado de carga o error
            when (loginState) {
                is LoginState.Loading -> CircularProgressIndicator()
                is LoginState.Error -> {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = (loginState as LoginState.Error).message,
                        color = Color.Red,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                is LoginState.Success -> {
                    LaunchedEffect(loginState) {
                        if (loginState is LoginState.Success) {
                            onLoginSuccess((loginState as LoginState.Success).token)
                        }
                    }
                }
                else -> {}
            }
        }
    }
}

@Preview(showBackground = true, name = "Login Preview", showSystemUi = true)
@Composable
fun PreviewLoginScreen() {
    MaterialTheme {
        LoginScreen( onLoginSuccess = {}, onRegisterClick = {})
    }
}
