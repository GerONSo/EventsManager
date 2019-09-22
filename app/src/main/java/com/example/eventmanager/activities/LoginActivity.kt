package com.example.eventmanager.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.UserHandle
import android.view.View
import android.widget.Toast
import com.example.eventmanager.*
import com.example.eventmanager.data.User
import com.example.eventmanager.extensions.hideKeyboard
import com.example.eventmanager.objects.Animator
import com.example.eventmanager.objects.EditTextChangeHandler
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.layout_password
import kotlinx.android.synthetic.main.activity_register.*
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent

class LoginActivity : AppCompatActivity() {

    companion object{
        lateinit var user: User
    }
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
            user = User(et_login.text.toString(), et_password.text.toString(), true)
            RegisterActivity.superusers.forEach {
                val email = et_login_final.text.toString()
                val password = et_password_final.text.toString()
                if (it.login == email && it.password == password) {
                    RegisterActivity.user = User(email, password, true, "Gleb", "Voitenko")
                    startActivity(Intent(this, EventActivity::class.java))

                }

                RegisterActivity.users.forEach {
                    if (it.login == email && it.password == password) {
                        RegisterActivity.user = User(email, password, false, "Gleb", "Voitenko")
                        startActivity(Intent(this, EventActivity::class.java))

                    }
                }
                Toast.makeText(applicationContext, "Ошибка, повторите ввод", Toast.LENGTH_LONG).show()
            }
        }
        EditTextChangeHandler.addHandler(
            listOf(et_login, et_password),
            btn_login
        )
    }
}
