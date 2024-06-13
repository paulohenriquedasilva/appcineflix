// package com.example.streamapp
//
// import android.os.Bundle
// import androidx.activity.enableEdgeToEdge
// import androidx.appcompat.app.AppCompatActivity
// import com.example.streamapp.bancoDados.DataBaseHelper
// import com.example.streamapp.databinding.AgrupadorLayoutBinding
//
//
//
// class MainActivity : AppCompatActivity() {
//
// private val binding by lazy {
//
// //ActivityMainBinding.inflate(layoutInflater)
//
// }
//
//
// private val bancoDados by lazy {
// DataBaseHelper(this)
// }
//
// override fun onCreate(savedInstanceState: Bundle?) {
// super.onCreate(savedInstanceState)
// enableEdgeToEdge()
//
// //setContentView(R.layout.activity_main)
// setContentView( binding.root )
//
// ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
// val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
// v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
// insets
// }
//
// with( binding ){
//
// //Area em que são acessados os componentes e os seu respectivos eventos
//
// //estrutura = componente > evento
// /*button99.setOnClickListener {
//
// }*/
// }
//
//
//
// }
//
// }