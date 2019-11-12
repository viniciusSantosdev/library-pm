package br.com.etecmatao.biblioteca.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import br.com.etecmatao.biblioteca.model.Usuario
import br.com.etecmatao.biblioteca.model.Usuario.Companion.COLUMN_EMAIL
import br.com.etecmatao.biblioteca.model.Usuario.Companion.COLUMN_SENHA
import br.com.etecmatao.biblioteca.model.Usuario.Companion.TABLE_NAME


@Dao
interface UsuarioDao {
    @Query("select * from $TABLE_NAME")
    fun select(): List<Usuario>

    @Query("select * from $TABLE_NAME where id = :id")
    fun select(id: Long): Usuario

    @Query("select * from $TABLE_NAME where $COLUMN_EMAIL = :email and $COLUMN_SENHA = :password")
    fun select(email: String, password: String): Usuario?

    @Insert(onConflict = REPLACE)
    fun insert(usuario: Usuario)

    @Query("delete from $TABLE_NAME where id = :id")
    fun delete(id: Long)
}