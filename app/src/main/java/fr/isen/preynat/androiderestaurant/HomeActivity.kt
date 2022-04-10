package fr.isen.preynat.androiderestaurant

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import fr.isen.preynat.androiderestaurant.ble.BLEScanActivity
import fr.isen.preynat.androiderestaurant.databinding.ActivityHomeBinding

class HomeActivity() : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.entrees.setOnClickListener{
            goToCategory(getString(R.string.entrees))
        }
        binding.plats.setOnClickListener{
            goToCategory(getString(R.string.plats))
        }
        binding.desserts.setOnClickListener{
            goToCategory(getString(R.string.desserts))
        }
        binding.boutonBLE.setOnClickListener{
            val intent = Intent(this, BLEScanActivity::class.java)
            startActivity(intent)
        }



    }

    override fun onDestroy(){
        super.onDestroy()

        Log.d("HomeActivity","Mon activité est détruite")
    }

    private fun goToCategory(category: String){
        val intent = Intent(this, CategoryActivity::class.java)
        intent.putExtra("category", category)
        startActivity(intent)
    }



}

