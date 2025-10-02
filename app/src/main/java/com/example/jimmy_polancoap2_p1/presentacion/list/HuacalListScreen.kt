package com.example.jimmy_polancoap2_p1.presentacion.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import java.text.NumberFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HuacalListScreen(
    onEdit: (Int) -> Unit,
    onAdd: () -> Unit,
    viewModel: HuacalListViewModel = hiltViewModel()
) {
    val huacales by viewModel.huacalesFiltrados.collectAsState()
    val filtro by viewModel.filtroCliente.collectAsState()

    val totalCantidad = huacales.sumOf { it.cantidad }
    val totalGeneral = huacales.sumOf { it.cantidad * it.precio }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Entradas de Huacales") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAdd) {
                Icon(Icons.Default.Add, contentDescription = "Agregar")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            OutlinedTextField(
                value = filtro,
                onValueChange = { viewModel.filtroCliente.value = it },
                label = { Text("Filtrar por cliente") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                items(huacales) { huacal ->
                    HuacalItem(
                        cliente = huacal.cliente,
                        fecha = huacal.fecha,
                        cantidad = huacal.cantidad,
                        precio = huacal.precio,
                        onClick = { onEdit(huacal.id) },
                        onEliminar = { viewModel.eliminarHuacal(huacal.id) }
                    )
                }
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Total Cantidad: $totalCantidad")
                    Text("Total General: ${NumberFormat.getCurrencyInstance(Locale.US).format(totalGeneral)}")
                }
            }
        }
    }
}

@Composable
fun HuacalItem(
    cliente: String,
    fecha: String,
    cantidad: Int,
    precio: Double,
    onClick: () -> Unit,
    onEliminar: () -> Unit
) {
    val total = cantidad * precio
    val formatoMoneda = NumberFormat.getCurrencyInstance(Locale.US)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(cliente, style = MaterialTheme.typography.titleMedium)
                Text(fecha, style = MaterialTheme.typography.bodyMedium)
            }
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("${cantidad} x")
                Text(formatoMoneda.format(precio))
                Text("= ${formatoMoneda.format(total)}")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = onEliminar,
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Eliminar")
            }
        }
    }
}
