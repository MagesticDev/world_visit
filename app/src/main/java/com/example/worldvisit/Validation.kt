package com.example.worldvisit

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CalendarView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.worldvisit.database.DatabaseHelper
import java.util.*

class Validation : AppCompatActivity() {
    private var databaseHelper: DatabaseHelper? = null
    var date = String()

    override fun onCreate(savedInstanceState: Bundle?) {
        databaseHelper = DatabaseHelper(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_validation)
        val calendarView = findViewById<View>(R.id.calendar) as CalendarView
        val day = findViewById<View>(R.id.dayVisit) as TextView
        val c = Calendar.getInstance()
        val year = c[Calendar.YEAR]
        val monthofYear = c[Calendar.MONTH]
        val dayOfMonth = c[Calendar.DAY_OF_MONTH]
        val wDay = c[Calendar.DAY_OF_WEEK]
        var dayOfWeek = String()
        var month = String()
        Log.i("TAG", "${Calendar.DAY_OF_WEEK}")
         when (wDay){
            0 -> dayOfWeek = "Sam";
            1 -> dayOfWeek = "Dim";
            2 -> dayOfWeek = "Lun";
            3 -> dayOfWeek = "Mar";
            4 -> dayOfWeek = "Mer";
            5 -> dayOfWeek = "Jeu";
            6 -> dayOfWeek = "Ven";
        }
        when (monthofYear){
            0 -> month = "Janvier";
            1 -> month = "Fevrier";
            2 -> month = "Mars";
            3 -> month = "Avril";
            4 -> month = "Mai";
            5 -> month = "Juin";
            6 -> month = "Juillet";
            7 -> month = "Août";
            8 -> month = "Septembre";
            9 -> month = "Octobre";
            10 -> month = "Novembre";
            11 -> month = "Décembre";
        }

        val displayedDay = "$dayOfWeek. $dayOfMonth $month $year"
        date = displayedDay.format("$dayOfMonth ${month[2]} $year")
        //yearView.setText(displayYear);
        day.text = displayedDay
        // perform setOnDateChangeListener event on CalendarView
        calendarView.setOnDateChangeListener { view, year, Month, dayOfMonth ->
            val selectedDate = calendarView.date
        }
    }

    fun backToHome(view: View?) {
        this.databaseHelper!!.addpaysDetail("Espagne", "Madrid", "Europe", "$date", "ES");
        finish()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> origin/master
