package Adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import model.Hewan
import model.Ayam
import model.Sapi
import com.example.polymorphism.R
import kotlinx.android.synthetic.main.activity_kartuhewan.view.*
import listener.CardListener

class HewanRVAdapter(var listAnimal: ArrayList<Hewan>, val cardListener: CardListener):
    RecyclerView.Adapter<HewanRVAdapter.viewHolder>(){

    class viewHolder(itemView: View, val cardListener1: CardListener): RecyclerView.ViewHolder(itemView){

        fun setData(data: Hewan){
            itemView.namahewan.text = data.nama

            if (data is Sapi) {
                itemView.jenishewan.text = "Sapi"
            } else if (data is Ayam) {
                itemView.jenishewan.text = "Ayam"
            } else {
                itemView.jenishewan.text = "Kambing"
            }

            itemView.umurhewan.text = data.umur.toString()

            if (data.imageUri.isNotEmpty()) {
                itemView.ImageHolder.setImageURI(Uri.parse(data.imageUri))
            }

            itemView.buttone.setOnClickListener {
                cardListener1.OnEditClicked(adapterPosition)
            }

            itemView.buttond.setOnClickListener {
                cardListener1.OnDeleteClicked(adapterPosition)
            }

            itemView.TombolSuara.setOnClickListener {
                Toast.makeText(itemView.context, data.MakeSound(), Toast.LENGTH_SHORT).show()
            }

            itemView.TombolMakan.setOnClickListener {
                Toast.makeText(itemView.context, data.GiveFood(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HewanRVAdapter.viewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.activity_kartuhewan, parent, false)
        return viewHolder(view, cardListener)
    }

    override fun onBindViewHolder(holder: HewanRVAdapter.viewHolder, position: Int) {
        holder.setData(listAnimal[position])
    }

    override fun getItemCount(): Int {
        return listAnimal.size
    }

}