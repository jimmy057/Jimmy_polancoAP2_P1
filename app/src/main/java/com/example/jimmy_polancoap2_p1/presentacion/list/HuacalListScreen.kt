package com.example.jimmy_polancoap2_p1.presentacion.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.foundation.layout.fillMaxSize


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HuacalListScreen(
    onEdit: (Int) -> Unit,
    onAdd: () -> Unit,
    viewModel: HuacalListViewModel = hiltViewModel()
) {
    val huacales by viewModel.huacalesFiltrados.collectAsState()
    val filtro by viewModel.filtroCliente.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Listado de Huacales") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAdd) {
                Icon(Icons.Default.Add, contentDescription = "Agregar")
            }
        }
    ) { padding ->
        OutlinedTextField(
            value = filtro,
            onValueChange = { viewModel.filtroCliente.value = it },
            label = { Text("Buscar por cliente") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = padding
        ) {
            items(huacales) { huacal ->
                val total = huacal.cantidad * huacal.precio
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable { onEdit(huacal.id) }
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Cliente: ${huacal.cliente}")
                        Text("Cantidad: ${huacal.cantidad}")
                        Text("Fecha: ${huacal.fecha}")
                        Text("Precio: ${huacal.precio}")
                        Text("Total: $total")

                        Spacer(modifier = Modifier.padding(8.dp))

                        Button(
                            onClick = { viewModel.eliminarHuacal(huacal.id) }
                        ) {
                            Text("Eliminar")
                        }
                    }
                }
            }

            item {
                val totalCantidad = huacales.sumOf { it.cantidad }
                val totalGeneral = huacales.sumOf { it.cantidad * it.precio }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("📊 Resumen del listado filtrado")
                        Text("Cantidad total: $totalCantidad")
                        Text("Total general: $totalGeneral")
                    }
                }
            }
        }

    }
}
