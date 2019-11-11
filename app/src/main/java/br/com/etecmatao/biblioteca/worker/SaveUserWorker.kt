package br.com.etecmatao.biblioteca.worker

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import br.com.etecmatao.biblioteca.R
import br.com.etecmatao.biblioteca.data.BibliotecaDatabase
import br.com.etecmatao.biblioteca.model.Usuario
import java.lang.Exception

class SaveUserWorker(var ctx: Context, params: WorkerParameters):Worker(ctx, params){
    override fun doWork(): Result {
        return try {
            var user = Usuario(
                null,
                inputData.getString("nome")!!,
                inputData.getString("email")!!,
                inputData.getString("telefone")!!,
                Usuario.generateHash(inputData.getString("senha")!!)
            )

            BibliotecaDatabase.getInstance(ctx)!!.usuarioDao().insert(user)

            val result = ctx.resources.getString(R.string.msg_user_registered, user.nome)
            val out = workDataOf("result" to result)
            Result.success(out)
        } catch (e:Exception){
            var out = workDataOf("result" to e.message)
            Result.failure(out)
        }
    }

}
