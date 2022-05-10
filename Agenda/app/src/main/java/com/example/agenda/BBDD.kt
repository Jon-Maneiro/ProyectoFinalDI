package com.example.agenda

import android.content.Context
import androidx.annotation.NonNull
import androidx.room.*
import kotlinx.coroutines.flow.Flow
import java.sql.Time
import java.util.*

@Entity(tableName = "tabla_citas")
data class tabla_citas(
    @PrimaryKey(autoGenerate = true) var id:Int = 0,
    @NonNull @ColumnInfo (name = "nombre") val nombre:String = "",
    @NonNull @ColumnInfo (name = "fecha") val fecha:Date,
    @NonNull @ColumnInfo (name = "hora") val hora: Time,
    @NonNull @ColumnInfo (name = "personas") val personas:String = ""
)

@Dao
interface CitaDAO {
    @Query("SELECT * FROM tabla_citas ORDER BY nombre ASC")
    fun CargarCitas(): Flow<List<Citas>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun Insertar(peli: Citas)

    @Delete
    suspend fun Borrar(peli:Citas)

    @Update
    suspend fun Actualizar(peli:Citas)

    @Query("SELECT * FROM tabla_citas WHERE id LIKE :id")
    fun BuscarPorId(id:Int) : Flow<Citas>
}
@Database(entities = arrayOf(Citas::class), version = 1, exportSchema = false)
abstract class BaseDatos: RoomDatabase(){

    abstract fun miDao(): CitaDAO
    companion object{
        @Volatile
        private var INSTANCE : BaseDatos?=null

        fun getDatabase(context: Context): BaseDatos{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BaseDatos::class.java,
                    "citas_database"
                ).build()
                INSTANCE = instance

                instance
            }
        }

    }

}