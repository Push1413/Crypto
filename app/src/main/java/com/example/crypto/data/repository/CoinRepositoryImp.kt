package com.example.crypto.data.repository

import javax.inject.Inject
import com.example.crypto.data.remote.CoinPaprikaApi
import com.example.crypto.domain.repository.CoinRepository
import com.example.crypto.data.remote.dto.CoinDto
import com.example.crypto.data.remote.dto.CoinDetailsDto

class CoinRepositoryImp @Inject constructor(
    private val api: CoinPaprikaApi
) : CoinRepository {

    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailsDto {
        return api.getCoinById(coinId)
    }

}
