package com.example.jimmy_polancoap2_p1.presentacion.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jimmy_polancoap2_p1.domain.model.Huacal
import com.example.jimmy_polancoap2_p1.domain.usecase.HuacalUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HuacalListViewModel @Inject constructor(
    private val useCases: HuacalUseCases
) : ViewModel() {

    private val huacales: StateFlow<List<Huacal>> = useCases.observeHuacales()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    val filtroCliente = MutableStateFlow("")

    val huacalesFiltrados: StateFlow<List<Huacal>> = combine(
        huacales,
        filtroCliente
    ) { lista, filtro ->
        if (filtro.isBlank()) lista
        else lista.filter { it.cliente.contains(filtro, ignoreCase = true) }
    }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun eliminarHuacal(id: Int) {
        viewModelScope.launch {
            useCases.deleteHuacal(id)
        }
    }
}