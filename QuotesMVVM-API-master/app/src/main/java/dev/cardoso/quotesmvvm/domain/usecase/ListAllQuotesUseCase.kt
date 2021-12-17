package dev.cardoso.quotesmvvm.domain.usecase

import dev.cardoso.quotesmvvm.data.local.daos.QuoteDAO
import dev.cardoso.quotesmvvm.data.model.QuoteModel
import dev.cardoso.quotesmvvm.data.model.QuoteResponse
import dev.cardoso.quotesmvvm.domain.QuoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ListAllQuotesUseCase @Inject constructor(private val quoteRepository: QuoteRepository) {


    suspend fun getQuotesListUseCase(token: String): Flow<QuoteResponse?> =
        quoteRepository.getQuotesListRepo(token)
}


