package com.example.apirest

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Llamar la función
        usuarios()
    }
    fun usuarios()
    {
        //TextView donde se presentaran los datos
        val txtJson=findViewById<TextView>(R.id.txtJson)
        //Instanciar el RequestQueue.
        val RequestQueue = Volley.newRequestQueue(this)
        //URL de los datos
        val url="https://gorest.co.in/public/v1/users"
        //Variable para guardar la cadena JSON de tipo String
        var Cadena :String=""
        //Solicitar una petición desde una URL
        val JsonObjectR = JsonObjectRequest(Request.Method.GET, url, null,
            { response ->
                //JSONArray para arreglos [] response.getJSONArray("data") nombre del array
                val jsonArray : JSONArray = response.getJSONArray("data")
                //Recorrido del array
                for (i in 0 until jsonArray.length())
                {
                    val JObject : JSONObject = jsonArray.getJSONObject(i)
                   // obtener los valores del array getString("id")
                    val Id: String = JObject.getString("id")
                    val Name: String = JObject.getString("name")
                    val Email: String = JObject.getString("email")
                    val Gender: String = JObject.getString("gender")
                    val Status: String = JObject.getString("status")
                    //Cadena de datos del Json
                    Cadena=
                        "$Cadena{ " +
                                "\n id: $Id, " +
                                "\n name: $Name, " +
                                "\n email: $Email, " +
                                "\n gender: $Gender, " +
                                "\n status: $Status \n " +
                                "} \n"
                }
                //Presentar datos en el TextView
                txtJson.text=Cadena
            },
            { error -> println(error.message) } )
        //Añadir la solicitud al RequestQueue.
        RequestQueue?.add(JsonObjectR)
    }
}