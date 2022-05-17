package com.example.agenda

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.agenda.databinding.FragmentSecondBinding
import androidx.recyclerview.widget.LinearLayoutManager

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    lateinit var reciclaje: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        reciclaje = binding.rcView
        reciclaje.layoutManager = LinearLayoutManager(activity)
        reciclaje.adapter = Adaptador((activity as MainActivity).citas)
        setHasOptionsMenu(true)
        activity?.setTitle("Lista de Citas")
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.findItem(R.id.action_listado)?.isVisible=false
        menu.findItem(R.id.action_limpiar)?.isVisible=false
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean{
        when(item.itemId) {
            R.id.action_crear->findNavController().navigate(R.id.action_SecondFragment_to_thirdFragment)
            R.id.action_main->findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}