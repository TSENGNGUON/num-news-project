package com.example.numnewsapp

import android.os.Bundle
import android.content.Intent
import android.widget.Toast


import androidx.appcompat.app.AppCompatActivity
import com.example.numnewsapp.databinding.LoginActivityBinding
import com.google.firebase.auth.FirebaseAuth


class LoginActivity : AppCompatActivity() {

    lateinit var binding:LoginActivityBinding
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = LoginActivityBinding.inflate(layoutInflater)

        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.btnLogin.setOnClickListener{
            val email = binding.txtEmail.text.toString()
            val password = binding.txtPassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()){
                firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener{
                   if(it.isSuccessful){
                       Toast.makeText(this,"Login Successful",Toast.LENGTH_SHORT).show()
                       val intent = Intent(this,HomePageActivity::class.java)
                       startActivity(intent);

                   }
                    else
                   {
                       Toast.makeText(this,"Invalid Email or Password!!",Toast.LENGTH_SHORT).show()
                   }
                }
            }else
            {
                Toast.makeText(this,"Empty Fields are not allow",Toast.LENGTH_SHORT).show()
            }
        }




        binding.forgotPasswordLink.setOnClickListener {
            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }

        binding.signUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

}
}