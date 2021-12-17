package dev.cardoso.quotesmvvm.presentation.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import dev.cardoso.quotesmvvm.R
import dev.cardoso.quotesmvvm.databinding.ActivityAddQuoteBinding
import dev.cardoso.quotesmvvm.databinding.ActivityDeleteQuoteBinding
import dev.cardoso.quotesmvvm.databinding.ActivityEditQuoteBinding
import dev.cardoso.quotesmvvm.databinding.ActivityMenuBinding
import dev.cardoso.quotesmvvm.presentation.viewmodel.UserViewModel

@AndroidEntryPoint
class MenuActivity : AppCompatActivity() {
     private lateinit var binding: ActivityMenuBinding

     override fun onCreate(savedInstanceState: Bundle?){
            print(this.applicationContext)
            super.onCreate(savedInstanceState)
            binding = ActivityMenuBinding.inflate(layoutInflater)
            setContentView(binding.root)

            binding.ImgButtonAdd.setOnClickListener {
                runAdd()
            }

            binding.imageButtonEdit.setOnClickListener {
                runEdit()
            }
            binding.imageButtonDelete.setOnClickListener {
                runDelete()
            }
            binding.ImgList.setOnClickListener{
                runListAll()
            }

        }

    private fun runAdd(){
        val intent: Intent = Intent(applicationContext, AddQuoteActivity::class.java)
        startActivity(intent)
    }
    private fun runEdit(){
        val intent: Intent = Intent(applicationContext, EditQuoteActivity::class.java)
        startActivity(intent)
    }
    private fun runDelete(){
        val intent: Intent = Intent(applicationContext, DeleteQuoteActivity::class.java)
        startActivity(intent)
    }
    private fun runListAll(){
        val intent: Intent = Intent(applicationContext, ListQuotesActivity::class.java)
        startActivity(intent)
    }
}