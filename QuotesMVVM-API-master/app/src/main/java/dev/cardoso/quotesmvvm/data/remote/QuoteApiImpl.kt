package dev.cardoso.quotesmvvm.data.remote

import dev.cardoso.quotesmvvm.data.model.QuoteModel
import dev.cardoso.quotesmvvm.data.model.QuoteResponse
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class QuoteApiImpl @Inject constructor(retrofit: Retrofit):QuoteApi{
    private val apiService: QuoteApi = retrofit.create(QuoteApi::class.java)

    override suspend fun getQuotes(): Response<List<QuoteModel>> {
        return apiService.getQuotes()
    }
    override suspend fun getQuote(quoteId: Int): Any {
        return apiService.getQuote(quoteId)
    }
    //--------------------


    override suspend fun lista(token: String): Response<QuoteResponse> {
        return apiService.lista(token)
    }

    //--------
    override suspend fun editQuote(
        token: String,
        id: Int,
        quoteModel: QuoteModel
    ): Response<QuoteResponse> {
        return apiService.editQuote(token,id,quoteModel)
    }

    override suspend fun addQuote(
        token: String,
        quoteModel: QuoteModel
    ): Response<QuoteResponse> {
        return apiService.addQuote(token, quoteModel)
    }

    override suspend fun deleteQuote(
        token: String,
        id: Int,
    ): Response<QuoteResponse> {
        return apiService.deleteQuote(token,id)
    }


}