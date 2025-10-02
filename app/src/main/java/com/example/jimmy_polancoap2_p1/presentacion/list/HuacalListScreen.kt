package com.example.jimmy_polancoap2_p1.presentacion.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.jimmy_polancoap2_p1.ui.viewmodel.HuacalViewModel

@Composable
fun HuacalListScreen(
    viewModel: HuacalViewModel,
    onEdit: (Int) -> Unit,
    onAdd: () -> Unit
) {
    val huacales by viewModel.huacales.collectAsState(initial = emptyList())

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
        LazyColumn(contentPadding = padding) {
            items(huacales) { huacal ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable { onEdit(huacal.idEntrada) }
                ) {
                    Text(
                        text = "Cliente: ${huacal.cliente}\nCantidad: ${huacal.cantidad}\nFecha: ${huacal.fecha}",
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}
