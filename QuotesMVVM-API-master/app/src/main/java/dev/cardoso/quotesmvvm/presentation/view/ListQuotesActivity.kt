package dev.cardoso.quotesmvvm.presentation.view

import android.content.ContentValues.TAG
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.get
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import dev.cardoso.quotesmvvm.R
import dev.cardoso.quotesmvvm.data.model.QuoteModel
import dev.cardoso.quotesmvvm.data.model.QuoteResponse
import dev.cardoso.quotesmvvm.databinding.ActivityEditQuoteBinding
import dev.cardoso.quotesmvvm.databinding.ActivityListQuotesBinding
import dev.cardoso.quotesmvvm.databinding.ActivityLoginBinding
import dev.cardoso.quotesmvvm.domain.UserPreferencesRepository
import dev.cardoso.quotesmvvm.presentation.viewmodel.EditQuoteViewModel
import dev.cardoso.quotesmvvm.presentation.viewmodel.ListViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.ArrayList

@AndroidEntryPoint
class ListQuotesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListQuotesBinding
    private lateinit var quoteList: List<QuoteModel>
    private lateinit var userPreferencesRepository: UserPreferencesRepository

    private val getAllViewModel: ListViewModel by viewModels()
    private var token=""
    var lista: ArrayList<QuoteModel> = ArrayList()
    var quotes: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListQuotesBinding.inflate(layoutInflater)
        userPreferencesRepository = UserPreferencesRepository(this@ListQuotesActivity)
        setContentView(binding.root)
        getAllViewModel.quoteResponse.let{ }
        observer()
        binding.imageButtonRegresar.setOnClickListener {
           finish()
        }

        val simpListView = findViewById<ListView>(R.id.simpleListView);
        Log.e("Probando fpr: ", lista.toString())
        for (i in lista.indices){


            quotes.add(lista.get(i).quote)

            Log.e("Quotes: ", quotes.toString())
        }
        //val adapter = ArrayAdapter(this,R.layout.item_view, R.id.itemTextView, quotes)
        val adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1, quotes)
        simpListView.adapter = adapter
        //simpListView.setAdapter(adapter)

    }



    private fun observer(){
        lifecycleScope.launch(Dispatchers.IO){
            getAllViewModel.quoteResponse.collect{
                getAllViewModel.getQuotesList(token)
                binding.tvMessage.text= it.data.toString()


                lista = it.data.toCollection(ArrayList())

                Log.e("Lista : ", lista.toString())

            }
        }
    }

}





/*
private val getAllViewModel: ListViewModel by viewModels()

private var token = "Prueba---------------------------------------------------------"

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityListQuotesBinding.inflate(layoutInflater)
    userPreferencesRepository = UserPreferencesRepository(this@ListQuotesActivity)
    setContentView(binding.root)

    //val datos = arrayListOf<String>("Kevin","Uziel")
    val courseList = arrayListOf<String>(
        "C-Programming",
        "Data Structure",
        "Database",
        "Python",
        "Java",
        "Operating System",
        "Compiler Design",
        "Android Development"
    )


   /*lifecycleScope.launch(Dispatchers.IO) {
        Log.e("TOKEN", token)
        getAllViewModel.listQuotes("Baerer $token")
    }*/

    /*val id = 1
    val quote = "Frase"
    val author = "Amonimo"
    val quoteModel = QuoteModel(id=id, quote=quote, author=author)
    lifecycleScope.launch(Dispatchers.IO) {
        Log.e("TOKEN", token)
        editQuoteViewModel.editQuote(quoteModel, "Bearer $token")
    }*/

    binding.imageButtonRegresar.setOnClickListener() {
        finish()
    }


   /* val simpListView = findViewById<ListView>(R.id.simpleListView);
    //private lateinit var listQuoteAdapter: ArrayAdapter<*>

    val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, courseList)
    //val adapter = ArrayAdapter(this,R.layout.item_view, R.id.itemTextView, observer())
    simpListView.setAdapter(adapter)
    //val adapter = ArrayAdapter(this, R.layout.item_view,R.layout.itemTexView, courseList)
*/
    observer()
}


private fun observer(){
    lifecycleScope.launch(Dispatchers.IO) {
        getAllViewModel.quoteResponse.collect {
            getAllViewModel.getQuotesList(token)
            binding.tvMessage.text= it.data.toString()
            Log.e("View", it.data.toString())

        }
    }
    }



}
*/