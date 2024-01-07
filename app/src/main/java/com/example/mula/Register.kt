package com.example.mula

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.mula.databinding.ActivityRegisterBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class Register : AppCompatActivity() {

    private lateinit var R_email: TextInputEditText
    private lateinit var password: TextInputEditText
    private lateinit var cpassword: TextInputEditText
    private lateinit var RegButton: Button
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //initializing firabse
        firebaseAuth = FirebaseAuth.getInstance()

        val btn_LogRegister: Button = findViewById(R.id.btnLogRegister)
        // slider animation to Login
        btn_LogRegister.setOnClickListener {
            onBackPressed()
        }

        // declaring passwords
       password = findViewById(R.id.txtPassword)
        cpassword = findViewById(R.id.txtCpassword)
        RegButton = findViewById(R.id.btnRegister)

        //binding button
        binding.btnRegister.setOnClickListener{
            val b_email = binding.rEmail.text.toString()
            val b_pass = binding.txtPassword.text.toString()
            val b_cpass = binding.txtCpassword.text.toString()

            if (b_email.isNotEmpty() && b_pass.isNotEmpty() && b_cpass.isNotEmpty()) {
                if (b_pass == b_cpass) {

                    firebaseAuth.createUserWithEmailAndPassword(b_email, b_pass).addOnCompleteListener {
                        if (it.isSuccessful) {
                            val intent = Intent(this, Link::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()

                        }
                    }
                } else {
                    Toast.makeText(this, "Password is not matching", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Empty Fields Are not Allowed !!", Toast.LENGTH_SHORT).show()

            }
        }



        //declaring email
        /*R_email = findViewById(R.id.txtPassword)

        // Set click listener for the button
        RegButton.setOnClickListener {
            // Get text from TextInputEditText fields
            val emailText = R_email.text.toString()

            //To check if email is valid
            if (!isValidEmail(emailText)) {
                Toast.makeText(this, "Invalid email address", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }




        }*/
    }

    // Function to validate email
    private fun isValidEmail(email: String): Boolean {
        val emailRegex = Regex("^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})")
        return email.matches(emailRegex)
    }

    //function to go back to Login
    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right)
    }

}