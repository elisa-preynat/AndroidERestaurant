package fr.isen.preynat.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.widget.Button
import android.widget.Toast

class HomeActivity() : AppCompatActivity(), Parcelable {
    constructor(parcel: Parcel) : this() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
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
        }

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

}

