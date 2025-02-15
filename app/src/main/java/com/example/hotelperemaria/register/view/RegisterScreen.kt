package com.example.hotelperemaria.register.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hotelperemaria.login.model.Usuario


@Composable
fun RegisterScreen( onRegisterSuccess: (Usuario) -> Unit) {
    var nombre by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var apellido1 by remember { mutableStateOf("") }
    var apellido2 by remember { mutableStateOf("") }
    var dniPasaporte by remember { mutableStateOf("") }
    var movil by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var fechaNacimiento by remember { mutableStateOf("") }

    //val registerState by viewModel.registerState.collectAsState()

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
                text = "Registro de Usuario",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre") },
                leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = apellido1,
                onValueChange = { apellido1 = it },
                label = { Text("Primer Apellido") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = apellido2,
                onValueChange = { apellido2 = it },
                label = { Text("Segundo Apellido (Opcional)") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = dniPasaporte,
                onValueChange = { dniPasaporte = it },
                label = { Text("DNI/Pasaporte") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = movil,
                onValueChange = { movil = it },
                label = { Text("Móvil") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = fechaNacimiento,
                onValueChange = { fechaNacimiento = it },
                label = { Text("Fecha de Nacimiento") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

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
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {  },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text(text = "Registrar", fontSize = 18.sp)
            }

            // Mostrar estado de carga o error
            /*
            when (registerState) {
                is RegisterState.Loading -> CircularProgressIndicator()
                is RegisterState.Error -> {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = (registerState as RegisterState.Error).message,
                        color = Color.Red,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                is RegisterState.Success -> {
                    LaunchedEffect(Unit) { onRegisterSuccess((registerState as RegisterState.Success).usuario) }
                }
                else -> {}
            }

             */
        }
    }
}

@Preview(showBackground = true, name = "Register Preview", showSystemUi = true)
@Composable
fun PreviewRegisterScreen() {
    MaterialTheme {
        RegisterScreen(
            onRegisterSuccess = {}
        )
    }
}
