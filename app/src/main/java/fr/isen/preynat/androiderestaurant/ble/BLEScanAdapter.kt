package fr.isen.preynat.androiderestaurant.ble

import android.annotation.SuppressLint
import android.bluetooth.le.ScanResult
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.isen.preynat.androiderestaurant.R


internal class BLEScanAdapter (val bleList: ArrayList<ScanResult>, val clickListener: (ScanResult) -> Unit) : RecyclerView.Adapter<BLEScanAdapter.BleScanViewHolder>() {

    inner class BleScanViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var name: TextView = view.findViewById(R.id.deviceTitle)
        var address: TextView = view.findViewById(R.id.adresseMac)
        var rssi: TextView = view.findViewById(R.id.puissance)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BleScanViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.device,
                parent,
                false
            )
        return BleScanViewHolder(itemView)
    }

    @SuppressLint("MissingPermission")

    override fun onBindViewHolder(holder: BleScanViewHolder, position: Int) {
        val result = bleList[position]
        holder.name.text = result.device.name
        holder.address.text = result.device.address
        holder.rssi.text = result.rssi.toString()

        holder.itemView.setOnClickListener {
            clickListener(result)
        }



    }
    fun addResultToBleList(scanResult: ScanResult){
        val indexOfResult = bleList.indexOfFirst {
            it.device.address == scanResult.device.address
        }
        if(indexOfResult != -1) {
            bleList[indexOfResult] = scanResult
            //  notifyItemChanged(indexOfResult)
        } else {
            bleList.add(scanResult)
            //  notifyItemInserted(bleList.size -1)
        }

    }

    override fun getItemCount(): Int {
        return bleList.size
    }






}


