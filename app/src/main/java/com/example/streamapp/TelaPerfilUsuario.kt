package com.example.streamapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.streamapp.databinding.ActivityTelaPerfilUsuarioBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore

class TelaPerfilUsuario : AppCompatActivity() {
    private val binding by lazy {
        ActivityTelaPerfilUsuarioBinding.inflate(layoutInflater)
    }

    private val firestore by lazy {
        FirebaseFirestore.getInstance()
    }

    private val fireAuth by lazy {
        FirebaseAuth.getInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( binding.root )

        carregarDadosUsuarioLogado()
        binding.imgInserirFotoUsuario.setImageResource(R.drawable.usuario_sem_foto)
        binding.btnVoltarTelaHome.setOnClickListener {
            finish()
        }

        binding.txtPerfilUsuarioSairConta.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val sairConta = Intent(this, TelaDeLoginUsuario::class.java)
            startActivity(sairConta)
            finish()

        }

        binding.txtUserAlterarAvatar.setOnClickListener {
            val intent = Intent(this, TelaAvatares::class.java)
            startActivity(intent)
        }

    }

    private fun carregarDadosUsuarioLogado() {
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            firestore.collection("usuarios").document(user.uid)
                .get()
                .addOnSuccessListener {
                    binding.txtNomeUsuarioLogado.text = it.getString("nome")
                    binding.txtEmailUsuarioLogado.text = it.getString("email")
                }
        }
        }

            }


