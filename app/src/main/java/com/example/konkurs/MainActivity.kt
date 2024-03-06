package com.example.konkurs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Switch
import androidx.fragment.app.Fragment
import com.example.konkurs.bottomnav.chats.ChatFragment
import com.example.konkurs.bottomnav.courses.CoursesFragment
import com.example.konkurs.bottomnav.profile.ProfileFragment
import com.example.konkurs.databinding.ActivityMainBinding
import com.example.konkurs.databinding.FragmentChatsBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
Log.d("TEST", FirebaseAuth.getInstance().currentUser.toString())
        var intent = Intent(applicationContext, LoginActivity::class.java)
        if(FirebaseAuth.getInstance().currentUser == null)
            startActivity(intent)

        supportFragmentManager.beginTransaction().replace(binding.fragmentContainer.id, ChatFragment()).commit()
        binding.bottomNav.selectedItemId = R.id.chats

        var fragmentMap = HashMap<Int, Fragment>()
        fragmentMap[R.id.chats] = ChatFragment()
        fragmentMap[R.id.courses] = CoursesFragment()
        fragmentMap[R.id.profile] = ProfileFragment()

        binding.bottomNav.setOnItemSelectedListener {item ->
            var fragment = fragmentMap[item.itemId]

            if (fragment != null) {
                supportFragmentManager.beginTransaction().replace(binding.fragmentContainer.id, fragment).commit()
            }
            return@setOnItemSelectedListener true
        }
    }
}
