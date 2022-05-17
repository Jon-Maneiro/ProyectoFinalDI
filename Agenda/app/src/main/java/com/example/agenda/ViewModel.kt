package com.example.agenda

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class CitaViewModel(private val miRepositorio:Repositorio):ViewModel() {
        val allCitas: LiveData<List<Citas>> = miRepositorio.listaCitas.asLiveData()
        lateinit var miCita: LiveData<Citas>

        fun Insertar(miCita : Citas) = viewModelScope.launch{
            miRepositorio.insertar(miCita)
        }

        fun BuscarPorId(id:Int) = viewModelScope.launch{
            miCita = miRepositorio.buscarPorId(id).asLiveData()
        }

        fun Borrar(miCita : Citas) = viewModelScope.launch{
            miRepositorio.borrar(miCita)
        }
        fun Actualizar(miCita : Citas) = viewModelScope.launch{
            miRepositorio.actualizar(miCita)
        }

        class peliViewModelFactory(private val repository:Repositorio) : ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>): T{
                if(modelClass.isAssignableFrom(CitaViewModel::class.java)){
                    @Suppress("UNCHECKED_CAST")
                    return CitaViewModel(repository) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }


}