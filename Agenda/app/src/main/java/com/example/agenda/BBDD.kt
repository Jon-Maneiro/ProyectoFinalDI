package com.example.agenda

import android.content.Context
import androidx.annotation.NonNull
import androidx.room.*
import kotlinx.coroutines.flow.Flow
import java.sql.Time
import java.time.LocalDate
import java.time.LocalTime
import java.util.*

class Converters{
    @TypeConverter
    fun fromStringLocalDate(value: String?) : LocalDate?{
        return value?.let {LocalDate.parse(it)}
    }
    @TypeConverter
    fun localDateToString(value: LocalDate?): String?{
        return value.toString()
    }
    @TypeConverter
    fun fromStringLocalTime(value: String?) : LocalTime?{
        return value?.let{LocalTime.parse(it)}
    }
    @TypeConverter
    fun localTimeToString(value: LocalTime?): String?{
        return value.toString()
    }
}

@Entity(tableName = "tabla_citas")
data class Citas(
    @NonNull @ColumnInfo (name = "nombre") val nombre:String = "",
    @NonNull @ColumnInfo (name = "fecha") val fecha: LocalDate?,
    @NonNull @ColumnInfo (name = "hora") val hora: LocalTime?,
    @NonNull @ColumnInfo (name = "personas") val personas:String = "",
    @PrimaryKey(autoGenerate = true) var id:Int = 0
)

@Dao
interface CitaDAO {
    @Query("SELECT * FROM tabla_citas ORDER BY fecha ASC")
    fun CargarCitas(): Flow<List<Citas>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun Insertar(peli: Citas)

    @Delete
    suspend fun Borrar(peli:Citas)

    @Update
    suspend fun Actualizar(peli:Citas)

    @Query("SELECT * FROM tabla_citas WHERE id LIKE :id")
    fun BuscarPorId(id:Int) : Flow<Citas>

    @Query("SELECT * FROM tabla_citas ORDER BY fecha ASC LIMIT 1")
    fun ObtenerCitaProxima() : Flow<Citas>
}
@Database(entities = arrayOf(Citas::class), version = 1, exportSchema = false)
@TypeConverters(Converters::class)
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