package com.example.workmeout.dataBase

import android.content.Context
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.workmeout.Controlador.Controlador
import com.example.workmeout.model.Exercise
import com.example.workmeout.model.Routine
import org.json.JSONArray
import org.json.JSONObject
import com.example.workmeout.ui.me.ExerciseSearchAdapter
import com.example.workmeout.ui.me.RoutineActivity

class RoutineDataBase {

    //Variable que se utilitza para acceder a la base de datos.
    lateinit var requestQ: RequestQueue

    //Método que utilizaremos para guardar la descripcion de la rutina en una base de datos.
    fun guardarRutina(

        context: Context,
        name: String,
        description: String,
        exercise1: Int,
        exercise2: Int,
        exercise3: Int,
        exercise4: Int,
        exercise5: Int,
        exercise6: Int,
        exercise7: Int,
        exercise8: Int,
        exercise9: Int,
        exercise10: Int,
        exercise11: Int,
        exercise12: Int,
        exercise13: Int,
        exercise14: Int,
        exercise15: Int,
        days: Int
    ) {
        val URL: String = "http://192.168.1.41:8080/websercv/routine/registrar.php"
        val stringRequest = object : StringRequest(Request.Method.POST, URL,
            Response.Listener<String> { response ->
                Toast.makeText(context, "Routine registered id: " + response , Toast.LENGTH_SHORT).show()
                Controlador.fillNewRoutineClassId(response.toInt())
                guardarRutinaUsuario(context,response.toInt(),days,exercise1,exercise2,exercise3,exercise4,exercise5,exercise6,exercise7,exercise8,exercise9,exercise10,exercise11,exercise12,exercise13,exercise14,exercise15)
            }, Response.ErrorListener { error ->
                Toast.makeText(context, "ERROR : " + error.toString(), Toast.LENGTH_LONG).show()
            }) {
            override fun getParams(): Map<String, String> {
                var parametros = HashMap<String, String>()
                parametros["name"] = name
                parametros["description"] = description
                parametros["exercise1"]=exercise1.toString()
                parametros["exercise2"]=exercise2.toString()
                parametros["exercise3"]=exercise3.toString()
                parametros["exercise4"]=exercise4.toString()
                parametros["exercise5"]=exercise5.toString()
                parametros["exercise6"]=exercise6.toString()
                parametros["exercise7"]=exercise7.toString()
                parametros["exercise8"]=exercise8.toString()
                parametros["exercise9"]=exercise9.toString()
                parametros["exercise10"]=exercise10.toString()
                parametros["exercise11"]=exercise11.toString()
                parametros["exercise12"]=exercise12.toString()
                parametros["exercise13"]=exercise13.toString()
                parametros["exercise14"]=exercise14.toString()
                parametros["exercise15"]=exercise15.toString()

                return parametros
            }
        }
        requestQ = Volley.newRequestQueue(context)
        requestQ.add(stringRequest);


    }

    //Método que utilizaremos para guardas las rutinas correspndietnes a los usuarios
    fun guardarRutinaUsuario(
        context: Context,
        classid: Int,
        days: Int,
        exercise1: Int,
        exercise2: Int,
        exercise3: Int,
        exercise4: Int,
        exercise5: Int,
        exercise6: Int,
        exercise7: Int,
        exercise8: Int,
        exercise9: Int,
        exercise10: Int,
        exercise11: Int,
        exercise12: Int,
        exercise13: Int,
        exercise14: Int,
        exercise15: Int
    ) {
        val URL: String = "http://192.168.1.41:8080/websercv/routine/registrarUsuario.php"
        val stringRequest = object : StringRequest(Request.Method.POST, URL,
            Response.Listener<String> { response ->
                Toast.makeText(context, "Routine registered id: " + response , Toast.LENGTH_SHORT).show()
                Controlador.fillNewRoutineId(context,response.toInt())
            }, Response.ErrorListener { error ->
                Toast.makeText(context, "ERROR : " + error.toString(), Toast.LENGTH_LONG).show()
            }) {
            override fun getParams(): Map<String, String> {
                var parametros = HashMap<String, String>()
                parametros["classid"] = classid.toString()
                parametros["days"] = days.toString()
                parametros["exercise1"]=exercise1.toString()
                parametros["exercise2"]=exercise2.toString()
                parametros["exercise3"]=exercise3.toString()
                parametros["exercise4"]=exercise4.toString()
                parametros["exercise5"]=exercise5.toString()
                parametros["exercise6"]=exercise6.toString()
                parametros["exercise7"]=exercise7.toString()
                parametros["exercise8"]=exercise8.toString()
                parametros["exercise9"]=exercise9.toString()
                parametros["exercise10"]=exercise10.toString()
                parametros["exercise11"]=exercise11.toString()
                parametros["exercise12"]=exercise12.toString()
                parametros["exercise13"]=exercise13.toString()
                parametros["exercise14"]=exercise14.toString()
                parametros["exercise15"]=exercise15.toString()
                return parametros
            }
        }
        requestQ = Volley.newRequestQueue(context)
        requestQ.add(stringRequest);
    }



