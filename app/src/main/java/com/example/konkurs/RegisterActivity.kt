package com.example.konkurs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
                    .addOnCompleteListener(OnCompleteListener <AuthResult>{ task ->
                        if(task.isSuccessful){
                            var userInfo = HashMap<String, String>()
                            userInfo.put("email", binding.regEmail.text.toString())
                            userInfo.put("pass", binding.regPass.text.toString())
                            userInfo.put("nick", binding.regNick.text.toString())
                            FirebaseDatabase.getInstance().getReference().child("Users").setValue(userInfo)
                            startActivity(main)
                        }

                    })


            }
        }


    }
}