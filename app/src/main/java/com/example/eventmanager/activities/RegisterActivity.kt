package com.example.eventmanager.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.example.eventmanager.R
import com.example.eventmanager.data.User
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    companion object{
        lateinit var user: User
        val superusers = listOf(User("glebik8","123456", true), User("seriy", "234567", true),
            User("stepa","345678", true))
        val users = listOf(User("glebik81","123456", true), User("seriy1", "234567", true),
            User("stepa1","345678", true))
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        btn_register_final.setOnClickListener {
            val email = et_login_final.text.toString()
            val password = et_password_final.text.toString()
            val name = et_name_final.text.toString()
            val surname = et_surname_final.text.toString()

        }
    }


}
