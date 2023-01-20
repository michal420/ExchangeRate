package com.example.exchangerate.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.exchangerate.CurrenciesApplication
import com.example.exchangerate.data.CurrenciesRepository
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

//GIT

/**
 * UI state for the Home screen
 */
sealed interface CurrenciesUiState {
    data class Success(val currencies: Map<String, String>) : CurrenciesUiState
    object Error : CurrenciesUiState
    object Loading : CurrenciesUiState
}

class CurrenciesViewModel(private val currenciesRepository: CurrenciesRepository) : ViewModel() {
    /** The mutable State that stores the status of the most recent request */
    var currenciesUiState: CurrenciesUiState by mutableStateOf(CurrenciesUiState.Loading)
        private set

    /**
     * Call getCurrencies() on init so we can display status immediately.
     */
    init {
        getCurrencies()
    }

    /**
     * Gets currencies information from the API service
     */
    fun getCurrencies() {
        viewModelScope.launch {
            currenciesUiState = CurrenciesUiState.Loading
            currenciesUiState = try {
                CurrenciesUiState.Success(currenciesRepository.getCurrencies())
            } catch (e: IOException) {
                CurrenciesUiState.Error
            } catch (e: HttpException) {
                CurrenciesUiState.Error
            }
        }
    }

    /**
     * Factory for [CurrenciesViewModel] that takes [CurrenciesRepository] as a dependency
     */
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as CurrenciesApplication)
                val currencyRepository = application.container.currenciesRepository
                CurrenciesViewModel(currenciesRepository = currencyRepository)
            }
        }
    }

}