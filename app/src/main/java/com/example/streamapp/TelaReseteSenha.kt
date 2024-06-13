package com.example.streamapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.streamapp.databinding.ActivityTelaReseteSenhaBinding
import com.example.streamapp.utils.exibirMensagem
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class TelaReseteSenha : AppCompatActivity() {
    private val binding by lazy {
       ActivityTelaReseteSenhaBinding.inflate( layoutInflater )
    }

    private val firebase by lazy {
        FirebaseAuth.getInstance()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView( binding.root )

        binding.btnAterarSenhaVoltarTelaLogin.setOnClickListener {
            val intent = Intent(this, TelaDeLoginUsuario::class.java)
            startActivity(intent)

        }

        binding.btnAlterarSenha.setOnClickListener {
            val email = binding.editEmailResetSenha.text.toString()
            if (email.isEmpty()) {
                exibirMensagem("Informe seu email cadastrado.")

            }
            else {
                enviarEmailRecuperacao(email)
            }
        }

    }

    private fun enviarEmailRecuperacao(email: String){
        firebase.sendPasswordResetEmail(email).addOnCompleteListener { retorno ->
            if (retorno.isSuccessful) {
                exibirMensagem("Email de recuperação enviado.")
            }
            else {
                exibirMensagem("Erro ao enviar email de recuperação!")
            }
        }
        }
    }