package com.example.konkurs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
Log.d("TEST", FirebaseAuth.getInstance().currentUser.toString())
        var intent = Intent(applicationContext, LoginActivity::class.java)
        if(FirebaseAuth.getInstance().currentUser == null)
            startActivity(intent)
    }
}