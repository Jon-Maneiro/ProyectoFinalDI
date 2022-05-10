package com.example.agenda

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class CitaViewModel(private val miRepositorio:Repositorio):ViewModel() {
        val allCitas: LiveData<List<Citas>> = miRepositorio.listaCitas.asLiveData()
        lateinit var miPelicula: LiveData<Citas>

        fun Insertar(miPelicula : Citas) = viewModelScope.launch{
            miRepositorio.insertar(miPelicula)
        }

        fun BuscarPorId(id:Int) = viewModelScope.launch{
            miPelicula = miRepositorio.buscarPorId(id).asLiveData()
        }

        fun Borrar(miPelicula : Citas) = viewModelScope.launch{
            miRepositorio.borrar(miPelicula)
        }
        fun Actualizar(miPelicula : Citas) = viewModelScope.launch{
            miRepositorio.actualizar(miPelicula)
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