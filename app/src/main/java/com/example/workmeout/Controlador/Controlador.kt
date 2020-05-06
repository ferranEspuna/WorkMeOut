package com.example.workmeout.Controlador

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import com.example.workmeout.model.User
import com.example.workmeout.dataBase.BaseDatos
import com.example.workmeout.model.Routine
import com.example.workmeout.ui.MainActivity
import com.example.workmeout.ui.identification.RegisterActivity
import com.example.workmeout.ui.me.ExerciseSearchAdapter


object Controlador{

    var baseDatos : BaseDatos = BaseDatos()
    var currentUser : User? = null

    //Guarda un usuario con los datos introducidos en la base de datos.
    fun register(context: Context, username : String, name : String, password: String, email: String, phone : String, age : String, gender : String, weight : String, height : String){
        baseDatos.guardarUsuario(context,username,name,password,email,phone,age,gender,weight,height)
    }

    //Hace una petición de loguear el usuario. La base de datos llamará la función loguear una vez haya respuesta o un Timeout.
    fun loginRequest(context : Context,username:String,password:String){
        currentUser = null //Nos asseguramos de que no haya ningún usuario activo.
        baseDatos.buscarUsuario(context,username,password)
    }

    fun editarUsuario(context: Context, username : String, name : String, password: String, email: String, phone : String, age : String, gender : String, weight : String, height : String){
        var id1 : Int = 0
        var id2 : Int = 0
        var id3 : Int = 0
        var id4 : Int = 0
        var id5 : Int = 0
        if(currentUser != null){
            if(currentUser!!.routine1 != null){
                id1 = currentUser!!.routine1.id
            }
            if(currentUser!!.routine2 != null){
                id2 = currentUser!!.routine2.id
            }
            if(currentUser!!.routine3 != null){
                id3 = currentUser!!.routine3.id
            }
            if(currentUser!!.routine4 != null){
                id4 = currentUser!!.routine4.id
            }
            if(currentUser!!.routine5 != null){
                id5 = currentUser!!.routine5.id
            }
            baseDatos.editarUsuario(context,username,name,password,email,phone,age,gender,weight,height,id1,id2,id3,id4,id5)
        }
    }


    //Loguea el usuario. Es llamada des de la base de datos. //TODO que se llame mas tarde
    fun login(context:Context,password:String){
        if (currentUser == null) {
            Toast.makeText(context, "Incorrect username", Toast.LENGTH_SHORT).show()
        } else {
            if (currentUser!!.password != password) {
                Toast.makeText(context, "Incorrect password", Toast.LENGTH_SHORT).show()
            } else {
                //Accedemos ya dentro de la aplicación.
                val logInt= Intent(context, MainActivity::class.java)
                context.startActivity(logInt)
            }
        }
    }

    fun temporalPrint(view: View){
        Toast.makeText(view.context,"Tenemos las rutinas " +currentUser!!.numberOfRoutines + " PRIMERA "+ currentUser!!.routine1.id+" con nombre "+ currentUser!!.routine1.name+" SEGUNDA "+ currentUser!!.routine2.id+" con nombre "+ currentUser!!.routine2.name+ " TERCERA "+ currentUser!!.routine3.id+" con nombre "+ currentUser!!.routine3.name, Toast.LENGTH_SHORT).show()
    }

    //Comprueba si los datos introducidos són correctos.
    fun checkData(username : String, name : String, password: String, email: String, phone : String, age : String) : Boolean{
        //TODO crear objecte per comprovar les dades i comprovar aquí.
        //En el nombre de usuario no importan mayúsculas i minúsculas para loguearse. Eso si si se imprime se imprimirá con estas.
        return true
    }

    //Guarda la descripción de un nuevo ejercicio la base de datos
    fun registerExercise(context: Context, name : String, description: String){
        baseDatos.guardarEjercicio(context,name,description)
    }

    //Luego enviara la rutina al usuario
    fun registerRoutine(context: Context, name: String,description: String,exercise1: Int, exercise2: Int,exercise3: Int,exercise4: Int,exercise5: Int,exercise6: Int,exercise7: Int,exercise8: Int,exercise9: Int,exercise10: Int,exercise11: Int, exercise12: Int, exercise13: Int,exercise14: Int,exercise15: Int,days : Int){
        var routine : Routine = Routine(0,0,name,description,days)
        postRoutine(context,routine)
        baseDatos.guardarRutina(context,name,description,exercise1,exercise2,exercise3,exercise4,exercise5,exercise6,exercise7,exercise8,exercise9,exercise10,exercise11,exercise12,exercise13,exercise14,exercise15,days)
    }

    //Pide a la base de datos del usuario que vaya cargando las diferentes rutinas.
    fun addRoutinesToUserRequest(context : Context,id1 : Int, id2 : Int, id3 : Int, id4 : Int, id5 : Int){
        if(id1 != 0){
            baseDatos.buscarRutinaUsuario(context,id1)
        }
        if(id2 != 0){
            baseDatos.buscarRutinaUsuario(context,id2)
        }
        if(id3 != 0){
            baseDatos.buscarRutinaUsuario(context,id3)
        }
        if(id4 != 0){
            baseDatos.buscarRutinaUsuario(context,id4)
        }
        if(id5 != 0) {
            baseDatos.buscarRutinaUsuario(context,id5)
        }

    }

