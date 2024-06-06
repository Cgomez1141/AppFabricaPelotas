package com.example.fabricadepelotas

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fabricadepelotas.databinding.ActivityTablasMenuBinding

class TablasMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val unionTabla=ActivityTablasMenuBinding.inflate(layoutInflater)
        setContentView(unionTabla.root)
    }
    fun regresarFabrica(view: View){
        val irFabrica=Intent(this,MainActivity::class.java)
        startActivity(irFabrica)
    }
    fun irPlanta(view: View){
        val iraPlanta= Intent(this,tablaPlanta::class.java)
        startActivity(iraPlanta)
    }
    fun irMaquina(view: View){
        val iraMaquina=Intent(this, tablaMaquina::class.java)
        startActivity(iraMaquina)
    }
    fun irTecnico(view: View){
        val iraTecnico=Intent(this, tablaTecnico::class.java)
        startActivity(iraTecnico)
    }
}