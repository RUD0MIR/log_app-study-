package com.example.newapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.newapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private var launcher: ActivityResultLauncher<Intent>? = null

    private var login = ""
    private var password = ""
    private var userName = ""
    private var isSignIn = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent: Intent = Intent(this@MainActivity,LogScreen::class.java)

        binding.signInBtn.setOnClickListener {
            isSignIn = true
            intent.putExtra(Constants.SIGN_STATE, isSignIn)
            launcher?.launch(intent)

        }
        binding.signUpBtn.setOnClickListener {
            isSignIn = false
            intent.putExtra(Constants.SIGN_STATE, isSignIn)
            launcher?.launch(intent)
        }

        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                result: ActivityResult ->
            if (result.resultCode == RESULT_OK) {
                isSignIn = result.data?.getBooleanExtra(Constants.SIGN_STATE, false)!!

                if (isSignIn) {
                    val currentLogin = result.data?.getStringExtra(Constants.LOGIN)
                    val currentPassword = result.data?.getStringExtra(Constants.PASSWORD)
                    if (login == currentLogin && password == currentPassword) {
                        binding.userNameTv.text = userName
                    } else {
                        binding.userNameTv.text = "Такого аккаунта не сушествует!"
                        Toast.makeText(this, isSignIn.toString(), Toast.LENGTH_SHORT).show()
                    }

                } else {
                    login = result.data?.getStringExtra(Constants.LOGIN).toString()
                    password = result.data?.getStringExtra(Constants.PASSWORD).toString()
                    userName = result.data?.getStringExtra(Constants.USER_NAME).toString()

                    binding.userNameTv.text = userName
                }
            }
        }
    }
}