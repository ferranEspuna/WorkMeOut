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
import com.example.workmeout.ui.me.ExerciseSearchAdapter
import org.json.JSONArray
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class ExerciseDataBase {

    //Variable que se utilitza para acceder a la base de datos.
    lateinit var requestQ : RequestQueue

    //Método que utilizaremos para guardar nuevos ejercicios en la base de datos.
    fun guardarEjercicio(context: Context, name : String, description: String, reps : Int, weight : Int){
        val URL : String = "http://192.168.1.41:8080/websercv/exercise/registrar.php"
        val stringRequest = object: StringRequest(Request.Method.POST, URL,
            Response.Listener<String> { response ->
                Toast.makeText(context, "Exercise registered id: " + response , Toast.LENGTH_SHORT).show()
                //Controlador.fillExerciseClassId(response.toInt()) TODO
                guardarEjercicioUsuario(context,response.toInt(),reps,weight,0,0,0,0,0,0,0,0,0,0,0,0,0,0) //Guardamos la rutina del usuario.
            }, Response.ErrorListener { error ->
                Toast.makeText(context,"ERROR : " + error.toString(), Toast.LENGTH_LONG).show()
            }) {
            override fun getParams(): Map<String, String> {
                var parametros = HashMap<String,String>()
                parametros["name"] = name
                parametros["description"] = description
                return parametros
            }
        }
        requestQ= Volley.newRequestQueue(context)
        requestQ.add(stringRequest);
    }

    //Método que utilizaremos para guardar nuevos ejercicios del usuario.
    fun guardarEjercicioUsuario(context: Context, classid: Int, reps : Int, weight : Int, day1 : Int, weight1 : Int, day2 : Int, weight2 : Int, day3 : Int, weight3 : Int, day4 : Int, weight4 : Int, day5: Int, weight5 : Int, day6 : Int, weight6 : Int, day7 : Int, weight7 : Int){
        val URL : String = "http://192.168.1.41:8080/websercv/exercise/registrarUsuario.php"
        val stringRequest = object: StringRequest(Request.Method.POST, URL,
            Response.Listener<String> { response ->
                Toast.makeText(context, "Exercise user registered id: " + response , Toast.LENGTH_SHORT).show()
                //Controlador.fillNewExerciseId(response.toInt()) TODO
                //Faltaria meter el objeto ejercicio aqui dentro del objeto rutina correspondiente. Mirar el método de la id.
                Controlador.saveExerciseIdOnRutine(context,response.toInt(),classid)
            }, Response.ErrorListener { error ->
                Toast.makeText(context,"ERROR : " + error.toString(), Toast.LENGTH_LONG).show()
            }) {
            override fun getParams(): Map<String, String> {
                var parametros = HashMap<String,String>()
                parametros["classid"] = classid.toString()
                parametros["reps"] = reps.toString()
                parametros["weight"] = weight.toString()
                parametros["day1"]=day1.toString()
                parametros["weight1"]=weight1.toString()
                parametros["day2"]=day2.toString()
                parametros["weight2"]=weight2.toString()
                parametros["day3"]=day3.toString()
                parametros["weight3"]=weight3.toString()
                parametros["day4"]=day4.toString()
                parametros["weight4"]=weight4.toString()
                parametros["day5"]=day5.toString()
                parametros["weight5"]=weight5.toString()
                parametros["day6"]=day6.toString()
                parametros["weight6"]=weight6.toString()
                parametros["day7"]=day7.toString()
                parametros["weight7"]=weight7.toString()
                return parametros
            }
        }
        requestQ= Volley.newRequestQueue(context)
        requestQ.add(stringRequest);
    }

    fun matchExercise(context: Context, partialName:String, adapter:ExerciseSearchAdapter){
        var name: String
        var description: String
        val URL : String = "http://192.168.1.41:8080/websercv/exercise/buscar_match.php?search="+partialName
        var list: ArrayList<Exercise>
        val jsonArrayRequest : JsonArrayRequest = JsonArrayRequest(URL,
            Response.Listener<JSONArray>{ response->
                var jsonObject: JSONObject
                list = ArrayList()
                for(i in 0..response.length()-1){
                    jsonObject=response.getJSONObject(i);
                    name = jsonObject.getString("name")
                    description = jsonObject.getString("description")
                    //list.add(Exercise(name, description))TODO

                    adapter.submitList(list)
                    adapter.notifyDataSetChanged()

                }
            }, Response.ErrorListener { error->
                Toast.makeText(context, "No matching exercises found.", Toast.LENGTH_SHORT).show()
                list = ArrayList()
                adapter.submitList(list)
                adapter.notifyDataSetChanged()
            })

        requestQ= Volley.newRequestQueue(context);
        requestQ.add(jsonArrayRequest)
    }

    //Método que utilizamos para buscar ejercicios en la base de datos.
    fun buscarEjercicio(
        context: Context,
        ejercicioUsuaro: Exercise
    ){
        var name : String
        var description : String
        val URL : String = "http://192.168.1.41:8080/websercv/exercise/buscar.php?id="+ejercicioUsuaro.classId.toString()
        val jsonArrayRequest : JsonArrayRequest = JsonArrayRequest(URL,
            Response.Listener<JSONArray>{ response->
                var jsonObject: JSONObject
                for(i in 0..response.length()-1){
                    jsonObject=response.getJSONObject(i)
                    name = jsonObject.getString("name")
                    description = jsonObject.getString("description")
                    ejercicioUsuaro.name = name
                    ejercicioUsuaro.description = description

                    //TODO llamar al controlador para que lo muestre
                }
            }, Response.ErrorListener { error->

            })

        requestQ= Volley.newRequestQueue(context);
        requestQ.add(jsonArrayRequest)

    }

    fun buscarEjercicioUsuario(context: Context, id: Int, routine:Routine){

        var classId:Int
        var reps: Int
        var weight: Int
        var days: ArrayList<Date> = ArrayList()
        var weights: ArrayList<Int> = ArrayList()
        var tmp:String

        val URL : String = "http://192.168.1.41:8080/websercv/exercise/buscarUsuario.php?id="+id//todo
        val jsonArrayRequest : JsonArrayRequest = JsonArrayRequest(URL,
            Response.Listener<JSONArray>{ response->
                var jsonObject: JSONObject
                for(i in 0..response.length()-1){

                    jsonObject=response.getJSONObject(i)

                    classId = jsonObject.getString("classid").toInt()
                    reps = jsonObject.getString("reps").toInt()
                    weight = jsonObject.getString("weight").toInt()

                    for(x in 1..7){

                        tmp = jsonObject.getString("day$x")
                        if(tmp != "0"){

                            days.add( SimpleDateFormat("dd/MM/yyyy").parse(tmp))
                            weights.add(jsonObject.getString("weight$x").toInt())
                        }
                    }

                    val ejercicioUsuario:Exercise = Exercise(id, classId,"", reps, "", weight, days, weights)
                    buscarEjercicio(context, ejercicioUsuario)
                    routine.exercises_class.add(ejercicioUsuario)
                }



            }, Response.ErrorListener { error->

            })

        requestQ= Volley.newRequestQueue(context);
        requestQ.add(jsonArrayRequest)

    }


    //TODO la función para buscar coincidencias que llamara al controlador para mostrarlas.

}