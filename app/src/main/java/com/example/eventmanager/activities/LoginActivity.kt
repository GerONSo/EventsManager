package com.example.eventmanager.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.eventmanager.*
import com.example.eventmanager.extensions.hideKeyboard
import kotlinx.android.synthetic.main.activity_login.*
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent

class LoginActivity : AppCompatActivity() {

    private lateinit var listForAnimating : List<View>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initViews()
    }

    private fun initViews() {
        listForAnimating = listOf(layout_login, layout_password, btn_login, btn_register)
        KeyboardVisibilityEvent.setEventListener(this) {
            if (it) {
                Animator.animate(
                    listForAnimating,
                    Animator.ANIMATE_UP_FROM_CENTER
                )
            } else {
                Animator.animate(
                    listForAnimating,
                    Animator.ANIMATE_DOWN
                )
            }
        }
        btn_register.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            hideKeyboard()
            Animator.animate(
                listForAnimating,
                Animator.ANIMATE_DOWN
            )
            startActivity(intent)

        }
        btn_login.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        EditTextChangeHandler.addHandler(
            listOf(et_login, et_password),
            btn_login
        )
    }
}