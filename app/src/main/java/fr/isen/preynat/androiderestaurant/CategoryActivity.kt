package fr.isen.preynat.androiderestaurant
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fr.isen.preynat.androiderestaurant.databinding.ActivityCategoryactivityBinding

class CategoryActivity : AppCompatActivity() {
    private lateinit var binding : ActivityCategoryactivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
       //setContentView(R.layout.activity_categoryactivity)

        binding = ActivityCategoryactivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val actionBar = supportActionBar
        val categoryName = intent.getStringExtra("category")
        binding.category.text = categoryName




    }

}