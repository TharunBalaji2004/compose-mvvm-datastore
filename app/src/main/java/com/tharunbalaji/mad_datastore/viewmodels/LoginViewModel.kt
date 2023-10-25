package com.tharunbalaji.mad_datastore.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tharunbalaji.mad_datastore.repository.AppEntryRepository
import com.tharunbalaji.mad_datastore.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.concurrent.Flow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val appEntryRepository: AppEntryRepository
) : ViewModel() {

    private val _isLoggedIn = MutableStateFlow(Constants.AuthState.LOADING)
    val isLoggedIn: StateFlow<Constants.AuthState> get() = _isLoggedIn

    fun saveAppEntry() {
        viewModelScope.launch {
            appEntryRepository.saveAppEntry()
        }
    }

    init {
        viewModelScope.launch {
            appEntryRepository.readAppEntry().collect {
                _isLoggedIn.value =
                    if (it) Constants.AuthState.LOGGED_IN else Constants.AuthState.NOT_LOGGED_IN
            }
        }
    }
}