package com.example.konkurs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.konkurs.databinding.ActivityLoginBinding
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var intent = Intent(applicationContext, LoginActivity::class.java)
        var main = Intent(applicationContext, MainActivity::class.java)
        var reg = Intent(applicationContext, RegisterActivity::class.java)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener{
            if(binding.editEmail.text.toString().isEmpty() || binding.editPass.text.toString().isEmpty()){
                Toast.makeText(applicationContext, "Fields is empty", Toast.LENGTH_SHORT).show()
            }else{
                FirebaseAuth.getInstance().signInWithEmailAndPassword(binding.editEmail.text.toString(), binding.editPass.text.toString())
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful)
                            startActivity(main)
                    }
            }
        }

        binding.btnReg.setOnClickListener{
            startActivity(reg)
        }
    }
}