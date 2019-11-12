package br.com.etecmatao.biblioteca.worker

import android.content.Context
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import br.com.etecmatao.biblioteca.R
import br.com.etecmatao.biblioteca.data.BibliotecaDatabase
import br.com.etecmatao.biblioteca.model.Usuario
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.lang.Exception

class LoginWorker(var ctx: Context, params: WorkerParameters) : Worker(ctx, params) {
    override fun doWork(): Result {
        return try {
            val email = inputData.getString("email")!!
            var password = inputData.getString("senha")!!

            password = Usuario.generateHash(password)

            val user = BibliotecaDatabase.getInstance(ctx)!!
                .usuarioDao()
                .select(email, password)

            if (user == null) {
                val out = workDataOf(
                    "msg" to ctx.resources.getString(R.string.msg_invalid_credential)
                )

                Result.failure(out)
            } else {
                val mapper = jacksonObjectMapper()

                val out = workDataOf(
                    "msg" to ctx.resources.getString(R.string.msg_welcome, user.nome),
                    "jsonUser" to mapper.writeValueAsString(user)
                )

                Result.success(out)
            }
        } catch (e: Exception) {
            Result.failure(workDataOf("msg" to e.message))
        }
    }
}
