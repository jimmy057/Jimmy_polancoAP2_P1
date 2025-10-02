package com.example.jimmy_polancoap2_p1.presentacion.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jimmy_polancoap2_p1.domain.model.Huacal
import com.example.jimmy_polancoap2_p1.domain.repository.HuacalRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HuacalEditViewModel(private val repository: HuacalRepository) : ViewModel() {

    private val _huacal = MutableStateFlow<Huacal?>(null)
    val huacal: StateFlow<Huacal?> = _huacal

    fun loadHuacal(id: Int) {
        viewModelScope.launch {
            _huacal.value = repository.getEntrada(id)
        }
    }

    fun saveHuacal(huacal: Huacal, onComplete: () -> Unit) {
        viewModelScope.launch {
            repository.upsert(huacal)
            onComplete()
        }
    }
}