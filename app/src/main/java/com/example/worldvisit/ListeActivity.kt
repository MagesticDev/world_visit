package com.example.worldvisit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.worldvisit.ws.Pays
import com.example.worldvisit.ws.ReseauHelper
import com.example.worldvisit.ws.RetrofitSingleton
import com.example.worldvisit.ws.WSInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListeActivity : AppCompatActivity() {

    //Retrofit
    private val serviceRetrofit = RetrofitSingleton.retrofit.create(WSInterface::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_liste)
        // layout manager, décrivant comment les items sont disposés :
//        val layoutManager = LinearLayoutManager(this)
//        liste_pays.layoutManager = layoutManager
        
        recuperePays()
        


    }


    fun recuperePays(){

        // vérification de l'état de la connexion internet :
        if (!ReseauHelper.estConnecte(this))
        {
            Log.d("tag", "Test Réseau")
            Toast.makeText(this, "vous n'avez pas de connexion internet", Toast.LENGTH_LONG).show()
            return
        }

        //appel du webservice
        val call = serviceRetrofit.wsGet()
        call.enqueue(object : Callback<List<Pays>> {

            override fun onResponse(call: Call<List<Pays>>, response: Response<List<Pays>>){
                Log.d("tag", "Response: ${response.code()}")
                if(response.isSuccessful){
                    val retourWSpays = response.body()
                    Log.d("tag", retourWSpays.toString())

                    // à ajouter pour de meilleures performances :
//                    liste_pays.setHasFixedSize(true)

                    val vehiculesAdapter = retourWSpays?.let {
                        PaysAdapter(it.toMutableList(),this@ListeActivity)
                    }
//                    liste_pays.adapter = vehiculesAdapter
                }
            }
            override fun onFailure(call: Call<List<Pays>>, t: Throwable){
                Log.d("tag", "OnFailure: ${t.message}")
            }
        })

    }
}

