package com.example.worldvisit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.worldvisit.ws.Pays
import com.example.worldvisit.ws.ReseauHelper
import com.example.worldvisit.ws.RetrofitSingleton
import com.example.worldvisit.ws.WSInterface
import kotlinx.android.synthetic.main.activity_research.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResearchActivity : AppCompatActivity() {
    private val serviceRetrofit = RetrofitSingleton.retrofit.create(WSInterface::class.java)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_research)
        val layoutManager = LinearLayoutManager(this)
        my_recycle_view.layoutManager = layoutManager


        // vérification de l'état de la connexion internet :
        if (!ReseauHelper.estConnecte(this)) {
            Log.d("tag", "Test Réseau")
            Toast.makeText(this, "vous n'avez pas de connexion internet", Toast.LENGTH_LONG).show()
            return
        }

        //appel du webservice
        val call = serviceRetrofit.wsGet()
        call.enqueue(object : Callback<List<Pays>> {

            override fun onResponse(
                call: Call<List<Pays>>,
                response: Response<List<Pays>>
            ) {
                Log.d("tag", "Response: ${response.code()}")
                if (response.isSuccessful) {
                    val retourWS = response.body()
                    Log.d("tag", retourWS.toString())

                    // à ajouter pour de meilleures performances :
                    my_recycle_view.setHasFixedSize(true)

                    val paysAdapter = retourWS?.let {
                        HomePaysAdapter(it.toMutableList())
                    }
                    my_recycle_view.adapter = paysAdapter
                }
            }

            override fun onFailure(call: Call<List<Pays>>, t: Throwable) {
                Log.d("tag", "OnFailure: ${t.message}")
            }
        })

    }

    fun calendar(view: View){
        val intent = Intent(this, Validation::class.java)
        startActivity(intent)
    }
}
