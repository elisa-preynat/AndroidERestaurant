package fr.isen.preynat.androiderestaurant.ble

import android.annotation.SuppressLint
import android.bluetooth.*
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import fr.isen.preynat.androiderestaurant.R
import fr.isen.preynat.androiderestaurant.databinding.ActivityBleDeviceBinding


@SuppressLint("MissingPermission")
class BleDeviceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBleDeviceBinding
    private var bluetoothGatt: BluetoothGatt? = null

    override fun onCreate(savedInstanceStateException: Bundle? ){
        super.onCreate(savedInstanceStateException)

        setContentView(R.layout.activity_ble_device)

        binding = ActivityBleDeviceBinding.inflate(layoutInflater)
        setContentView(binding.root)

       val device = intent.getParcelableExtra<BluetoothDevice?>(BLEScanActivity.DEVICE_KEY)
        binding.deviceName.text = device?.name ?:"Nom inconnu"
        binding.deviceStatut.text = getString(R.string.ble_device_disconnected)

        connectToDevice(device)

    }

    private fun connectToDevice(device: BluetoothDevice?) {
        bluetoothGatt= device?.connectGatt(this,true,object:BluetoothGattCallback() {
            override fun onConnectionStateChange(gatt: BluetoothGatt?, status: Int, newState: Int) {
                super.onConnectionStateChange(gatt, status, newState)
                connectionStateChange(gatt, newState)
            }

            override fun onServicesDiscovered(gatt: BluetoothGatt?, status: Int) {
                super.onServicesDiscovered(gatt, status)
                val bleServices = gatt?.services?.map {BleService(it.uuid.toString(),it.characteristics) }
                    ?: arrayListOf()

                runOnUiThread{
                    binding.serviceList.layoutManager  = LinearLayoutManager(this@BleDeviceActivity)/////////////////
                    binding.serviceList.adapter = BleServiceAdapter(bleServices.toMutableList())

                }
            }

            override fun onCharacteristicRead(
                gatt: BluetoothGatt?,
                characteristic: BluetoothGattCharacteristic?,
                status: Int
            ) {
                super.onCharacteristicRead(gatt, characteristic, status)
            }
        })


        bluetoothGatt?.connect()
    }

    private fun connectionStateChange(gatt: BluetoothGatt?, newState: Int) {
        val state = if(newState == BluetoothProfile.STATE_CONNECTED){
            gatt?.discoverServices()
            getString(R.string.ble_device_connected)
        }else {
            getString(R.string.ble_device_disconnected)
        }
        runOnUiThread{
            binding.deviceStatut.text = state
        }

    }

    override fun onStop() {
        super.onStop()
        closeBluetoothGatt()
    }

    private fun closeBluetoothGatt() {
        bluetoothGatt?.close()
        bluetoothGatt=null
    }



}
