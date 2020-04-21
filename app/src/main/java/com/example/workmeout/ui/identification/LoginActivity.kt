package com.example.workmeout.ui.identification

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import com.example.workmeout.ui.MainActivity
import com.example.workmeout.R
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText

    private var email:String = ""
    private var password:String = ""
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        editTextEmail = findViewById(R.id.edttxt_user_login)
        editTextPassword = findViewById(R.id.edttxt_password_login)

        mAuth = FirebaseAuth.getInstance();
    }

     //Function called when login button is clicked
    fun loginBtn(view: View){
         email = editTextEmail.text.toString()
         password = editTextPassword.text.toString()
         /*
         mAuth.signInWithEmailAndPassword(email, password)
             .addOnCompleteListener(this) { task ->
                 if (task.isSuccessful) {
                     // Sign in success, update UI with the signed-in user's information
                     val user = mAuth.currentUser

                     val logInt= Intent(this, MainActivity::class.java)
                     startActivity(logInt)

                 } else {
                     // If sign in fails, display a message to the user.
                     Toast.makeText(baseContext, "Authentication failed.",
                         Toast.LENGTH_SHORT).show()
                 }
             }*/

         if(checkLogin()) {
             val logInt= Intent(this, MainActivity::class.java)
             startActivity(logInt)
         }
     }

    //Function called when register button is clicked
    fun registerBtn(view:View){
        val regInt= Intent(this, RegisterActivity::class.java)
        startActivity(regInt)
    }

    //Function called when forgotPassword button is clicked
    fun forgonPasswordBtn(view:View){
        val regFInt = Intent(this,
            RecoverPasswordActivity::class.java)
        startActivity(regFInt)
    }

    //TODO
    /***
     * Habría que ver que las casilla no esten vacias al principio.
     * Luego habría que comprovar en la base de datos las cosas.
     * Finalmente meterle al intent de la pantalla siguiente el nombre del usuario o su id.
     */
    private fun checkLogin() : Boolean{
        return true
    }
}