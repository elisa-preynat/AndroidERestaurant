package fr.isen.preynat.androiderestaurant

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import fr.isen.preynat.androiderestaurant.databinding.ActivityCategoryactivityBinding

class CategoryActivity : AppCompatActivity() {
    private lateinit var binding : ActivityCategoryactivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityCategoryactivityBinding.inflate(layoutInflater)

        setContentView(binding.root)


        binding.category.text = intent.getStringExtra("category") ?: " "

        val items = resources.getStringArray(R.array.plats)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter =
            CategoryAdapter(items){
                val intent = Intent(this,DetailActivity::class.java)
                intent.putExtra(ITEM_KEY, it)
                startActivity(intent)
            }


    }
    companion object{
        val ITEM_KEY= "item"
    }

}