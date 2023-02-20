package com.example.newapp

import android.app.Activity
import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.newapp.databinding.ActivityLogScreenBinding
import com.example.newapp.databinding.ActivityMainBinding

class LogScreen : AppCompatActivity() {

    private lateinit var binding: ActivityLogScreenBinding

    private var isSignIn = false


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLogScreenBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        isSignIn = intent.getBooleanExtra(Constants.SIGN_STATE, false)

        if (!isSignIn) { binding.unameEt.visibility = View.VISIBLE }

        binding.resultBtn.setOnClickListener {
            if (isSignIn) {
//                val i = Intent()
                intent.putExtra(Constants.LOGIN, binding.loginEt.text.toString())
                intent.putExtra(Constants.PASSWORD, binding.passwordEt.text.toString())

                intent.putExtra(Constants.SIGN_STATE, isSignIn)
                setResult(RESULT_OK, intent)

                finish()
            } else {
                intent.putExtra(Constants.LOGIN, binding.loginEt.text.toString())
                intent.putExtra(Constants.PASSWORD, binding.passwordEt.text.toString())
                intent.putExtra(Constants.USER_NAME, binding.unameEt.text.toString())

                intent.putExtra(Constants.SIGN_STATE, isSignIn)

                setResult(RESULT_OK, intent)

                finish()
            }
        }
    }
}