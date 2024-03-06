package com.example.konkurs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Debug
import android.util.Log
import android.widget.Toast
import com.example.konkurs.databinding.ActivityLoginBinding
import com.example.konkurs.databinding.ActivityRegisterBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class RegisterActivity : AppCompatActivity() {

private lateinit var binding: ActivityRegisterBinding

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    var intent = Intent(applicationContext, RegisterActivity::class.java)
    var main = Intent(applicationContext, MainActivity::class.java)
    binding = ActivityRegisterBinding.inflate(layoutInflater)
    setContentView(binding.root)

    binding.btnRegister.setOnClickListener{
        if(binding.regEmail.text.toString().isEmpty() || binding.regPass.text.toString().isEmpty() || binding.regNick.text.toString().isEmpty()){
            Toast.makeText(applicationContext, "Fields is empty", Toast.LENGTH_SHORT).show()
        }else{
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(binding.regEmail.text.toString(), binding.regPass.text.toString())
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        var userInfo = HashMap<String, String>()
                        Log.d("TEST", "createdMap")
                        userInfo["email"] = binding.regEmail.text.toString()
                        userInfo["pass"] = binding.regPass.text.toString()
                        userInfo["nick"] = binding.regNick.text.toString()
                        FirebaseDatabase.getInstance("https://konkurs-91da2-default-rtdb.europe-west1.firebasedatabase.app/")
                            .reference.child("users")
                            .child(FirebaseAuth.getInstance().currentUser!!.uid)
                            .setValue(userInfo)
                        startActivity(main)
                    }
                }

        }




        }
    }


}
