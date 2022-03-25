package fr.isen.preynat.androiderestaurant


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fr.isen.preynat.androiderestaurant.databinding.ActivityDetailBinding
import fr.isen.preynat.androiderestaurant.model.Item


class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val item = intent.getSerializableExtra(CategoryActivity.ITEM_KEY) as Item

         binding.detailTitle.text = item.name_fr

        val carouselAdapter = CarouselAdapter(this, item.images)

        binding.detailSlider.adapter = carouselAdapter


    }
}
