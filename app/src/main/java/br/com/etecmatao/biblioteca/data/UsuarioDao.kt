package br.com.etecmatao.biblioteca.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import br.com.etecmatao.biblioteca.model.Usuario

@Dao
interface UsuarioDao {
    @Query("select * from ${Usuario.TABLE_NAME}")
    fun select(): List<Usuario>

    @Query("select * from ${Usuario.TABLE_NAME} where id = :id")
    fun select(id: Long): Usuario

    @Insert(onConflict = REPLACE)
    fun insert(usuario: Usuario)

    @Query("delete from ${Usuario.TABLE_NAME} where id = :id")
    fun delete(id: Long)
}