package com.example.jimmy_polancoap2_p1.presentacion.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jimmy_polancoap2_p1.domain.model.Huacal
import com.example.jimmy_polancoap2_p1.domain.repository.HuacalRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class HuacalListViewModel(private val repository: HuacalRepository) : ViewModel() {

    val huacales: StateFlow<List<Huacal>> = repository.observeEntradas()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    val filtroCliente = MutableStateFlow("")

    val huacalesFiltrados: StateFlow<List<Huacal>> = filtroCliente.stateIn(viewModelScope)
}
