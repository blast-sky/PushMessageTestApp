@file:Suppress("SpellCheckingInspection", "SpellCheckingInspection")

package com.example.pushmessagetestapp

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.pushmessagetestapp.data.dto.MessageDto
import com.example.pushmessagetestapp.data.mapper.dto.toMessage
import com.example.pushmessagetestapp.data.remote.StoreUtil
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() = runTest {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext

        StoreUtil()
            .getMessagesFlow("3HtpyL0b4RrUZbexs4yg")
            .take(1)
            .collect { message ->
                assertTrue(message.isNotEmpty())
                assertEquals(message.first().toMessage()?.message, "hi")
            }

        assertEquals("com.example.pushmessagetestapp", appContext.packageName)
    }
}