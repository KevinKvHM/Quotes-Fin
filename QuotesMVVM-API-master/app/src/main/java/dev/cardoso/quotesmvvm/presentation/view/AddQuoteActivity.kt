package dev.cardoso.quotesmvvm.presentation.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import dev.cardoso.quotesmvvm.R
import dev.cardoso.quotesmvvm.data.model.QuoteModel
import dev.cardoso.quotesmvvm.databinding.ActivityAddQuoteBinding
import dev.cardoso.quotesmvvm.databinding.ActivityEditQuoteBinding
import dev.cardoso.quotesmvvm.domain.UserPreferencesRepository
import dev.cardoso.quotesmvvm.presentation.viewmodel.AddQuoteViewModel
import dev.cardoso.quotesmvvm.presentation.viewmodel.EditQuoteViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
@AndroidEntryPoint
class AddQuoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddQuoteBinding
    private lateinit var userPreferencesRepository: UserPreferencesRepository

    private val addQuoteViewModel: AddQuoteViewModel by viewModels()

    private var token=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddQuoteBinding.inflate(layoutInflater)
        userPreferencesRepository = UserPreferencesRepository(this@AddQuoteActivity)
        setContentView(binding.root)
        getToken()

        binding.btnAdd.setOnClickListener {
            val id = binding.etIdQuotesAdd.text.toString().toInt()
            val quote = binding.etQuoteAdd.text.toString()
            val author = binding.etAuthorAdd.text.toString()
            val quoteModel = QuoteModel(id=id, quote=quote, author=author)

            lifecycleScope.launch(Dispatchers.IO) {
                Log.e("TOKEN", token)
                addQuoteViewModel.addQuote(quoteModel, "Bearer $token")
                //addQuoteViewModel.addQuote(quoteModel)
            }
        }

        binding.btnAddBack.setOnClickListener {
            finish()
        }

        observer()
    }
    @Override
    private fun getToken() {
        addQuoteViewModel.quoteResponse.let {
            lifecycleScope.launch(Dispatchers.IO) {
                userPreferencesRepository.getTokenFromDataStore().collect { token = it }
            }
        }
    }

    private  fun observer() {
        addQuoteViewModel.quoteResponse.let {
            lifecycleScope.launch(Dispatchers.IO) {
                addQuoteViewModel.quoteResponse.collect {
                    binding.tvMessage.text = it.message
                }
            }
        }
    }

    private fun back(){
        val intent: Intent = Intent(applicationContext, AddQuoteActivity::class.java)
        finish()
    }

}
