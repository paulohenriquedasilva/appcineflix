package com.example.streamapp.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast


@SuppressLint("ShowToast")
fun Activity.exibirMensagem(mensagem: String){
    val toast = Toast.makeText(this, mensagem, Toast.LENGTH_SHORT)
    toast.setGravity(Gravity.TOP,0,50)
    toast.show()
}

fun abrirActivity(context: Context, activityClass: Class<*>, extras: Bundle? = null) {
    val intent = Intent(context, activityClass)
    if (extras != null) {
        intent.putExtras(extras)
    }
    context.startActivity(intent)
}

