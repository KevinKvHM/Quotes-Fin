package dev.cardoso.quotesmvvm.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import dev.cardoso.quotesmvvm.data.model.QuoteModel
import dev.cardoso.quotesmvvm.databinding.ActivityDeleteQuoteBinding
import dev.cardoso.quotesmvvm.databinding.ActivityEditQuoteBinding
import dev.cardoso.quotesmvvm.domain.UserPreferencesRepository
import dev.cardoso.quotesmvvm.presentation.viewmodel.DeleteQuoteViewModel
import dev.cardoso.quotesmvvm.presentation.viewmodel.EditQuoteViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
@AndroidEntryPoint
class DeleteQuoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDeleteQuoteBinding
    private lateinit var userPreferencesRepository: UserPreferencesRepository

    private val deleteQuoteViewModel: DeleteQuoteViewModel by viewModels()

    private var token=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeleteQuoteBinding.inflate(layoutInflater)
        userPreferencesRepository = UserPreferencesRepository(this@DeleteQuoteActivity)
        setContentView(binding.root)
        getToken()
        binding.btnDelete.setOnClickListener {
            val id = binding.etIdQuotes.text.toString().toInt()
            val quote = binding.etQuote.text.toString()
            val author = binding.etAuthor.text.toString()
            val quoteModel = QuoteModel(id=id, quote=quote, author=author)

            lifecycleScope.launch(Dispatchers.IO) {
                Log.e("TOKEN", token)
                deleteQuoteViewModel.deleteQuote(quoteModel, "Bearer $token")
            }
        }

        observer()
        binding.btnDeBack.setOnClickListener {
            finish()
        }
    }
    @Override
    private fun getToken() {
        deleteQuoteViewModel.quoteResponse.let {
            lifecycleScope.launch(Dispatchers.IO) {
                userPreferencesRepository.getTokenFromDataStore().collect { token = it }
            }
        }
    }

    private  fun observer() {
        deleteQuoteViewModel.quoteResponse.let {
            lifecycleScope.launch(Dispatchers.IO) {
                deleteQuoteViewModel.quoteResponse.collect {
                    binding.tvMessage.text = it.message
                }
            }
        }
    }

}