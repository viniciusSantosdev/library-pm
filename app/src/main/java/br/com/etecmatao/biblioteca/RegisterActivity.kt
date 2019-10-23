package br.com.etecmatao.biblioteca

import android.annotation.SuppressLint
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import br.com.etecmatao.biblioteca.data.BibliotecaDatabase
import br.com.etecmatao.biblioteca.model.Usuario
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import java.lang.Exception

class RegisterActivity : AppCompatActivity() {

    lateinit var campoNome: TextInputEditText
    lateinit var campoEmail: TextInputEditText
    lateinit var campoTelefone: TextInputEditText
    lateinit var campoSenha: TextInputEditText
    lateinit var campoConfirmaSenha: TextInputEditText

    lateinit var fields:Array<TextInputEditText>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        campoNome = findViewById(R.id.txtNome)
        campoEmail = findViewById(R.id.txtEmail)
        campoTelefone = findViewById(R.id.txtTelefone)
        campoSenha = findViewById(R.id.txtSenha)
        campoConfirmaSenha = findViewById(R.id.txtConformaSenha)

        fields = arrayOf(campoNome, campoEmail, campoTelefone, campoSenha, campoConfirmaSenha)
    }

    fun validar():Boolean{
        var error = false

        for (field in fields) {
            field.error = null

            if (TextUtils.isEmpty(field.text.toString())){
                field.error = resources.getString(R.string.msg_required_field)
                error = true
            }
        }

        if (!TextUtils.equals(campoSenha.text.toString(), campoConfirmaSenha.text.toString())){
            campoConfirmaSenha.error = resources.getString(R.string.msg_password_different)
            error = true
        }

        return !error
    }

    fun salvar(v:View){
        if (!validar()) {
            return
        }

        var senha = Usuario.generateHash(campoSenha.text.toString())

        var usuario = Usuario(
            null,
            campoNome.text.toString(),
            campoEmail.text.toString(),
            campoTelefone.text.toString(),
            senha
        )

        val task = @SuppressLint("StaticFieldLeak")
        object : AsyncTask<Usuario, Void, Void?>() {
            override fun doInBackground(vararg usuario: Usuario?): Void? {

            }

        }
    }
}
