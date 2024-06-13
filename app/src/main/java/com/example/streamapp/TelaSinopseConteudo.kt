package com.example.streamapp

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.webkit.WebViewClient
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.example.streamapp.databinding.ActivityTelaSinopseConteudoBinding

class TelaSinopseConteudo : AppCompatActivity() {
    private val binding by lazy {
        ActivityTelaSinopseConteudoBinding.inflate(layoutInflater)
    }
    @SuppressLint("SetJavaScriptEnabled", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( binding.root )

        val dadosConteudoEscolhido  = intent.extras
        val url = dadosConteudoEscolhido?.getString("url")
        if (url != null) {
            carregarWebView(url)
            binding.txtNomeConteudo.text = dadosConteudoEscolhido.getString("nome")
            binding.txtSinopseConteudo.text = dadosConteudoEscolhido.getString("sinopse")
            binding.txtGenero.text = "Genero: "+dadosConteudoEscolhido.getString("genero")
        }

        binding.imgLogo.setOnClickListener {
            val intent = Intent(this, TelaHome::class.java)
            startActivity(intent)
        }

        binding.imgUsuarioLogado.setOnClickListener {
            val  intent = Intent(this, TelaPerfilUsuario::class.java)
            startActivity(intent)
        }


    }
    @SuppressLint("SetJavaScriptEnabled")
    private fun carregarWebView(url: String){
        val vW = binding.vWPlayConteudo
        vW.webViewClient = WebViewClient()
        vW.loadData(url,"text/html","utf-8")
        vW.settings.javaScriptEnabled = true
        vW.loadUrl(url)
    }

}