package com.example.worldvisit

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.worldvisit.database.DatabaseHelper
import java.util.*

class MainActivity : AppCompatActivity() {
    private var btnStore: Button? = null
    private var btnGetall: Button? = null
    private var etname: EditText? = null
    private var databaseHelper: DatabaseHelper? = null
    private var tvnames: TextView? = null
    private var arrayList: ArrayList<String>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        databaseHelper = DatabaseHelper(this)
        tvnames = findViewById(R.id.tvnames) as TextView
        btnStore = findViewById(R.id.btnstore) as Button
        btnGetall = findViewById(R.id.btnget) as Button
        etname = findViewById(R.id.etname) as EditText
        btnStore!!.setOnClickListener { /*databaseHelper.addpaysDetail(etname.getText().toString());*/
            etname!!.setText("")
            Toast.makeText(this@MainActivity, "Stored Successfully!", Toast.LENGTH_SHORT).show()
        }
        btnGetall!!.setOnClickListener {
            arrayList = databaseHelper!!.getAllpaysList()
            tvnames!!.text = ""
            for (i in arrayList!!.indices) {
                tvnames!!.text = tvnames!!.text.toString() + ", " + arrayList!![i]
            }
        }
    }

    fun addCountry(view: View?) {
        val intent = Intent(this, Research::class.java)
        /*intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);*/startActivity(intent)
    }
}