package com.example.streamapp
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.streamapp.databinding.ActivityTelaDeLoginUsuarioBinding
import com.example.streamapp.utils.exibirMensagem
import com.google.firebase.auth.FirebaseAuth

class TelaDeLoginUsuario : AppCompatActivity() {

    private val binding by lazy {
        ActivityTelaDeLoginUsuarioBinding.inflate(layoutInflater)
    }

    private val firebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       setContentView( binding.root )

        binding.btnLogin.setOnClickListener {
            val vEmailUsuario = binding.editEmailLogin.text.toString()
            val vSenha = binding.editSenhaLogin.text.toString()

            if (vEmailUsuario.isEmpty() || vSenha.isEmpty()) {
                exibirMensagem("Por favor, preencha todos os campos!")
            }else{
                firebaseAuth.signInWithEmailAndPassword(vEmailUsuario, vSenha).addOnCompleteListener { autenticacao ->
                    if (autenticacao.isSuccessful){
                        exibirMensagem("Login efetuado com sucesso.")
                        val intent = Intent(this, TelaHome::class.java)
                        startActivity(intent)
                    }else{
                        exibirMensagem("Erro ao tentar realizar login! " +
                                                 "Verifique seus dados antes de tentar novamente.")
                    }
                }
            }
        }

        binding.txtCriarConta.setOnClickListener {
            val intent = Intent(this, TelaDeCadastroUsuario::class.java)
            startActivity(intent)
        }

        binding.txtRecuperarSenha.setOnClickListener {
            val intent = Intent(this, TelaReseteSenha::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()

        val usuarioAtual = FirebaseAuth.getInstance().currentUser

        if ( usuarioAtual != null ){
            val intent = Intent(this, TelaHome::class.java)
            startActivity( intent )
        }
    }
}