package com.example.polymorphism

import DataArray.GlobalVar
import android.Manifest
import model.Hewan
import model.Ayam
import model.Kambing
import model.Sapi
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_add.*

class AddActivity : AppCompatActivity() {

    private var position = -1
    private lateinit var Urii: String

    private val GetResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == Activity.RESULT_OK){
            val uri = it.data?.data
            Urii = uri.toString()

            PictureHolder.setImageURI(uri)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        supportActionBar?.hide()

        Urii = ""

        CheckPermissions()
        listener()
        GetIntent()
        display()
    }

    private fun CheckPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), GlobalVar.STORAGEWrite_PERMISSION_CODE)
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), GlobalVar.STORAGERead_PERMISSION_CODE)
        }
    }

    private fun listener() {

        PictureHolder.setOnClickListener{
            val myIntent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            myIntent.type = "image/*"
            GetResult.launch(myIntent)
        }

        FormInputButton.setOnClickListener {
            var isCompleted = true

            if (FormInputNama.editText?.text.toString().trim() == "") {
                isCompleted = false
                FormInputNama.error = "Nama Hewan Tidak Boleh Kosong!"
            } else {
                FormInputNama.error = ""
            }

            if (FormInputUmurHewan.editText?.text.toString().trim() == "") {
                isCompleted = false
                FormInputUmurHewan.error = "Umur Hewan Tidak Boleh Kosong!"
            } else {
                FormInputUmurHewan.error = ""
            }

            if (isCompleted) {
                var binatang: Hewan

                if (radiogrupjenis.checkedRadioButtonId == R.id.radioButtonSapi) {
                    binatang = Sapi(FormInputNama.editText?.text.toString().trim(), FormInputUmurHewan.editText?.text.toString().toInt(), "")
                } else if (radiogrupjenis.checkedRadioButtonId == R.id.radioButtonAyam){
                    binatang = Ayam(FormInputNama.editText?.text.toString().trim(), FormInputUmurHewan.editText?.text.toString().toInt(), "")
                } else {
                    binatang = Kambing(FormInputNama.editText?.text.toString().trim(), FormInputUmurHewan.editText?.text.toString().toInt(), "")
                }

                if (position == -1) {
                    GlobalVar.listDataAnimal.add(binatang)

                    var index = GlobalVar.listDataAnimal.size - 1

                    if (Urii.isNotEmpty()) {
                        GlobalVar.listDataAnimal[index].imageUri = Urii
                    }
                } else {
                    binatang.imageUri = GlobalVar.listDataAnimal[position].imageUri

                    GlobalVar.listDataAnimal.set(position, binatang)

                    if (Urii.isNotEmpty()) {
                        GlobalVar.listDataAnimal[position].imageUri = Urii
                    }
                }

                finish()
            }
        }
    }

    private fun GetIntent() {
        position = intent.getIntExtra("Position", -1)
    }

    private fun display() {
        if (position != -1) {
            FormInputNama.editText!!.setText(GlobalVar.listDataAnimal.get(position).nama)

            if (GlobalVar.listDataAnimal.get(position) is Sapi) {
                radiogrupjenis.check(R.id.radioButtonSapi)
            } else if (GlobalVar.listDataAnimal.get(position) is Ayam) {
                radiogrupjenis.check(R.id.radioButtonAyam)
            } else {
                radiogrupjenis.check(R.id.radioButtonKambing)
            }

            FormInputUmurHewan.editText!!.setText(GlobalVar.listDataAnimal.get(position).umur.toString())

            if (GlobalVar.listDataAnimal.get(position).imageUri != "") {
                PictureHolder.setImageURI(
                    Uri.parse(
                        GlobalVar.listDataAnimal.get(
                            position
                        ).imageUri
                    )
                )
            }

            judulForm.text = "Edit Hewan"
        }
    }
}