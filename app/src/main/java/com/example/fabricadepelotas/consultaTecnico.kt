package com.example.fabricadepelotas

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fabricadepelotas.databinding.ActivityConsultaTecnicoBinding

class consultaTecnico : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val unionTecnico=ActivityConsultaTecnicoBinding.inflate(layoutInflater)
        setContentView(unionTecnico.root)
    }
    fun regresarConsultaTablas(view: View){
        val regresarConsultaT=Intent(this,ConsultaTablas::class.java)
        startActivity(regresarConsultaT)
    }
}