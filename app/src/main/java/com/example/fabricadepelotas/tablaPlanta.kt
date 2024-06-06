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
import com.example.fabricadepelotas.databinding.ActivityTablaPlantaBinding

class tablaPlanta : AppCompatActivity() {
    //se definen variables lateinit con base al activity de cada Empty activity
    val ipserver ="10.10.25.183"
    lateinit var color: EditText
    lateinit var superficie: EditText
    lateinit var nombreProceso: EditText
    lateinit var gradoComplejidad: EditText
    lateinit var mensajePlanta: EditText
    lateinit var codigoborrar: EditText
    lateinit var verdatosPlanta: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val unionPlanta = ActivityTablaPlantaBinding.inflate(layoutInflater)
        setContentView(unionPlanta.root)
        color=unionPlanta.editColorPlanta
        superficie=unionPlanta.editSuperficiePlanta
        nombreProceso=unionPlanta.editNombreProcesoPlanta
        gradoComplejidad=unionPlanta.editGradoComplejidadPlanta
        mensajePlanta=unionPlanta.mostrarPlanta
        codigoborrar=unionPlanta.codigoBorrarPlanta
        verdatosPlanta=unionPlanta.verdatosPlanta
    }
    //función para regresar al menu de las tablas
    fun regresarTablasMenu(view: View){
        val irTablasM=Intent(this,TablasMenu::class.java)
        startActivity(irTablasM)
    }
    //función para poder insertar dartos de la tabla en la base de datos por medio de WEB
    fun ejecutarServicioInsertarPlanta(view: View){
        //ruta de envío de mensaje
        var url="http://$ipserver/android/insertarPlanta.php?color="+ color.text.toString()+ "&superficie="+superficie.text.toString()+ "&nombreProceso="+nombreProceso.text.toString()+"&gradoComplejidad="+gradoComplejidad.text.toString()
        if (color.text.isEmpty() || superficie.text.isEmpty() || nombreProceso.text.isEmpty() || gradoComplejidad.text.isEmpty()) {
            mostrarAlerta("Error", "Por favor llene todos los campos.")
            return
        }
        val cola= Volley.newRequestQueue(this);
        val cadenaConexion=
            StringRequest(Request.Method.GET,url,
                Response.Listener {response ->
                    //se recibe la respuestas

                    mensajePlanta.setText("res: "+response.toString())
                },
                Response.ErrorListener { response ->
                    //en caso de error

                    mensajePlanta.setText("error*"+response.toString())})
        cola.add(cadenaConexion)
       // consultarDatosPlanta(view)
    }
    fun borrarDatosPlanta(view: View) {
        val idABorrar = codigoborrar.text.toString()
        if (idABorrar.isEmpty()) {
            mostrarAlerta("Error", "Por favor ingrese un código para borrar.")
            return
        }
        val urlBorrar = "http://$ipserver/android/borrarPlanta.php?id=$idABorrar"
        val cola = Volley.newRequestQueue(this)
        val solicitud = StringRequest(
            Request.Method.GET, urlBorrar,
            Response.Listener { response ->
                // Si la solicitud se realiza correctamente, limpiar los campos
                color.setText("")
                superficie.setText("")
                nombreProceso.setText("")
                gradoComplejidad.setText("")
                mensajePlanta.setText(response) // Mostrar la respuesta del servidor
            },
            Response.ErrorListener { error ->
                mensajePlanta.setText("Error al borrar los datos: " + error.message)
            })

        cola.add(solicitud)
        //consultarDatosPlanta(view)
    }
    //función para actualizar los datos de la base de datos
    fun actualizarDatosPlanta(view: View) {
        val idAActualizar = codigoborrar.text.toString()
        if (color.text.isEmpty() || superficie.text.isEmpty() || nombreProceso.text.isEmpty() || gradoComplejidad.text.isEmpty() || idAActualizar.isEmpty()) {
            mostrarAlerta("Error", "Por favor llene todos los campos y especifique un código para actualizar.")
            return
        }
        val nuevoColor = color.text.toString()
        val nuevaSuperficie = superficie.text.toString()
        val nuevoNombreProceso = nombreProceso.text.toString()
        val nuevoGradoComplejidad = gradoComplejidad.text.toString()
        val urlActualizar = "http://$ipserver/android/actualizarPlanta.php?id=$idAActualizar&color=$nuevoColor&superficie=$nuevaSuperficie&nombreProceso=$nuevoNombreProceso&gradoComplejidad=$nuevoGradoComplejidad"

        val cola = Volley.newRequestQueue(this)
        val solicitud = StringRequest(
            Request.Method.GET, urlActualizar,
            Response.Listener { response ->
                // Si la solicitud se realiza correctamente, mostrar un mensaje de éxito
                mensajePlanta.setText(response)
            },
            Response.ErrorListener { error ->
                // En caso de error en la solicitud, mostrar un mensaje de error
                mensajePlanta.setText("Error al actualizar los datos: ${error.message}")
            })
        cola.add(solicitud)
        //consultarDatosPlanta(view)
    }
    //función para consultar los datos guardados en la base de datos
    fun consultarDatosPlanta(view: View) {
        val urlConsulta = "http://$ipserver/android/consultarP.php"
        val cola = Volley.newRequestQueue(this)
        val solicitud = StringRequest(
            Request.Method.GET, urlConsulta,
            Response.Listener { response ->
                // Mostrar los datos en el TextView
                verdatosPlanta.text = response
            },
            Response.ErrorListener { error ->
                verdatosPlanta.text = "Error al obtener los datos: ${error.message}"
            })
        cola.add(solicitud)
    }
    fun mostrarAlerta(titulo: String, mensaje: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(titulo)
        builder.setMessage(mensaje)
        builder.setPositiveButton("Aceptar", DialogInterface.OnClickListener { dialog, which -> })
        builder.show()
    }

}