package fr.afaucogney.mobile.android.app.kotlintest

import io.mockk.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MainClassTest {

    @Test
    fun executeOnSuccess() {
        // Given
        val cb = spyk<MyCallBack>()
        mockkConstructor(MyDependency::class)
        every { anyConstructed<MyDependency>().send(any(), any()) } answers {}

        //When
        MainClass().execute(cb)

        //Then
        verify {
            anyConstructed<MySecondDependency>().doStuff()
            cb.onSuccess(any())
        }

    }

    @Test
    fun executeOnFailure() {
        // Given
        val cb = spyk<MyCallBack>()
        mockkConstructor(MyDependency::class)
        every { anyConstructed<MyDependency>().send(any(), any()) } answers {}

        //When
        MainClass().execute(cb)

        //Then
        verifySequence {
            cb.onError(any())
        }

    }

}