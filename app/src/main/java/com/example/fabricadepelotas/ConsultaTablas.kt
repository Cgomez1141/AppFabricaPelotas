package com.example.fabricadepelotas

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fabricadepelotas.databinding.ActivityConsultaTablasBinding
import com.example.fabricadepelotas.databinding.ActivityMainBinding

class ConsultaTablas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val unionConsulta =ActivityConsultaTablasBinding.inflate(layoutInflater)
        setContentView(unionConsulta.root)
    }
    fun regresarFabrica(view: View){
        val irFabrica= Intent(this,MainActivity::class.java)
        startActivity(irFabrica)
    }
    fun irConsultaPlanta(view: View){
        val irConsulPlanta=Intent(this,consultaPlanta::class.java)
        startActivity(irConsulPlanta)
    }
    fun irConsultaMaquina(view: View){
        val irConsulMaquina=Intent(this,consultaMaquina::class.java)
        startActivity(irConsulMaquina)
    }
    fun irConsultaTecnico(view: View){
        val irConsulTecnico=Intent(this,consultaTecnico::class.java)
        startActivity(irConsulTecnico)
    }
}