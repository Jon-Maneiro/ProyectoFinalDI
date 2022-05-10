package com.example.agenda

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class Repositorio(val miDAO: CitaDAO) {
    val listaCitas: Flow<List<Citas>> = miDAO.CargarCitas()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertar(miCita:Citas){
        miDAO.Insertar(miCita)
    }
    fun buscarPorId(id:Int): Flow<Citas> {
        return miDAO.BuscarPorId(id)
    }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun borrar(miPeli: Citas){
        miDAO.Borrar(miPeli)
    }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun actualizar(miPeli:Citas){
        miDAO.Actualizar(miPeli)
    }
}