package com.mnowo.chatapp.ViewModel

import androidx.arch.core.executor.testing.CountingTaskExecutorRule
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mnowo.chatapp.MainCoroutineRule
import com.mnowo.chatapp.Repository.FakeRepository
import com.mnowo.schoolmanager.getOrAwaitValueTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import com.google.common.truth.Truth.assertThat
import com.mnowo.schoolmanager.other.Status
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class RegisterFragmentViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val countingTaskExecutorRule = CountingTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: RegisterFragmentViewModel


    @Before
    fun setup() {
        viewModel = RegisterFragmentViewModel(FakeRepository())
    }

    @Test
    fun `empty email, returns error`() {
        viewModel.checkInputs("", "asdfsdfsdfsa", "fsd")

        val value = viewModel.status.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `empty password, returns error`() {
        viewModel.checkInputs("asd", "", "asdsd")

        val value = viewModel.status.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `empty username, returns error`() {
        viewModel.checkInputs("asd", "asasdfsaf", "")

        val value = viewModel.status.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `everything empty, returns error`() {
        viewModel.checkInputs("", "", "")

        val value = viewModel.status.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `password less than seven characters, returns error`() {
        viewModel.checkInputs("sdfsdf", "sfsfa", "fsdfds")

        val value = viewModel.status.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `password more than seven characters, returns error`() {
        viewModel.checkInputs("sdfsdf", "sfsfafsdfs", "fsdfds")

        val value = viewModel.status.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }


    @Test
    fun `nothing empty and password equals 7, returns true`() {
        viewModel.checkInputs("asdasfd", "aaaaaaa", "asdfsd")

        val value = viewModel.status.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.SUCCESS)
    }
}