package com.example.streamapp

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.streamapp.databinding.ActivityTelaAvataresBinding

class TelaAvatares : AppCompatActivity() {
    private val binding by lazy {
        ActivityTelaAvataresBinding.inflate(layoutInflater)
    }
    private lateinit var imagem: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.btnAvataresVoltarTelaHome.setOnClickListener {
            val intent = Intent(this, TelaHome::class.java)
            startActivity(intent)
        }
        /*//val fotoUsuario = findViewById<ImageView>(R.id.img_InserirFotoUsuario)
        binding.btnEscolherAvatar.setOnClickListener {
            if (imagem.toString().isEmpty()) {
                exibirMensagem("Escolha uma foto!")
            } else {
                exibirMensagem("Imagem adicionada ao seu perfil.")
            }
        }
        fun uriImagem(view: View) {
            imagem = view as ImageView
        }*/
    }
}