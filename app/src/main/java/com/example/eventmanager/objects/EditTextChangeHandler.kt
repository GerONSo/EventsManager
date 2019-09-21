package com.example.eventmanager.objects

import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText

object EditTextChangeHandler {
    fun addHandler(args : List<EditText>, targetButton : Button) {
        args.forEach {
            it.addTextChangedListener(object : TextWatcher {

                override fun afterTextChanged(s: Editable) {}

                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    var flag = false
                    for(editText in args) {
                        if(editText.text?.isEmpty()!!) {
                            flag = true
                        }
                    }
                    if (!flag) {
                        targetButton.alpha = 1f
                        targetButton.isEnabled = true
                    }
                    else {
                        targetButton.isEnabled = false
                        targetButton.alpha = 0.5f
                    }
                }
            })
        }
    }
}