    //Postea la rutina ( se llama des de la base de datos ) y pide rellenar los demás campos.
    fun postRoutine(context : Context, routine : Routine){
        when(currentUser!!.numberOfRoutines){
            0-> {
                currentUser!!.numberOfRoutines = 1
                currentUser!!.routine1 = routine }
            1-> {
                currentUser!!.numberOfRoutines = 2
                currentUser!!.routine2 = routine }
            2-> {
                currentUser!!.numberOfRoutines = 3
                currentUser!!.routine3 = routine }
            3-> {
                currentUser!!.numberOfRoutines = 4
                currentUser!!.routine4 = routine }
            4-> {
                currentUser!!.numberOfRoutines = 5
                currentUser!!.routine5 = routine
            }
        }
    }

    //Acaba de meter en las rutinas que se estan cargando los datos.
    fun fillRoutine(name : String, description : String){
        when(currentUser!!.tempRoutineIndex){
            0-> {
                currentUser!!.tempRoutineIndex = 1
                currentUser!!.routine1.name = name
                currentUser!!.routine1.description = description
            }
            1-> {
                currentUser!!.tempRoutineIndex = 2
                currentUser!!.routine2.name = name
                currentUser!!.routine2.description = description
            }
            2-> {
                currentUser!!.tempRoutineIndex = 3
                currentUser!!.routine3.name = name
                currentUser!!.routine3.description = description
            }
            3-> {
                currentUser!!.tempRoutineIndex = 4
                currentUser!!.routine4.name = name
                currentUser!!.routine4.description = description
            }
            4-> {
                currentUser!!.tempRoutineIndex = 5
                currentUser!!.routine5.name = name
                currentUser!!.routine5.description = description
            }
        }
    }

    //Acaba de meter la id procediente de la base de datos. También guardaremos en la base de datos del usuario esta id de rutina.
    fun fillNewRoutineId(context:Context,id:Int){
        if(currentUser!=null){
            when(currentUser!!.numberOfRoutines){
                1-> {
                    currentUser!!.routine1.id = id
                    baseDatos.editarUsuario(context, currentUser!!.userName, currentUser!!.name,currentUser!!.password,currentUser!!.email,
                        currentUser!!.phoneNumber.toString(),
                        currentUser!!.age.toString(),
                        currentUser!!.sex.toString(),
                        currentUser!!.weight.toString(),
                        currentUser!!.height.toString(),id,0,0,0,0)
                }
                2-> {
                    currentUser!!.routine2.id = id
                    baseDatos.editarUsuario(context, currentUser!!.userName, currentUser!!.name,currentUser!!.password,currentUser!!.email,
                        currentUser!!.phoneNumber.toString(),
                        currentUser!!.age.toString(),
                        currentUser!!.sex.toString(),
                        currentUser!!.weight.toString(),
                        currentUser!!.height.toString(),
                        currentUser!!.routine1.id,id,0,0,0)
                }
                3-> {
                    currentUser!!.routine3.id = id
                    baseDatos.editarUsuario(context, currentUser!!.userName, currentUser!!.name,currentUser!!.password,currentUser!!.email,
                        currentUser!!.phoneNumber.toString(),
                        currentUser!!.age.toString(),
                        currentUser!!.sex.toString(),
                        currentUser!!.weight.toString(),
                        currentUser!!.height.toString(),currentUser!!.routine1.id,currentUser!!.routine2.id,id,0,0)
                }
                4-> {
                    currentUser!!.routine4.id = id
                    baseDatos.editarUsuario(context, currentUser!!.userName, currentUser!!.name,currentUser!!.password,currentUser!!.email,
                        currentUser!!.phoneNumber.toString(),
                        currentUser!!.age.toString(),
                        currentUser!!.sex.toString(),
                        currentUser!!.weight.toString(),
                        currentUser!!.height.toString(),currentUser!!.routine1.id,currentUser!!.routine2.id,currentUser!!.routine3.id,id,0)
                }
                5-> {
                    currentUser!!.routine5.id = id
                    baseDatos.editarUsuario(context, currentUser!!.userName, currentUser!!.name,currentUser!!.password,currentUser!!.email,
                        currentUser!!.phoneNumber.toString(),
                        currentUser!!.age.toString(),
                        currentUser!!.sex.toString(),
                        currentUser!!.weight.toString(),
                        currentUser!!.height.toString(),currentUser!!.routine1.id,currentUser!!.routine2.id,currentUser!!.routine3.id,currentUser!!.routine4.id,id)
                }
            }
        }

    }

    //Acaba de meter la id procediente de la base de datos. También guardaremos en la base de datos del usuario esta id de rutina.
    fun fillNewRoutineClassId(id:Int){
        when(currentUser!!.numberOfRoutines){
            1-> {
                currentUser!!.routine1.classid = id
            }
            2-> {
                currentUser!!.routine2.classid = id
            }
            3-> {
                currentUser!!.routine3.classid = id
            }
            4-> {
                currentUser!!.routine4.classid = id
            }
            5-> {
                currentUser!!.routine5.classid = id
            }
        }
    }


    fun matchExercise(context: Context, name:String, adapter:ExerciseSearchAdapter){
        baseDatos.matchExercise(context, name, adapter)
    }





}