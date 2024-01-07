package com.example.mula

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.mula.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //initiating firebase
        firebaseAuth = FirebaseAuth.getInstance()

        binding.btnLogin.setOnClickListener {
            val b_email = binding.rEmail.text.toString()
            val b_pass = binding.txtPassword.text.toString()

            if (b_email.isNotEmpty() && b_pass.isNotEmpty()) {

                firebaseAuth.signInWithEmailAndPassword(b_email, b_pass).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val intent = Intent(this, Dashboard::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()

                    }
                }
            } else {
                Toast.makeText(this, "Empty Fields Are not Allowed !!", Toast.LENGTH_SHORT).show()

            }
        }

        val btn_RegLogin: Button = findViewById(R.id.btnRegLogin)


        btn_RegLogin.setOnClickListener {
            startActivity(Intent(this, Register::class.java))
            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
        }

    }
   /* override fun onStart() {
        super.onStart()

        if(firebaseAuth.currentUser != null){
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
        }
    }*/
}