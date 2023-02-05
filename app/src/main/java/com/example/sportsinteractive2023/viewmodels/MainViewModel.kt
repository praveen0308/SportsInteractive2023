package com.example.sportsinteractive2023.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportsinteractive2023.models.ApiResponse
import com.example.sportsinteractive2023.repository.MatchRepository
import com.example.sportsinteractive2023.utils.identify
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val matchRepository: MatchRepository
) : ViewModel() {
    val mainPageState: MutableLiveData<MainPageState> =
        MutableLiveData(MainPageState.Idle)
    lateinit var apiResponse : ApiResponse
    fun fetchMatchDetails(matchCode: String) {
        viewModelScope.launch {
            matchRepository
                .fetchMatchDetail(matchCode)
                .onStart {
                    mainPageState.postValue(MainPageState.Loading)
                }
                .catch { exception ->
                    exception.message?.let {
                        mainPageState.postValue(MainPageState.Error(exception.identify()))
                        Timber.d("Error caused by >>>> fetchMatchDetails")
                        Timber.e("Exception : $it")
                    }
                }
                .collect {
                    apiResponse = it
                    mainPageState.postValue(MainPageState.ReceivedMatchDetails(it))
                }
        }
    }
}

sealed class MainPageState {
    object Loading : MainPageState()
    object Idle : MainPageState()
    object NoInternet : MainPageState()
    data class Error(val msg: String) : MainPageState()
    data class Processing(val msg: String) : MainPageState()
    data class ReceivedMatchDetails(val data: ApiResponse) : MainPageState()
}