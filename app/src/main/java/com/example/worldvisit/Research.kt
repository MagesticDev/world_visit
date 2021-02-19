package com.example.worldvisit

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL
import java.util.*

class Research : AppCompatActivity() {
    private var mRecyclerView: RecyclerView? = null
    //private var mAdapter: MyAdapter? = null
    private var mLayoutManager: RecyclerView.LayoutManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_research)
        mRecyclerView = findViewById<View>(R.id.my_recycle_view) as RecyclerView

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView!!.setHasFixedSize(true)

        // use a linear layout manager
        mLayoutManager = LinearLayoutManager(this)
        mRecyclerView!!.layoutManager = mLayoutManager

        // specify an adapter (see also next example)
        // val data = countries.toTypedArray<Array<String>>()
       // mAdapter = MyAdapter(data)
        //mRecyclerView!!.adapter = mAdapter
    }

    fun validateVisit(view: View?) {
        val intent = Intent(this, Validation::class.java)
        /*intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);*/startActivity(intent)
    }

    companion object {
        // On récupère un objet JSON du tableau
        // On fait le lien Personne - Objet JSON

        // On ajoute le pays à la liste
        // On retourne la liste des pays

        /*

        /**  Cette classe a été réimportée et adaptée afin de fonctionner dans le cas présent
         *  Link : http://tutorielandroid.francoiscolin.fr/recupjson.php
         */
             * InputStreamOperations est une classe complémentaire:
             * Elle contient une méthode InputStreamToString.
             */

        // On récupère le JSON complet
        // On récupère le tableau d'objets qui nous concernent
        // Pour tous les objets on récupère les infos
        /**
         * Récupère une liste de personnes.
         * @return ArrayList<Personne>: ou autre type de données.
         * @author François http://www.francoiscolin.fr/
        </Personne> */
        val countries: ArrayList<Country>
            get() {
                val countries = ArrayList<Country>()
                try {
                    val myurl = "https://restcountries.eu/rest/v2/all"
                    val url = URL(myurl)
                    val connection =
                        url.openConnection() as HttpURLConnection
                    connection.connect()
                    val inputStream = connection.inputStream
                    /*
                 * InputStreamOperations est une classe complémentaire:
                 * Elle contient une méthode InputStreamToString.
                 */
                    val result = InputStreamOperations.InputStreamToString(inputStream)

                    // On récupère le JSON complet
                    val jsonObject = JSONObject(result)
                    // On récupère le tableau d'objets qui nous concernent
                    val array = JSONArray(jsonObject.getString(""))
                    // Pour tous les objets on récupère les infos
                    for (i in 0 until array.length()) {
                        // On récupère un objet JSON du tableau
                        val obj = JSONObject(array.getString(i))
                        // On fait le lien Personne - Objet JSON
                        val country = Country()
                        val names = JSONObject(obj.getString("translations"))
                        country.name
                        country.capital
                        country.region
                        country.flag

                        // On ajoute le pays à la liste
                        countries.add(country)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                // On retourne la liste des pays
                return countries

                /*
            *  Cette classe a été réimportée et adaptée afin de fonctionner dans le cas présent
            *  Link : http://tutorielandroid.francoiscolin.fr/recupjson.php
            */
            }
    }
}