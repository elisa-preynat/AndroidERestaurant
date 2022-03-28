package fr.isen.preynat.androiderestaurant

import android.bluetooth.BluetoothClass
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.isen.preynat.androiderestaurant.model.Item

class BLEScanAdapter (val data: ArrayList<BluetoothClass.Device>, val clickListener: (BluetoothClass.Device) -> Unit) : RecyclerView.Adapter<BLEScanAdapter.CategoryViewHolder>() {

        inner class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            var deviceTitle: TextView = view.findViewById(R.id.deviceTitle)
            var adresseMac: ImageView = view.findViewById(R.id.adresseMac)
            var puissance: TextView = view.findViewById(R.id.puissance)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
            val deviceView = LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.device,
                    parent,
                    false
                )
            return CategoryViewHolder(deviceView)
        }

        override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
            val device = data[position]
            holder.deviceTitle.text = device.name

            val prix: String = item.prices[0].price+ "€"
            holder.priceTextView.text = prix
            Picasso.get().load(item.images[0].ifEmpty{ null })
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(holder.itemLogo)
            holder.itemView.setOnClickListener { clickListener(item) }



        }

        override fun getItemCount(): Int {
            return data.size
        }
    }
}
