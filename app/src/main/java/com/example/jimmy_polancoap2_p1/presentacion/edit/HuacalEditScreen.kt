package com.example.jimmy_polancoap2_p1.presentacion.edit

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jimmy_polancoap2_p1.domain.model.Huacal
import kotlinx.coroutines.launch
@OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)
@Composable
fun HuacalEditScreen(
    huacalId: Int?,
    onSave: () -> Unit,
    onCancel: () -> Unit,
    viewModel: HuacalEditViewModel = hiltViewModel()
) {
    val huacalState by viewModel.huacal.collectAsState()

    var cliente by remember { mutableStateOf("") }
    var cantidad by remember { mutableStateOf("") }
    var fecha by remember { mutableStateOf("") }
    var precio by remember { mutableStateOf("") }

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    LaunchedEffect(huacalId) {
        huacalId?.let { viewModel.loadHuacal(it) }
    }

    LaunchedEffect(huacalState) {
        huacalState?.let { h ->
            cliente = h.cliente
            cantidad = h.cantidad.toString()
            fecha = h.fecha
            precio = h.precio.toString()
        } ?: run {
        }
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text(if (huacalId == null) "Agregar Huacal" else "Editar Huacal") }) },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            OutlinedTextField(
                value = cliente,
                onValueChange = { cliente = it },
                label = { Text("Cliente") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = cantidad,
                onValueChange = { cantidad = it },
                label = { Text("Cantidad") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = fecha,
                onValueChange = { fecha = it },
                label = { Text("Fecha") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = precio,
                onValueChange = { precio = it },
                label = { Text("Precio") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Button(onClick = {
                    if (cliente.isBlank()) {
                        scope.launch { snackbarHostState.showSnackbar("El nombre del cliente es obligatorio") }
                        return@Button
                    }

                    val cantidadInt = cantidad.toIntOrNull() ?: 0
                    val precioDouble = precio.toDoubleOrNull() ?: 0.0

                    val entidad = Huacal(
                        id = huacalId ?: 0,
                        fecha = fecha,
                        cliente = cliente,
                        cantidad = cantidadInt,
                        precio = precioDouble
                    )

                    viewModel.saveHuacal(entidad) {
                        onSave()
                    }
                }) {
                    Text("Guardar")
                }

                OutlinedButton(onClick = onCancel) {
                    Text("Cancelar")
                }
            }
        }
    }
}