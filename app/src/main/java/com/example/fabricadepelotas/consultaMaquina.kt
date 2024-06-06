package com.example.fabricadepelotas

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fabricadepelotas.databinding.ActivityConsultaMaquinaBinding

class consultaMaquina : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val unionConsultaMaquina=ActivityConsultaMaquinaBinding.inflate(layoutInflater)
        setContentView(unionConsultaMaquina.root)
    }
    fun regresarConsultaTablas(view: View){
        val regresarConsultaT= Intent(this,ConsultaTablas::class.java)
        startActivity(regresarConsultaT)
    }
}