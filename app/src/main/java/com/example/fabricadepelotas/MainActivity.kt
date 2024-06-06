package com.example.fabricadepelotas

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fabricadepelotas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val union = ActivityMainBinding.inflate(layoutInflater)
        setContentView(union.root)
    }
    fun ventanaTablasMenu(view: View){
        val irTablas= Intent(this,TablasMenu::class.java)
        startActivity(irTablas)
    }
    fun ventanaConsultaTablas(view: View){
        val irConsulta= Intent(this,ConsultaTablas::class.java)
        startActivity(irConsulta)
    }
}