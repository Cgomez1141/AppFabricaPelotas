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
import com.example.fabricadepelotas.databinding.ActivityTablaTecnicoBinding

class tablaTecnico : AppCompatActivity() {
    //se definen variables lateinit con base al activity de cada Empty activity
    val ipserver ="10.10.25.183"
    lateinit var documento: EditText
    lateinit var nombreCompleto: EditText
    lateinit var fechaNacimiento: EditText
    lateinit var telefono: EditText
    lateinit var mensajeTecnico: EditText
    lateinit var codigoborrar: EditText
    lateinit var verdatosTecnico: TextView
    lateinit var numeroMaquina: EditText
    lateinit var turno: EditText
    lateinit var periodo:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val unionTecnico=ActivityTablaTecnicoBinding.inflate(layoutInflater)
        setContentView(unionTecnico.root)
        documento=unionTecnico.editDocumentoTecnico
        nombreCompleto=unionTecnico.editNombreTecnico
        fechaNacimiento=unionTecnico.editFechaNacimientoTecnico
        telefono=unionTecnico.editNumeroTecnico
        mensajeTecnico=unionTecnico.mostrarTecnico
        codigoborrar=unionTecnico.codigoBorrarTecnico
        verdatosTecnico=unionTecnico.verdatosTecnico
        numeroMaquina=unionTecnico.numerodeMaquina
        turno=unionTecnico.turno
        periodo=unionTecnico.periodo
    }
    //Función para regresar al menu de tablas
    fun regresarTablasMenu(view: View){
        val irTablasM= Intent(this,TablasMenu::class.java)
        startActivity(irTablasM)
    }
    //Función para ingresar o insertar datos a la base de datos por medio de web
    fun ejecutarServicioInsertarTecnico(view: View){
        var url="http://$ipserver/android/insertarTecnico.php?documento="+ documento.text.toString()+ "&nombreCompleto="+nombreCompleto.text.toString()+"&fechaNacimiento="+fechaNacimiento.text.toString()+"&telefono="+telefono.text.toString()
        if (documento.text.isEmpty() || nombreCompleto.text.isEmpty() || fechaNacimiento.text.isEmpty() || telefono.text.isEmpty()) {
            mostrarAlerta("Error", "Por favor llene todos los campos.")
            return
        }
        val cola= Volley.newRequestQueue(this);
        val cadenaConexion=
            StringRequest(Request.Method.GET,url,
                Response.Listener {response ->
                    mensajeTecnico.setText("res: "+response.toString())
                },
                Response.ErrorListener { response ->
                    mensajeTecnico.setText("error*"+response.toString())})
        //se encola el mensaje
        cola.add(cadenaConexion)
       // consultarDatosTecnico(view)
    }
    //Función para borrar datos de la base da datos
    fun borrarDatosTecnico(view: View) {
        val idABorrar = codigoborrar.text.toString()
        if (idABorrar.isEmpty()) {
            mostrarAlerta("Error", "Por favor ingrese un código para borrar.")
            return
        }
        val urlBorrar = "http://$ipserver/android/borrarTecnico.php?id=$idABorrar"

        val cola = Volley.newRequestQueue(this)

        val solicitud = StringRequest(
            Request.Method.GET, urlBorrar,
            Response.Listener { response ->
                documento.setText("")
                nombreCompleto.setText("")
                fechaNacimiento.setText("")
                telefono.setText("")
                mensajeTecnico.setText(response) // Mostrar la respuesta del servidor
            },
            Response.ErrorListener { error ->
                mensajeTecnico.setText("Error al borrar los datos: " + error.message)
            })
        cola.add(solicitud)
       // consultarDatosTecnico(view)
    }
    //Función para actualizar datos de la base de datos
    fun actualizarDatosTecnico(view: View) {
        val idAActualizar = codigoborrar.text.toString()
        if (documento.text.isEmpty() || nombreCompleto.text.isEmpty() || fechaNacimiento.text.isEmpty() || telefono.text.isEmpty() || idAActualizar.isEmpty()) {
            mostrarAlerta("Error", "Por favor llene todos los campos y especifique un código para actualizar.")
            return
        }
        val nuevoDocumento = documento.text.toString()
        val nuevoNombre = nombreCompleto.text.toString()
        val nuevaFecha = fechaNacimiento.text.toString()
        val nuevoTel = telefono.text.toString()
        val urlActualizar = "http://$ipserver/android/actualizarTecnico.php?id=$idAActualizar&documento=$nuevoDocumento&nombreCompleto=$nuevoNombre&fechaNacimiento=$nuevaFecha&telefono=$nuevoTel"
        val cola = Volley.newRequestQueue(this)

        val solicitud = StringRequest(
            Request.Method.GET, urlActualizar,
            Response.Listener { response ->

                mensajeTecnico.setText(response)
            },
            Response.ErrorListener { error ->
                mensajeTecnico.setText("Error al actualizar los datos: ${error.message}")
            })
        cola.add(solicitud)
       // consultarDatosTecnico(view)
    }
    //Función para consultar los datos de la base de datos
    fun consultarDatosTecnico(view: View) {
        val urlConsulta = "http://$ipserver/android/consultarT.php"
        val cola = Volley.newRequestQueue(this)
        val solicitud = StringRequest(
            Request.Method.GET, urlConsulta,
            Response.Listener { response ->
                // Mostrar los datos en el TextView
                verdatosTecnico.text = response
            },
            Response.ErrorListener { error ->
                verdatosTecnico.text = "Error al obtener los datos: ${error.message}"
            })


        cola.add(solicitud)
    }
    //nos permite enviar una alerta al usuario para que llene todos los campos al momento de ejecutar un evento de un boton
    fun mostrarAlerta(titulo: String, mensaje: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(titulo)
        builder.setMessage(mensaje)
        builder.setPositiveButton("Aceptar", DialogInterface.OnClickListener { dialog, which -> })
        builder.show()
    }
    fun asignarDatoTecnico(view: View) {
        val idMaquina = codigoborrar.text.toString()
        val numM= numeroMaquina.text.toString()
        val turnoTecnico = turno.text.toString()
        val periodoTecnico = periodo.text.toString()

        // Verifica que todos los campos estén llenos
        if (idMaquina.isEmpty() || numM.isEmpty()|| turnoTecnico.isEmpty() || periodoTecnico.isEmpty()) {
            mostrarAlerta("Error", "Por favor llene los campos.")
            return
        }

        // Construye la URL con los parámetros necesarios
        val url = "http://$ipserver/android/asignarMaquinaTecnico.php?id=$idMaquina&turno=$turnoTecnico&periodo=$periodoTecnico&numeroMaquina=$numM"
        val cola = Volley.newRequestQueue(this)
        val solicitud = StringRequest(
            Request.Method.GET, url,
            Response.Listener { response ->
                // Mostrar la respuesta del servidor
                mensajeTecnico.setText(response)
            },
            Response.ErrorListener { error ->
                // En caso de error en la solicitud, mostrar un mensaje de error
                mensajeTecnico.setText("Error al asignar datos: ${error.message}")
            })

        // Agregar la solicitud a la cola
        cola.add(solicitud)
    }
}