package com.example.jimmy_polancoap2_p1.presentacion.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jimmy_polancoap2_p1.domain.model.Huacal
import com.example.jimmy_polancoap2_p1.domain.usecase.HuacalUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class HuacalEditViewModel @Inject constructor(
    private val useCases: HuacalUseCases
) : ViewModel() {
    private val _huacal = MutableStateFlow<Huacal?>(null)
    val huacal: StateFlow<Huacal?> = _huacal

    fun loadHuacal(id: Int) {
        viewModelScope.launch {
            _huacal.value = useCases.getHuacal(id)
        }
    }

    fun saveHuacal(huacal: Huacal, onComplete: () -> Unit) {
        viewModelScope.launch {
            useCases.upsertHuacal(huacal)
            onComplete()
        }
    }
}