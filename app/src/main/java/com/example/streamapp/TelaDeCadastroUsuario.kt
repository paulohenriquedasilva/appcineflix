package com.example.streamapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
/*import androidx.activity.enableEdgeToEdge*/
import androidx.appcompat.app.AppCompatActivity
/*import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat*/
import com.example.streamapp.databinding.ActivityTelaDeCadastroUsuarioBinding
import com.example.streamapp.models.Usuario
import com.example.streamapp.utils.exibirMensagem
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

class TelaDeCadastroUsuario : AppCompatActivity() {
    private lateinit var nome: String
    private lateinit var email: String
    private lateinit var senha: String

    private val binding by lazy {
        ActivityTelaDeCadastroUsuarioBinding.inflate(layoutInflater)
    }

    private val storage by lazy {
        FirebaseStorage.getInstance()
    }

    private val firebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    private val firestore by lazy {
        FirebaseFirestore.getInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        /*binding.imgInserirFotoUsuario.setImageResource(R.drawable.usuario_sem_foto)
        binding.txtEscolherAvatar.setOnClickListener {
            val  intent = Intent(this, TelaAvatares::class.java)
            startActivity(intent)
        }*/

        binding.btnCadastroVoltarTelaHome.setOnClickListener {
            val intent = Intent(this, TelaDeLoginUsuario::class.java)
            startActivity(intent)
        }

        /*binding.txtEscolherAvatar.setOnClickListener {
            val chamarTelaAvatar = Intent(this, TelaAvatares::class.java)
            startActivity(chamarTelaAvatar)
        }*/

        binding.btnConfirmarCadastro.setOnClickListener {
            nome = binding.editNome.text.toString()
            email = binding.editEmail.text.toString()
            senha = binding.editSenha.text.toString()

            if (validarCampos(nome, email, senha)) {
                cadastrarUsuario(email, senha)
            }
        }

    }

    private fun subirFotoUsuario(view: View) {
        val foto = view as ImageView
        binding.imgInserirFotoUsuario.setImageURI(Uri.parse(foto.toString()))
    }

    private fun cadastrarUsuario(email: String, senha: String) {
        firebaseAuth.createUserWithEmailAndPassword(
            email, senha
        ).addOnCompleteListener { resultado ->
            if (resultado.isSuccessful) {

                val idUsuario = resultado.result.user?.uid
                if (idUsuario != null) {
                    val usuario = Usuario(
                        idUsuario, nome, email
                    )
                    salvarUsuario(usuario)
                }

            }
        }.addOnFailureListener { erro ->
            try {
                throw erro
            } catch (
                erroCredenciaisInvalidas: FirebaseAuthInvalidCredentialsException
            ) {
                exibirMensagem("E-mail inválido, digite outro.")

            } catch (
                errEmailExistente: FirebaseAuthUserCollisionException
            ) {
                exibirMensagem("E-mail não disponível")
            } catch (
                erroSenhaFraca: FirebaseAuthWeakPasswordException
            ) {
                exibirMensagem("Senha fraca. Crie uma senha com números, letras e caracteres especiais.")
            }
        }

    }

    private fun salvarUsuario(usuario: Usuario) {
        firestore.collection("usuarios")
            .document(usuario.id)
            .set(usuario).addOnSuccessListener {
                exibirMensagem("Cadastro realizado com sucesso.")
                startActivity(
                    Intent(applicationContext, TelaHome::class.java)
                )
            }.addOnFailureListener {
                exibirMensagem("Erro ao realizar cadastro")
            }
    }

    private fun validarCampos(nome: String, email: String, senha: String): Boolean {
        return if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
            exibirMensagem("Por favor, preencha todos os campos!")
            false
        }else{
            exibirMensagem("Login efetuado.")
            true
        }
    }
}