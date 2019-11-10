package io.github.ovso.hometraining

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.github.ovso.hometraining.view.video.VideoViewModel
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class VideoViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var subject: VideoViewModel

    @Before
    fun setup() {
        subject = VideoViewModel()
    }

    @Test
    fun whenMainViewModelClicked_showSnackbar() {
        runBlocking {
            subject._snackbar.captureValues {
                subject.onMainViewClicked()
                assertSendsValues(2_000, "Hello, from threads!")
            }
        }
    }
}