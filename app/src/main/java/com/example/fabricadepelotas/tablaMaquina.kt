package com.example.fabricadepelotas

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.Request.Method
import com.android.volley.toolbox.StringRequest
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import com.android.volley.Request
import com.example.fabricadepelotas.databinding.ActivityTablaMaquinaBinding

class tablaMaquina : AppCompatActivity() {
    //se definen variables lateinit con base al activity de cada Empty activity
    val ipserver ="10.10.25.183"
    lateinit var numero: EditText
    lateinit var marca: EditText
    lateinit var modelo: EditText
    lateinit var mensajeMaquina: EditText
    lateinit var codigoborrar: EditText
    lateinit var verdatosMaquina: TextView
    lateinit var colorPlantas: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val unionMaquina=ActivityTablaMaquinaBinding.inflate(layoutInflater)
        setContentView(unionMaquina.root)
        numero=unionMaquina.editNumeroMaquina
        marca=unionMaquina.editMarcaMaquina
        modelo=unionMaquina.editModeloMaquina
        mensajeMaquina=unionMaquina.mostrarMaquina
        codigoborrar=unionMaquina.codigoBorrarMaquina
        verdatosMaquina=unionMaquina.verdatosMaquina
        colorPlantas=unionMaquina.colorPlanta
    }//función para regresar al menu de las tablas
    fun regresarTablasMenu(view: View){
        val irTablasM= Intent(this,TablasMenu::class.java)
        startActivity(irTablasM)
    }
    //función para poder insertar dartos de la tabla en la base de datos por medio de WEB
    fun ejecutarServicioInsertarMaquina(view: View){

        if (marca.text.isEmpty() || modelo.text.isEmpty() || numero.text.isEmpty()) {
            mostrarAlerta("Error", "Por favor llene todos los campos.")
            return
        }
        var url="http://$ipserver/android/insertarMaquina.php?marca="+marca.text.toString()+"&modelo="+modelo.text.toString()+ "&numero="+numero.text.toString()
        val cola= Volley.newRequestQueue(this);
        val cadenaConexion=
            StringRequest(Request.Method.GET,url,
                Response.Listener {response ->
                    //se recibe la respuestas

                    mensajeMaquina.setText("res: "+response.toString())
                },
                Response.ErrorListener { response ->
                    //en caso de error

                    mensajeMaquina.setText("error*"+response.toString())})
        cola.add(cadenaConexion)
        //consultarDatosMaquina(view)
    }
    //Función para poder borrar datos de la base de datos con base al id de cada registro
    fun borrarDatosMaquina(view: View) {
        val idABorrar = codigoborrar.text.toString()
        if (idABorrar.isEmpty()) {
            mostrarAlerta("Error", "Por favor ingrese un código para borrar.")
            return
        }
        val urlBorrar = "http://$ipserver/android/borrarMaquina.php?id=$idABorrar"
        val cola = Volley.newRequestQueue(this)
        val solicitud = StringRequest(
            Request.Method.GET, urlBorrar,
            Response.Listener { response ->
                // Si la solicitud se realiza correctamente, limpiar los campos
                marca.setText("")
                modelo.setText("")
                numero.setText("")
                mensajeMaquina.setText(response) // respuesta del servidor
            },
            Response.ErrorListener { error ->
                mensajeMaquina.setText("Error al borrar los datos: " + error.message)
            })

        cola.add(solicitud)
        //consultarDatosMaquina(view)
    }
    fun actualizarDatosMaquina(view: View) {
        val idAActualizar = codigoborrar.text.toString()
        if (marca.text.isEmpty() || modelo.text.isEmpty() || numero.text.isEmpty() || idAActualizar.isEmpty()) {
            mostrarAlerta("Error", "Por favor llene todos los campos y especifique un código para actualizar.")
            return
        }
        val nuevaMarca = marca.text.toString()
        val nuevoModelo = modelo.text.toString()
        val nuevoNumero = numero.text.toString()
        val urlActualizar = "http://$ipserver/android/actualizarMaquina.php?id=$idAActualizar&marca=$nuevaMarca&modelo=$nuevoModelo&numero=$nuevoNumero"

        val cola = Volley.newRequestQueue(this)

        val solicitud = StringRequest(
            Request.Method.GET, urlActualizar,
            Response.Listener { response ->
                // Si la solicitud se realiza correctamente, mostrar un mensaje de éxito
                mensajeMaquina.setText(response)
            },
            Response.ErrorListener { error ->
                // En caso de error en la solicitud, mostrar un mensaje de error
                mensajeMaquina.setText("Error al actualizar los datos : ${error.message}")
            })
        cola.add(solicitud)
        //consultarDatosMaquina(view)
    }
    fun consultarDatosMaquina(view: View) {
        val urlConsulta = "http://$ipserver/android/consultarM.php"
        val cola = Volley.newRequestQueue(this)
        val solicitud = StringRequest(
            Request.Method.GET, urlConsulta,
            Response.Listener { response ->
                // Mostrar los datos en el TextView
                verdatosMaquina.text = response
            },
            Response.ErrorListener { error ->
                // En caso de error en la solicitud, mostrar un mensaje de error
                verdatosMaquina.text = "Error al obtener los datos: ${error.message}"
            })

        // Añadir la solicitud a la cola
        cola.add(solicitud)
    }
    fun mostrarAlerta(titulo: String, mensaje: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(titulo)
        builder.setMessage(mensaje)
        builder.setPositiveButton("Aceptar", DialogInterface.OnClickListener { dialog, which -> })
        builder.show()
    }
    fun asignarDatoMaquina(view: View) {
        val idMaquina = codigoborrar.text.toString()
        val colorPlanta = colorPlantas.text.toString()

        if (idMaquina.isEmpty() || colorPlanta.isEmpty()) {
            mostrarAlerta("Error", "Por favor ingrese el ID de la máquina y el color de la planta.")
            return
        }

        val url = "http://$ipserver/android/asignarPlantaMaquina.php?id=$idMaquina&color=$colorPlanta"
        val cola = Volley.newRequestQueue(this)
        val solicitud = StringRequest(
            Request.Method.GET, url,
            Response.Listener { response ->
                // Mostrar la respuesta del servidor
                mensajeMaquina.setText(response)
            },
            Response.ErrorListener { error ->
                // En caso de error en la solicitud, mostrar un mensaje de error
                mensajeMaquina.setText("Error al asignar el color de la planta: ${error.message}")
            })

        cola.add(solicitud)
    }

}