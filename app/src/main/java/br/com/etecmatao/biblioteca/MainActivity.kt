package br.com.etecmatao.biblioteca

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.etecmatao.biblioteca.data.BibliotecaApplication
import br.com.etecmatao.biblioteca.data.BibliotecaDatabase
import br.com.etecmatao.biblioteca.model.Usuario
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()

        with(application as BibliotecaApplication){
            if (usuario == null){
                val intent = Intent(this, LoginActivity::class.java)
                startActivityForResult(intent, 1010)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == 1010){
            val extra = data!!.getStringExtra("jsonUser")

            extra?.let {
                val user = jacksonObjectMapper().readValue<Usuario>(extra)

                with(application as BibliotecaApplication){
                    usuario = user
                }
            }
        }
    }
}

