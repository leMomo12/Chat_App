package com.mnowo.chatapp.ViewModel

import androidx.arch.core.executor.testing.CountingTaskExecutorRule
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.mnowo.chatapp.MainCoroutineRule
import com.mnowo.chatapp.Repository.FakeRepository
import com.mnowo.schoolmanager.getOrAwaitValueTest
import com.mnowo.schoolmanager.other.Status
import kotlinx.coroutines.ExperimentalCoroutinesApi

import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class LoginFragmentViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val countingTaskExecutorRule = CountingTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: LoginFragmentViewModel

    @Before
    fun setup() {
        viewModel = LoginFragmentViewModel(FakeRepository())
    }

    @Test
    fun `empty email, returns error`() {
        viewModel.checkInput("", "fsdf")

        val value = viewModel.status.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `empty password, returns error`() {
        viewModel.checkInput("sfsdf", "")

        val value = viewModel.status.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `nothing empty, returns success`() {
        viewModel.checkInput("sfssf", "fsdf")

        val value = viewModel.status.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.SUCCESS)
    }

}