package fr.isen.preynat.androiderestaurant.BLE

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import fr.isen.preynat.androiderestaurant.R
import fr.isen.preynat.androiderestaurant.databinding.ActivityBleScanBinding

class BleScanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBleScanBinding
    private var progressBar: ProgressBar? = null
    private var scanning = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ble_scan)
        binding = ActivityBleScanBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        progressBar = findViewById<ProgressBar>(R.id.ScanBleProgressBar) as ProgressBar
        progressBar!!.visibility = View.INVISIBLE


        binding.scanBLEtexte.text = "Lancer le scan"

        binding.scanBLEBtn.setImageResource(R.drawable.ic_play)
        progressBar!!.visibility = View.INVISIBLE
        binding.scanBLEBtn.setOnClickListener {
            if (scanning) {
                binding.scanBLEtexte.text = "Lancer le scan"
                binding.scanBLEBtn.setImageResource(R.drawable.ic_play)
                progressBar!!.visibility = View.INVISIBLE
                scanning = !scanning
            } else {
                binding.scanBLEtexte.text = "Scan en cours..."
                binding.scanBLEBtn.setImageResource(R.drawable.ic_pause)
                progressBar!!.visibility = View.VISIBLE
                scanning = !scanning
            }
        }

    }
}




