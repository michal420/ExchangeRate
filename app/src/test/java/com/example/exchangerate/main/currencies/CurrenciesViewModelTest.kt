package com.example.exchangerate.main.currencies

import com.example.exchangerate.CoroutinesTestRule
import com.example.exchangerate.data.CurrenciesRepository
import com.example.exchangerate.model.Currency
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.unmockkAll
import org.junit.*
import org.junit.Assert.*
import java.io.IOException

class CurrenciesViewModelTest {

    lateinit var cut: CurrenciesViewModel

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    @MockK
    lateinit var currenciesRepository: CurrenciesRepository

    // Stubs
    private val getCurrenciesStub = mapOf("EUR" to "Name", "EUR1" to "Name1")

    @Before
    fun setUp() {
        // turn relaxUnitFun on for all mock
        MockKAnnotations.init(this, relaxUnitFun = true)

        coEvery {
            currenciesRepository.getCurrencies()
        } coAnswers { getCurrenciesStub }

        cut = CurrenciesViewModel(currenciesRepository)
    }

    @After
    fun afterTests() {
        unmockkAll()
    }

    @Test
    fun `Given Success with non empty map when init then currenciesUiState should be Success with that map`() {
        // Given
        // setup

        // When
        // init

        // Then
        assertEquals(cut.currenciesUiState, CurrenciesUiState.Success(getCurrenciesStub))
    }

    @Test
    fun `Given Success when getCurrencies returning empty map then should be success`() {
        // Given
        val emptyMap = emptyMap<String, String>()
        coEvery {
            currenciesRepository.getCurrencies()
        } coAnswers { emptyMap }

        // When
        cut.getCurrencies()

        // Then
        assertEquals(cut.currenciesUiState, CurrenciesUiState.Success(emptyMap))
    }

    @Test
    fun `Given Error when getCurrencies then currenciesUiState should be error`() {
        // Given
        coEvery {
            currenciesRepository.getCurrencies()
        } coAnswers { throw IOException("Error") }

        // When
        cut.getCurrencies()

        // Then
        assertEquals(cut.currenciesUiState, CurrenciesUiState.Error)
    }

    @Test
    fun `Given IllegalAccessError Error when getCurrencies then currenciesUiState should be error`() {
        // Given
        coEvery {
            currenciesRepository.getCurrencies()
        } coAnswers { throw Exception("Error") }

        // When
        cut.getCurrencies()

        // Then
        assertEquals(cut.currenciesUiState, CurrenciesUiState.Error)
    }

    @Test
    fun `Given map of String to String when makeCurrenciesList then returns list of Currencies`() {
        // Given
        val testCurrenciesMap = mapOf("EUR" to "Euro", "AUD" to "Australian")
        val expectedReturnList =
            listOf(Currency("EUR", "Euro"), Currency("AUD", "Australian"))

        // When
        val out = makeCurrenciesList(testCurrenciesMap)

        // Then
        assertEquals(expectedReturnList, out)
    }

}