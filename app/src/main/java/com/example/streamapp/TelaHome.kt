package com.example.streamapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.streamapp.databinding.ActivityTelaHomeBinding
import com.example.streamapp.utils.exibirMensagem
import com.google.firebase.firestore.FirebaseFirestore

class TelaHome : AppCompatActivity() {

   private val binding by lazy{
        ActivityTelaHomeBinding.inflate(layoutInflater)
    }

    private val firestore by lazy {
        FirebaseFirestore.getInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( binding.root )

            binding.imgUsuarioLogado.setOnClickListener {
                val telaPerfilUsuario = Intent(this, TelaPerfilUsuario::class.java)
                startActivity(telaPerfilUsuario)
            }
    }

fun onImagemClicada(view: View) {
    val imagemClicada = view as ImageView
    val idImage = resources.getResourceEntryName(imagemClicada.id)
    val intent = Intent(this, TelaSinopseConteudo::class.java)

    firestore.collection("filmes")
        .whereEqualTo("nome_carrossel", idImage)
        .get()
        .addOnSuccessListener { documentSnapshot ->
            if (!documentSnapshot.isEmpty) {
                val dadosConteudo = documentSnapshot.documents[0]
                val url = dadosConteudo.getString("url")
                intent.putExtra("url", url)
                val nome = dadosConteudo.getString("nome")
                intent.putExtra("nome", nome)
                val textoSinopse = dadosConteudo.getString("sinopse")
                intent.putExtra("sinopse", textoSinopse)
                val  textoGenero = dadosConteudo.getString("genero")
                intent.putExtra("genero", textoGenero)
                startActivity(intent)
            } else {
                exibirMensagem("Conteúdo não encontrado!")
            }
        }
        .addOnFailureListener { exception ->
            exibirMensagem("Erro ao buscar dados do conteúdo: $exception")
        }
}

}