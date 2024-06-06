package com.example.fabricadepelotas

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fabricadepelotas.databinding.ActivityConsultaPlantaBinding

class consultaPlanta : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val unionConsultaPlanta=ActivityConsultaPlantaBinding.inflate(layoutInflater)
        setContentView(unionConsultaPlanta.root)
    }
    fun regresarConsultaTablas(view: View){
        val regresarConsultaT= Intent(this,ConsultaTablas::class.java)
        startActivity(regresarConsultaT)
    }
}