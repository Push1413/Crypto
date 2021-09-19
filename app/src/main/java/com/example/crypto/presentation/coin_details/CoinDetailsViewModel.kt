package com.example.crypto.presentation.coin_details

import androidx.lifecycle.ViewModel
import com.example.crypto.domain.use_cases.get_coin.GetCoinUseCase
import javax.inject.Inject
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.crypto.common.Constants
import com.example.crypto.common.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@HiltViewModel
class CoinDetailsViewModel @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(CoinDetailsState())
    val state = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let { coinId ->
            getCoinDetails(coinId)
        }
    }

    private fun getCoinDetails(coinId:String){
        getCoinUseCase(coinId).onEach { result ->
            when(result){
                is Resource.Loading ->{
                    _state.value = CoinDetailsState(isLoading = true)
                }
                is Resource.Success ->{
                    _state.value = CoinDetailsState(coin = result.data)
                }
                is Resource.Error ->{
                    _state.value = CoinDetailsState(error = result.message?:"An unexpected error occured")
                }
            }
        }.launchIn(viewModelScope)
    }

}