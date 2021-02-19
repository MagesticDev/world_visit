package com.example.worldvisit

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.worldvisit.database.DatabaseHelper
import java.util.*

class MainActivity : AppCompatActivity() {
    private var databaseHelper: DatabaseHelper? = null
    private var mRecyclerView: RecyclerView? = null
    private var mLayoutManager: RecyclerView.LayoutManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        databaseHelper = DatabaseHelper(this)
        // this.databaseHelper!!.addpaysDetail("New york", "blabla", "etat unis", "15/09/1995", "en");
        Log.e("warning", this.databaseHelper!!.allpaysList.toString())

        mRecyclerView = findViewById<View>(R.id.my_recycle_view) as RecyclerView

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView!!.setHasFixedSize(true)

        // use a linear layout manager
        mLayoutManager = LinearLayoutManager(this)
        mRecyclerView!!.layoutManager = mLayoutManager

        val paysList = this.databaseHelper!!.allpaysList;
        Log.e("warning", paysList.toString())
        val mAdapter = HomePaysAdapter(paysList)
        mRecyclerView!!.adapter = mAdapter


    }

    fun addCountry(view: View?) {
        val intent = Intent(this, ResearchActivity::class.java)
        /*intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);*/startActivity(intent)
    }
}