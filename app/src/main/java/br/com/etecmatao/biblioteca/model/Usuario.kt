package br.com.etecmatao.biblioteca.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.etecmatao.biblioteca.model.Usuario.Companion.TABLE_NAME
import java.security.MessageDigest

@Entity(tableName = TABLE_NAME)
data class Usuario(
    @PrimaryKey(autoGenerate = true) var id: Long?,
    @ColumnInfo(name = COLUMN_NAME) var nome: String,
    @ColumnInfo(name = COLUMN_EMAIL) var email: String,
    @ColumnInfo(name = COLUMN_TELEFONE) var telefone: String,
    @ColumnInfo(name = COLUMN_SENHA) var senha: String
) {
    companion object {
        const val TABLE_NAME = "usuarios"
        const val COLUMN_NAME = "nome"
        const val COLUMN_EMAIL = "email"
        const val COLUMN_TELEFONE = "telefone"
        const val COLUMN_SENHA = "senha"

        fun generateHash(senha: String): String {
            try {
                val digest: MessageDigest = MessageDigest.getInstance("SHA-512")
                val hash: ByteArray = digest.digest(senha.toByteArray())
                return printableHexString(hash)
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return ""
        }

        private fun printableHexString(data: ByteArray): String {
            // Create Hex String
            val hexString: StringBuilder = StringBuilder()
            for (aMessageDigest:Byte in data) {
                var h: String = Integer.toHexString(0xFF and aMessageDigest.toInt())
                while (h.length < 2)
                    h = "0$h"
                hexString.append(h)
            }
            return hexString.toString()
        }
    }
}