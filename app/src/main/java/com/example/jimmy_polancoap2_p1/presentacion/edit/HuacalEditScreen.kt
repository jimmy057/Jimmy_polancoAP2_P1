package com.example.jimmy_polancoap2_p1.presentacion.edit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.jimmy_polancoap2_p1.domain.model.Huacal
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun HuacalEditScreen(
    viewModel: HuacalViewModel,
    huacalId: Int?,
    onSave: () -> Unit,
    onCancel: () -> Unit
) {
    var cliente by remember { mutableStateOf("") }
    var cantidad by remember { mutableStateOf("") }
    var fecha by remember { mutableStateOf("") }

    LaunchedEffect(huacalId) {
        huacalId?.let {
            val h = viewModel.getHuacal(it)
            h?.let { huacal ->
                cliente = huacal.cliente
                cantidad = huacal.cantidad.toString()
                fecha = huacal.fecha
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(if (huacalId == null) "Agregar Huacal" else "Editar Huacal") })
        }
    ) { padding ->
        Column(modifier = Modifier.padding(16.dp)) {
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
            Spacer(modifier = Modifier.height(16.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Button(onClick = {
                    CoroutineScope(Dispatchers.IO).launch {
                        viewModel.upsertHuacal(
                            Huacal(
                                idEntrada = huacalId ?: 0,
                                cliente = cliente,
                                cantidad = cantidad.toIntOrNull() ?: 0,
                                fecha = fecha
                            )
                        )
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