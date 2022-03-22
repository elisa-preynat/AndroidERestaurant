package fr.isen.preynat.androiderestaurant

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
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
/*, Parcelable {
    constructor(parcel: Parcel) : this() {
    }
    
    private lateinit var binding: ActivityHomeBinding //Etape 1 : déclaration
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater) //Etape 2
        val view = binding.root //setContentView(binding.root)
        setContentView(view) //setContentView(R.layout.activity_home) -- Etape 3
        //val text = findViewById<TextView>(R.id.homeStarters)
        
        binding.entrees.setOnClickListener{
            val intent = Intent(this, CategoryActivity::class.java)
            startActivity(intent)
            
        }

        binding.plats.setOnClickListener{
            val intent = Intent(this, Plats::class.java)
            startActivity(intent)

        }

        binding.desserts.setOnClickListener{
            val intent = Intent(this, Desserts::class.java)
            startActivity(intent)

        }
    }

    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        //Toast Entrées
                val entrees = findViewById(R.id.entrees) as Button
                    entrees.setOnClickListener {
                        Toast.makeText(
                            this@HomeActivity,
                            "Vous avez cliqué sur 'Entrées'",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
        //Toast Plats
                val plats = findViewById(R.id.plats) as Button
                plats.setOnClickListener{
                    Toast.makeText(
                        this@HomeActivity,
                        "Vous avez cliqué sur 'Plats'",
                        Toast.LENGTH_SHORT).show()
                }

        //Toast Desserts
                val desserts = findViewById(R.id.desserts) as Button
                desserts.setOnClickListener {
                    Toast.makeText(
                        this@HomeActivity,
                        "Vous avez cliqué sur 'Desserts'",
                        Toast.LENGTH_SHORT).show()
                }
        }*/

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<HomeActivity> {
        override fun createFromParcel(parcel: Parcel): HomeActivity {
            return HomeActivity(parcel)
        }

        override fun newArray(size: Int): Array<HomeActivity?> {
            return arrayOfNulls(size)
        }
    }
*/
}

