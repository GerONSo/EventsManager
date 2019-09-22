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
