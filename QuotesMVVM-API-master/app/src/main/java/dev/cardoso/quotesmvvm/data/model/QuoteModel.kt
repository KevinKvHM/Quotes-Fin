package dev.cardoso.quotesmvvm.data.model
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import dev.cardoso.quotesmvvm.data.local.entities.QuoteEntity

data class QuoteModel (
    @SerializedName("id") val id: Int,
    @SerializedName("quote") val quote: String,
    @SerializedName("author") val author: String,
)

/*{
//@SerializedName("id") @PrimaryKey(autoGenerate = true) val id: Int,
    fun toQuoteEntity():QuoteEntity{
        return QuoteEntity(id= this.id,
                quote = this.quote,
                author = this.author)
    }
}*/
