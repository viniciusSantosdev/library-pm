package br.com.etecmatao.biblioteca.data

import android.content.Context
import android.os.AsyncTask
import br.com.etecmatao.biblioteca.R
import br.com.etecmatao.biblioteca.model.Usuario
import com.google.android.material.snackbar.Snackbar
import java.lang.Exception

class SalvaUsuarioTask(var context:Context):AsyncTask<Usuario, Void, String>(){
    override fun doInBackground(vararg usuarios: Usuario?): String{
        try {
            var usuario = usuarios[0]

            BibliotecaDatabase.getInstance(context)?.UsuarioDao()?.insert(usuario!!)
            return context.resources.getString(R.string.msg_user_registered, usuario!!.nome)
        }catch (e: Exception){
            e.message?.let {
                return it
            }
        }

        return ""
    }


}