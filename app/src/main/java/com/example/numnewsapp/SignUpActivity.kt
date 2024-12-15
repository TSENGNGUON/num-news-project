package com.example.numnewsapp
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import android.widget.Toast
import com.example.numnewsapp.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase


class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)


        binding = ActivitySignUpBinding.inflate(layoutInflater)

        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()


        //Back to Login
        binding.registered.setOnClickListener{


            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)


        }


        //  Got SignUp Button By Binding
        // Sign Up Button
        binding.btnNext.setOnClickListener{

            val email = binding.email.text.toString()
            val password = binding.passWord.text.toString()
            val confirmPassword = binding.confirmPass.text.toString()

            //Check Validation
            if (email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()){
                if (password == confirmPassword)
                {
                    firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
                        if (it.isSuccessful)
                        {
                            Toast.makeText(this,"Sign Up Successful",Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, LoginActivity::class.java)

                            startActivity(intent)

                        }else{
                            Toast.makeText(this,it.exception.toString(),Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
            else
            {
                Toast.makeText(this,"Empty fields Are not Allowed!!",Toast.LENGTH_SHORT).show()
            }
        }

        //Back Button To Login Form
        val txtBack: ImageView = findViewById(R.id.back_)
        txtBack.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}