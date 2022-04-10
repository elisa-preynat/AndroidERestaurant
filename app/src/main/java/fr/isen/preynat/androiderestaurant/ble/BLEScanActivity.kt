package fr.isen.preynat.androiderestaurant.ble

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import fr.isen.preynat.androiderestaurant.R
import fr.isen.preynat.androiderestaurant.databinding.ActivityBleScanBinding


class BLEScanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBleScanBinding
    private val ENABLE_BLUETOOTH_REQUEST_CODE = 1
    private val ALL_PERMISSION_REQUEST_CODE = 100
    private var scanning = false
    private var adapter: BLEScanAdapter? = null

    private val bluetoothAdapter: BluetoothAdapter? by lazy {
        val bluetoothManager = getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        bluetoothManager.adapter
    }

    private fun startLeScanBLEWithPermission(enable: Boolean){
        if (checkAllPermissionGranted()) {
            startLeScanBLE(enable)
        }else{
            ActivityCompat.requestPermissions(this, getAllPermissions() ,ALL_PERMISSION_REQUEST_CODE)
        }
    }

    private fun checkAllPermissionGranted(): Boolean {
        return getAllPermissions().all { permission ->
            ActivityCompat.checkSelfPermission(
                this,
                permission
            ) == PackageManager.PERMISSION_GRANTED
        }
    }

    private fun getAllPermissions(): Array<String> {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            arrayOf(
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.BLUETOOTH_SCAN,
                android.Manifest.permission.BLUETOOTH_CONNECT
            )
        } else {
            arrayOf(
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBleScanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        when {
            bluetoothAdapter?.isEnabled == true ->
                startLeScanWithPermission(true)
            bluetoothAdapter != null ->
                askBluetoothPermission()
            else -> {
                displayBLEUnAvailable()
            }
        }
        binding.scanBLEBtn.setOnClickListener {
            val test = scanning

            startLeScanWithPermission(!scanning)
        }
        binding.scanBLEtexte.setOnClickListener {
            startLeScanWithPermission(!scanning)
        }
        adapter= BLEScanAdapter(arrayListOf()) {
            val intent = Intent ( this, BleDeviceActivity::class.java)
            intent.putExtra(DEVICE_KEY, it)
            startActivity(intent)
        }

        binding.deviceView.layoutManager = LinearLayoutManager(this)

        binding.deviceView.adapter = adapter



    }

    override fun onStop(){
        super.onStop()
        startLeScanWithPermission(false)
    }

    private fun startLeScanWithPermission(enable: Boolean){
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ){
            startLeScanBLE(enable)
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(
                android.Manifest.permission.ACCESS_FINE_LOCATION
                /*Manifest.permission.BLUETOOTH_CONNECT,
                Manifest.permission.BLUETOOTH_SCAN*/
            ), ALL_PERMISSION_REQUEST_CODE)
        }

    }

    @SuppressLint("MissingPermission")
    private fun startLeScanBLE(enable: Boolean) {

        bluetoothAdapter?.bluetoothLeScanner?.apply {
            if(enable) {
                scanning = true
                startScan(scanCallback)
            } else {
                scanning = false
                stopScan(scanCallback)
            }
            scanToggle()
        }
    }

    private val scanCallback = object: ScanCallback() {
        override fun onScanResult(callbackType: Int, result: ScanResult) {
            Log.d("BLEScanActivity", "result: ${result.device.address}, rssi : ${result.rssi}")
            adapter?.apply {

                addResultToBleList(result)
                notifyDataSetChanged()
            }

        }
    }



    private fun displayBLEUnAvailable() {
        binding.scanBLEBtn.isVisible = false
        binding.scanBLEtexte.text = getString(R.string.ble_scan_device_error)
        binding.ScanBleProgressBar.isVisible = false
    }

    private fun askBluetoothPermission(){
        val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
        if(ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.BLUETOOTH_CONNECT
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            startActivityForResult(enableBtIntent, ENABLE_BLUETOOTH_REQUEST_CODE)
        }
    }


    private fun initScanPage() {
        binding = ActivityBleScanBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.scanBLEtexte.text = "Lancer le scan"
        binding.scanBLEBtn.setImageResource(R.drawable.ic_play)
        binding.ScanBleProgressBar.visibility = View.INVISIBLE
        binding.ScanBleProgressBar.isIndeterminate = true

        scanToggle()
    }

    private fun scanToggle() {
        if (!scanning) {
            binding.scanBLEtexte.text = "Lancer le scan"
            binding.scanBLEBtn.setImageResource(R.drawable.ic_play)
            binding.ScanBleProgressBar.visibility = View.INVISIBLE
        } else {
            binding.scanBLEtexte.text = "Scan en cours..."
            binding.scanBLEBtn.setImageResource(R.drawable.ic_pause)
            binding.ScanBleProgressBar.visibility = View.VISIBLE
        }


    }

    companion object{
        val DEVICE_KEY = "device"
    }

}
