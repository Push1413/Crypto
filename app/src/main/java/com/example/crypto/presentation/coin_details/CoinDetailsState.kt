package com.example.crypto.presentation.coin_details

import com.example.crypto.domain.model.CoinDetails

data class CoinDetailsState(
    val isLoading:Boolean = false,
    val coin:CoinDetails? = null,
    val error:String = ""
)
