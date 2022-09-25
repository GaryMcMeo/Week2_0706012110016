package com.example.polymorphism

import android.content.Intent
import listener.CardListener
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import Adapter.HewanRVAdapter
import DataArray.GlobalVar
import android.view.View
import model.Ayam
import model.Kambing
import model.Sapi
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(), CardListener {
    private val adapter = HewanRVAdapter(GlobalVar.listDataAnimal, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        supportActionBar?.hide()

        setUpRecyclerView()
        setUpListener()
    }

    override fun onResume() {
        super.onResume()
        if (GlobalVar.listDataAnimal.size > 0){
            list.visibility = View.VISIBLE
            AlertText.visibility = View.GONE
        }else{
            list.visibility = View.GONE
            AlertText.visibility = View.VISIBLE
        }
        adapter.notifyDataSetChanged()
    }

    private fun setUpRecyclerView() {
        val layoutManager = LinearLayoutManager(baseContext)
        list.layoutManager = layoutManager
        list.adapter = adapter
    }

    private fun setUpListener() {
        floatingaddbutton.setOnClickListener {
            val myIntent = Intent(this, AddActivity::class.java)

            startActivity(myIntent)
        }
    }

    override fun OnEditClicked(position: Int) {
                val myIntent = Intent(this, AddActivity::class.java).apply {
            putExtra("Position", position)
        }

        startActivity(myIntent)
    }

    override fun OnDeleteClicked(position: Int) {
        MaterialAlertDialogBuilder(this)
            .setTitle("Hapus Hewan")
            .setMessage("Apakah anda ingin menghapus hewan ini?")
            .setNegativeButton("Batal") { dialog, which ->
                // Respond to negative button press
            }
            .setPositiveButton("Setuju") { dialog, which ->
                GlobalVar.listDataAnimal.removeAt(position)
                Toast.makeText(baseContext, "Data berhasil di hapus", Toast.LENGTH_LONG).show()
                onResume()
            }
            .show()
    }
}