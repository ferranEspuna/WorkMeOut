package com.example.workmeout.model

import com.example.workmeout.model.Routine

class Usuari{

    var id: Int;
    var userName: String
    var password: String
    var email: String
    var phoneNumber: Int
    var age: Int
    var weight: Int = 0;
    var height: Int = 0;
    var RutineList: ArrayList<Routine> = ArrayList();

    constructor(id:Int, userName: String, password: String, email: String, phoneNumber: Int, age: Int){
        this.id = id;
        this.userName = userName
        this.password = password
        this.email = email
        this.phoneNumber = phoneNumber
        this.age = age
    }
    constructor(id:Int, userName: String, password: String, email: String, phoneNumber: Int, age: Int, RutineList: ArrayList<Routine>){
        this.id = id;
        this.userName = userName
        this.password = password
        this.email = email
        this.phoneNumber = phoneNumber
        this.age = age
        this.RutineList = RutineList;
    }

    fun addRutine(Rutine: Routine){
        if(!isRutine(Rutine.name)){
            RutineList.add(Rutine)
        }else{
            //NOTIFICACION QUE NO SE PUEDE AÑADIR PORQUE YA ESTA
        }
    }

    fun deleteRutine(rutineName: String){
        var positionRutine = rutinePosition(rutineName);
        if(positionRutine == -1){
            //Mensaje de que no existe la Routine i no se puede eliminar
        }else{
            RutineList.removeAt(positionRutine)
        }
    }

    fun isRutine(RutineName: String): Boolean {
        val RutineListIterator = RutineList.iterator()
        while(RutineListIterator.hasNext()){
            if(RutineListIterator.next().name == RutineName)
                return true
        }
        return false
    }

    fun rutinePosition(RutineName: String): Int{
        val RutineListIterator = RutineList.iterator()
        while(RutineListIterator.hasNext()){
            var mom : Routine = RutineListIterator.next()
            if(mom.name == RutineName){
                return RutineList.indexOf(mom)
            }
        }
        return -1;
    }
}