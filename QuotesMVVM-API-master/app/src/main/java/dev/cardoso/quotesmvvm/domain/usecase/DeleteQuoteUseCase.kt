package dev.cardoso.quotesmvvm.domain.usecase

import dev.cardoso.quotesmvvm.data.model.QuoteModel
import dev.cardoso.quotesmvvm.data.model.QuoteResponse
import dev.cardoso.quotesmvvm.domain.QuoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteQuoteUseCase @Inject constructor(private val quoteRepository: QuoteRepository) {
    suspend fun deleteQuote(quoteModel: QuoteModel, token: String): Flow<QuoteResponse?> = quoteRepository.deleteQuote(quoteModel, token)
}