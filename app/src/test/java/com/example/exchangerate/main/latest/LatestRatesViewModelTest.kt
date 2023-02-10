package com.example.exchangerate.main.latest

import com.example.exchangerate.CoroutinesTestRule
import com.example.exchangerate.data.RatesRepository
import com.example.exchangerate.main.currencies.CurrenciesUiState
import com.example.exchangerate.model.CurrencyRates
import com.example.exchangerate.model.Rate
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.unmockkAll
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

class LatestRatesViewModelTest {

    lateinit var cut: LatestRatesViewModel

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    @MockK
    lateinit var ratesRepository: RatesRepository

    // Stubs
    private val getRatesStub =
        CurrencyRates(1.0, "EURO", "2023-02-07", mapOf("EUR" to 1.234, "AUD" to 1.547))

    @Before
    fun setUp() {
        // turn relaxUnitFun on for all mock
        MockKAnnotations.init(this, relaxUnitFun = true)

        coEvery {
            ratesRepository.getRates()
        } coAnswers { getRatesStub }

        cut = LatestRatesViewModel(ratesRepository)
    }

    @After
    fun afterTests() {
        unmockkAll()
    }

    @Test
    fun `Given Success with not null CurrencyRates object when init then ratesUiState should be success`() {
        // Given
        // setup

        // When
        // init

        // Then
        assertEquals(cut.ratesUiState, RatesUiState.Success(getRatesStub))
    }

//    @Test
//    fun `Given Success when getRates returning null object then should be success`() {
//        // Given
//        val nullRatesObject: CurrencyRates? = null
//        coEvery {
//            ratesRepository.getRates()
//        } coAnswers { nullRatesObject!! }
//
//        // When
//        cut.getRates()
//
//        // Then
//    assertNull("Is Null", RatesUiState.Success(nullRatesObject!!))
//    }

    @Test
    fun `Given Error when gerRates the rateUiState should be error`() {
        // Given
        coEvery {
            ratesRepository.getRates()
        } coAnswers { throw IOException("Error") }

        // When
        cut.getRates()

        // Then
        assertEquals(cut.ratesUiState, RatesUiState.Error)
    }

    @Test
    fun `Given IllegalAccessError Error when getRates then ratesUiState should be error`() {
        // Given
        coEvery {
            ratesRepository.getRates()
        } coAnswers { throw Exception("Error") }

        // When
        cut.getRates()

        // Then
        assertEquals(cut.ratesUiState, RatesUiState.Error)
    }

    @Test
    fun `Given map of String to Double when makeRatesList then returns list of Rates`() {
        // Given
        val testRatesMap = mapOf("EUR" to 1.0, "AUD" to 1.234)
        val expectedReturnList = listOf(Rate("EUR", 1.0), Rate("AUD", 1.234))

        // When
        val out = makeRatesList(testRatesMap)

        // Then
        assertEquals(expectedReturnList, out)
    }

}