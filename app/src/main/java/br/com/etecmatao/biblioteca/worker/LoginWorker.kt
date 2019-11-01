package br.com.etecmatao.biblioteca.worker

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import br.com.etecmatao.biblioteca.data.BibliotecaDatabase
import java.lang.Exception

class LoginWorker(var ctx: Context, params: WorkerParameters):Worker(ctx,params){
    override fun doWork(): Result {
        return try {
            val email = inputData.getString("email")!!
            val password = inputData.getString("senha")!!

            val user = BibliotecaDatabase.getInstance(ctx)!!
                .UsuarioDao()
                .selectByUserAndPassword(email, password)

            val out = workDataOf(
                "msg" to "Seja bem vindo!",
                "user" to user
            )

            Result.success(out)
        } catch (e:Exception){
            Result.failure(workDataOf("result" to e.message))
        }
    }
}
