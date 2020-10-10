package com.raditya.wikipedia.feature.account.presentation

import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raditya.wikipedia.feature.account.domain.AccountUseCase
import com.raditya.wikipedia.feature.account.presentation.intent.AccountIntent
import com.raditya.wikipedia.feature.account.presentation.state.AccountState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import okhttp3.MediaType
import java.io.File
import javax.inject.Inject

class AccountViewModel @Inject constructor(
    private val usecase: AccountUseCase
) : ViewModel() {

    private val _state = MutableLiveData<AccountState>()
    val state: LiveData<AccountState>
        get() = _state

    fun processIntents(
        flows: Flow<AccountIntent>,
        scope: LifecycleCoroutineScope
    ) {

    }

    fun upload(file: File, mediaType: MediaType) {
        viewModelScope.launch(Dispatchers.IO) {
            usecase.execute(file, mediaType)
        }
    }
}