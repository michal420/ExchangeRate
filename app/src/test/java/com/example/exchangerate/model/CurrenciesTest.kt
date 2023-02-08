package com.example.exchangerate.model

import com.example.exchangerate.data.CurrenciesRepository
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import io.mockk.unmockkAll
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

// Coverage
// CUT, OUT
// GIVEN WHEN THEN

class CurrenciesTest {

    // GIVEN WHEN THEN
    @Test
    fun `Given invoke when testMethod then result should be 5`() {
        // Given
        // ...

        // When
        val out = testMethod()

        // Then
        assertEquals(5, out)
    }

    @Test
    fun `Given passing integer when stringMethod then result should be string with integer`() {
        // Given
        val testInt = 7

        // When
        val out = stringMethod(testInt)

        // Then
        assertEquals("You passed number: ${testInt}", out)
    }

    @Test
    fun `Given null as argument when stringMethod then result should be string`() {
        // Given
        val testInt = null

        // When
        val out = stringMethod(testInt)

        // Then
        assertEquals("You passed null", out)
    }

    @Test
    fun firstRunTest() {
        val result = 2+2
        println("Show something ${result}")
    }

    @Test
    fun `Second test show`() {
        println("Show something else")
    }
}