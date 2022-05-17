package com.example.agenda

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.agenda.databinding.FragmentThirdBinding
import java.time.LocalDate
import java.time.LocalTime
import kotlin.properties.Delegates


class ThirdFragment : Fragment() {

    private var _binding: FragmentThirdBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentThirdBinding.inflate(inflater, container, false)
        return binding.root    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id:Int=arguments?.getInt("id") ?: -1

        if(id == -1){
            binding.btnCreate.isEnabled = true
            binding.btnDelete.isEnabled = false
            binding.btnUpdate.isEnabled = false
        }else{
            val nombre:String=arguments?.getString("nombre") ?: "nil"
            val fecha:String=arguments?.getString("fecha") ?: "1997-01-01"//Hay que parsear
            //val fechaD = LocalDate.parse(fecha)
            val hora:String=arguments?.getString("hora") ?: "00:00:00"//Hay que parsear
            //val horaD = LocalTime.parse(hora)
            val personas:String=arguments?.getString("personas") ?: "nil"
            binding.btnCreate.isEnabled = false
            binding.btnDelete.isEnabled = true
            binding.btnUpdate.isEnabled = true
            binding.etNombre.setText(nombre)
            binding.etFecha.setText(fecha)
            binding.etHora.setText(hora)
            binding.etPersonas.setText(personas)
        }

        binding.btnCreate.setOnClickListener{
            if ((binding.etNombre.text.toString() == "") ||
                (binding.etPersonas.text.toString() == "") ||
                (binding.etFecha.text.toString() == "") ||
                (binding.etHora.text.toString() == "")){

                val builder = AlertDialog.Builder(activity?.applicationContext ?: null)
                builder.setTitle("Faltan datos")
                builder.setMessage("No has introducido todos los datos")
                builder.show()
            }
            else{
                (activity as MainActivity).miViewModel.Insertar( Citas(binding.etNombre.text.toString(),
                    LocalDate.parse(binding.etFecha.text.toString()),
                    LocalTime.parse(binding.etHora.text.toString()),
                    binding.etPersonas.text.toString()))
            }
        }

        binding.btnUpdate.setOnClickListener{
            if ((binding.etNombre.text.toString() == "") ||
                (binding.etPersonas.text.toString() == "") ||
                (binding.etFecha.text.toString() == "") ||
                (binding.etHora.text.toString() == "")){

                val builder = AlertDialog.Builder(activity?.applicationContext ?: null)
                builder.setTitle("Faltan datos")
                builder.setMessage("No has introducido todos los datos")
                builder.show()
            }
            else{
                (activity as MainActivity).miViewModel.Actualizar( Citas(binding.etNombre.text.toString(),
                    LocalDate.parse(binding.etFecha.text.toString()),
                    LocalTime.parse(binding.etHora.text.toString()),
                    binding.etPersonas.text.toString(),
                    id))
            }
        }

        binding.btnDelete.setOnClickListener{
            if ((binding.etNombre.text.toString() == "") ||
                (binding.etPersonas.text.toString() == "") ||
                (binding.etFecha.text.toString() == "") ||
                (binding.etHora.text.toString() == "")){

                val builder = AlertDialog.Builder(activity?.applicationContext ?: null)
                builder.setTitle("Faltan datos")
                builder.setMessage("No has introducido todos los datos")
                builder.show()
            }
            else{
                (activity as MainActivity).miViewModel.Borrar( Citas(binding.etNombre.text.toString(),
                    LocalDate.parse(binding.etFecha.text.toString()),
                    LocalTime.parse(binding.etHora.text.toString()),
                    binding.etPersonas.text.toString(),
                    id))
            }
        }
        setHasOptionsMenu(true)
        activity?.setTitle("Gestión")
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.findItem(R.id.action_crear)?.isVisible=false
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_listado->findNavController().navigate(R.id.action_thirdFragment_to_SecondFragment)
            R.id.action_main->findNavController().navigate(R.id.action_thirdFragment_to_FirstFragment)
            R.id.action_limpiar->{
                binding.etNombre.setText("")
                binding.etFecha.setText("")
                binding.etHora.setText("")
                binding.etPersonas.setText("")
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}