    //Método que utilizamos para buscar rutinas
    fun buscarRutina(context: Context, id: Int) {

        var name: String
        var description: String
        var exercise1: Int
        var exercise2: Int
        var exercise3: Int
        var exercise4: Int
        var exercise5: Int
        var exercise6: Int
        var exercise7: Int
        var exercise8: Int
        var exercise9: Int
        var exercise10: Int
        var exercise11: Int
        var exercise12: Int
        var exercise13: Int
        var exercise14: Int
        var exercise15: Int

        val URL: String = "http://192.168.1.41:8080/websercv/routine/buscar.php?id=" + id
        val jsonArrayRequest: JsonArrayRequest = JsonArrayRequest(URL,
            Response.Listener<JSONArray> { response ->
                var jsonObject: JSONObject
                for (i in 0..response.length() - 1) {
                    jsonObject = response.getJSONObject(i);
                    name = jsonObject.getString("name")
                    description = jsonObject.getString("description")
                    exercise1 = jsonObject.getString("exercise1").toInt()
                    exercise2 = jsonObject.getString("exercise2").toInt()
                    exercise3 = jsonObject.getString("exercise3").toInt()
                    exercise4 = jsonObject.getString("exercise4").toInt()
                    exercise5 = jsonObject.getString("exercise5").toInt()
                    exercise6 = jsonObject.getString("exercise6").toInt()
                    exercise7 = jsonObject.getString("exercise7").toInt()
                    exercise8 = jsonObject.getString("exercise8").toInt()
                    exercise9 = jsonObject.getString("exercise9").toInt()
                    exercise10 = jsonObject.getString("exercise10").toInt()
                    exercise11 = jsonObject.getString("exercise11").toInt()
                    exercise12 = jsonObject.getString("exercise12").toInt()
                    exercise13 = jsonObject.getString("exercise13").toInt()
                    exercise14 = jsonObject.getString("exercise14").toInt()
                    exercise15 = jsonObject.getString("exercise15").toInt()

                    Controlador.fillRoutine(name,description)
                }
            }, Response.ErrorListener { error ->

            })

        requestQ = Volley.newRequestQueue(context);
        requestQ.add(jsonArrayRequest)

    }

    //Método que utilizamos para buscar rutinas
    fun buscarRutinaUsuario(context: Context, id: Int) {
        var classid: Int
        var days: Int
        var exercise1: Int
        var exercise2: Int
        var exercise3: Int
        var exercise4: Int
        var exercise5: Int
        var exercise6: Int
        var exercise7: Int
        var exercise8: Int
        var exercise9: Int
        var exercise10: Int
        var exercise11: Int
        var exercise12: Int
        var exercise13: Int
        var exercise14: Int
        var exercise15: Int
        val URL: String = "http://192.168.1.41:8080/websercv/routine/buscarUsuario.php?id=" + id
        val jsonArrayRequest: JsonArrayRequest = JsonArrayRequest(URL,
            Response.Listener<JSONArray> { response ->
                var jsonObject: JSONObject
                for (i in 0..response.length() - 1) {
                    jsonObject = response.getJSONObject(i);
                    classid = jsonObject.getString("classid").toInt()
                    days = jsonObject.getString("days").toInt()
                    exercise1 = jsonObject.getString("exercise1").toInt()
                    exercise2 = jsonObject.getString("exercise2").toInt()
                    exercise3 = jsonObject.getString("exercise3").toInt()
                    exercise4 = jsonObject.getString("exercise4").toInt()
                    exercise5 = jsonObject.getString("exercise5").toInt()
                    exercise6 = jsonObject.getString("exercise6").toInt()
                    exercise7 = jsonObject.getString("exercise7").toInt()
                    exercise8 = jsonObject.getString("exercise8").toInt()
                    exercise9 = jsonObject.getString("exercise9").toInt()
                    exercise10 = jsonObject.getString("exercise10").toInt()
                    exercise11 = jsonObject.getString("exercise11").toInt()
                    exercise12 = jsonObject.getString("exercise12").toInt()
                    exercise13 = jsonObject.getString("exercise13").toInt()
                    exercise14 = jsonObject.getString("exercise14").toInt()
                    exercise15 = jsonObject.getString("exercise15").toInt()

                    val rutina : Routine = Routine(id,classid,"","",days)
                    //TODO lo mismo con los demás ejercicios.
                    Controlador.postRoutine(context, rutina)
                    buscarRutina(context,classid)
                }
            }, Response.ErrorListener { error ->
            })

        requestQ = Volley.newRequestQueue(context);
        requestQ.add(jsonArrayRequest)

    }


}