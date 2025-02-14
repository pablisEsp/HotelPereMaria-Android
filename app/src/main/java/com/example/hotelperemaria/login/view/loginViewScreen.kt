import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaCompleta() {

    var userName by remember { mutableStateOf("") }
    var pasword by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Hotel Asgard") })
        }, modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text("Bienvenido", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = userName,
                onValueChange = { userName = it},
                label = { Text("Ingresa tu usuario")})
            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = pasword,
                onValueChange = { pasword = it},
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                label = { Text("Ingresa la contraseña")})
            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {  }) {
                Text("Acceder")
            }
        }
    }
}

// Preview de la pantalla completa
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewPantallaCompleta() {
    PantallaCompleta()
}
