package com.emildayuliansari.myapplication

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.emildayuliansari.myapplication.adapter.AdapterTeamBola
import com.emildayuliansari.myapplication.databinding.ActivityMainBinding
import com.emildayuliansari.myapplication.model.Pemain

class MainActivity : AppCompatActivity() {
    private lateinit var  binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val  listPemain = ArrayList<Pemain>()
        listPemain.add(Pemain("Egy Maulana",R.drawable.egy, "Penyerang", "168cm","Medan","7 Juli 2001"))
        listPemain.add(Pemain("Elkan Baggott",R.drawable.elkan, "Belakang", "193cm","Bangkok","23 Oktober 2002"))
        listPemain.add(Pemain("Asnawi Mangkualam",R.drawable.asnawi, "Penyerang", "175cm","Makassar","04 Oktober 1999"))
        listPemain.add(Pemain("Witan Sulaeman",R.drawable.witan, "Penyerang", "167cm","Kota Palu"," 08 Oktober 2001"))
        listPemain.add(Pemain("Indra Sjafri",R.drawable.indra, "Pelatih", "165cm","Batang Kapas"," 02 Februari 1963"))

        binding.list.adapter = AdapterTeamBola(this, listPemain,object : AdapterTeamBola.OnClikListener{
            override fun detailData(item: Pemain?) {
                Dialog(this@MainActivity).apply {
                    requestWindowFeature(Window.FEATURE_NO_TITLE)
                    setCancelable(true)
                    setContentView(R.layout.detail_data_pemain)

                    val image = this.findViewById<ImageView>(R.id.image_pemain)
                    val nama = this.findViewById<TextView>(R.id.textDataDiri)

                    val posisi = this.findViewById<TextView>(R.id.txtposisi)
                    val tinggi = this.findViewById<TextView>(R.id.txttinggi)
                    val tempatlahir = this.findViewById<TextView>(R.id.txttempatlahir)
                    val tanggallahir = this.findViewById<TextView>(R.id.txttanggallahir)
                    val btn = this.findViewById<Button>(R.id.btnClose)

                    image.setImageResource(item?.foto ?:0)
                    nama.text = "${item?.nama}"
                    posisi.text = "${item?.posisi}"
                    tinggi.text = "${item?.tinggi}"
                    tempatlahir.text = "${item?.tempatlahir}"
                    tanggallahir.text = "${item?.tgllahir}"


                    btn.setOnClickListener {
                        this.dismiss()
                    }


                }.show()
            }

        })
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun setMode (selectedMode : Int){
        when (selectedMode){
            R.id.myprofile -> {
                val  intent = Intent ( this,Profile::class.java)
                startActivity(intent)
            }
        }
    }

}
