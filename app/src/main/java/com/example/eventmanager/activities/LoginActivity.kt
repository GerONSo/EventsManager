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
        val superusers = listOf(User("glebik8","123456", true), User("seriy", "234567", true),
            User("stepa","345678", true))
        val users = listOf(User("glebik81","123456", false), User("seriy1", "234567", false),
            User("stepa1","345678", false))
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
            user = User(et_login.text.toString(), et_password.text.toString(), false)
            val email = et_login.text.toString()
            val password = et_password.text.toString()
            var enter: Boolean = false
            superusers.forEach {
                if (it.login == email && it.password == password) {
                    user = User(email, password, true, "Gleb", "Voitenko")
                    enter = true
                    startActivity(Intent(this, EventActivity::class.java))
                }
            }
            if (!enter)
                users.forEach {
                if (it.login == email && it.password == password) {
                    user = User(email, password, false, "Gleb", "Voitenko")
                    enter = true
                    startActivity(Intent(this, EventActivity::class.java))
                }
            }
                Toast.makeText(applicationContext, "Ошибка, повторите ввод", Toast.LENGTH_LONG)
                    .show()
        }
        EditTextChangeHandler.addHandler(
            listOf(et_login, et_password),
            btn_login
        )
    }
}
