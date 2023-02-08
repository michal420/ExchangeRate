package com.example.exchangerate.main.latest

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.exchangerate.CurrenciesApplication
import com.example.exchangerate.data.RatesRepository
import com.example.exchangerate.model.CurrencyRates
import com.example.exchangerate.model.Rate
import kotlinx.coroutines.launch

sealed interface RatesUiState {
    data class Success(val rates: CurrencyRates) : RatesUiState
    object Error : RatesUiState
    object Loading : RatesUiState
}

class LatestRatesViewModel(private val ratesRepository: RatesRepository) : ViewModel() {
    /** The mutable State that stores the status of the most recent request */
    var ratesUiState: RatesUiState by mutableStateOf(RatesUiState.Loading)
        set

    /**
     * Call getCurrencies() on init so we can display status immediately.
     */
    init {
        getRates()
    }

    /**
     * Gets currencies information from the API service
     */
    fun getRates() {
        viewModelScope.launch {
            ratesUiState = RatesUiState.Loading
            ratesUiState = try {
                RatesUiState.Success(ratesRepository.getRates())
            } catch (e: Exception) {
                RatesUiState.Error
            }
        }
    }

    /**
     * Factory for [RatesViewModel] that takes [RatesRepository] as a dependency
     */
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as CurrenciesApplication)
                val ratesRepository = application.container.ratesRepository
                LatestRatesViewModel(ratesRepository = ratesRepository)
            }
        }
    }
}

// Function to convert array to Rate list
fun makeRatesList(m: Map<String, Double>): List<Rate> {
    val ratesList = mutableListOf<Rate>()
    val iter = m.keys.iterator()
    while (iter.hasNext()) {
        val key = iter.next()
        val value = m[key]
        if (value != null) {
            ratesList.add(Rate(key, value))
        }
    }
    return ratesList
}