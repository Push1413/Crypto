package com.example.crypto.domain.use_cases.get_coin

import com.example.crypto.common.Resource
import com.example.crypto.data.remote.dto.toCoinDetail
import com.example.crypto.domain.model.CoinDetails
import com.example.crypto.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(coinId: String): Flow<Resource<CoinDetails>> = flow {
        try {
            emit(Resource.Loading<CoinDetails>())
            val coinDetail = repository.getCoinById(coinId = coinId).toCoinDetail()
            emit(Resource.Success<CoinDetails>(coinDetail))
        } catch (e: HttpException) {
            emit(Resource.Error<CoinDetails>(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error<CoinDetails>("Couldn't reach server. Check your internet connection."))
        }

    }
}