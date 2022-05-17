package com.example.agenda

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView

class Adaptador(citas:List<Citas>): RecyclerView.Adapter<Adaptador.ViewHolder>() {
    var citas = citas

    class ViewHolder(v: View): RecyclerView.ViewHolder(v) {
        var tvNombre:TextView
        var tvFecha:TextView
        var tvHora:TextView
        var tvPersonas:TextView
        var tvId:TextView

        init{
            tvNombre = v.findViewById(R.id.tv_nombre)
            tvFecha = v.findViewById(R.id.tv_fecha)
            tvHora = v.findViewById(R.id.tv_hora)
            tvPersonas = v.findViewById(R.id.tv_personas)
            tvId = v.findViewById(R.id.tv_id)
            v.findViewById<Button>(R.id.btn_gest).setOnClickListener{
                val bundle= bundleOf("id" to this.tvId.text.toString().toInt(),
                    "nombre" to this.tvNombre.text.toString(),
                    "fecha" to this.tvFecha.text.toString(),
                    "hora" to this.tvHora.text.toString(),
                    "personas" to this.tvPersonas.text.toString())
                v.findNavController().navigate(R.id.action_SecondFragment_to_thirdFragment,bundle)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.plantilla_cita, parent, false)
        return ViewHolder(v)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvNombre.text = citas[position].nombre
        holder.tvFecha.text = citas[position].fecha.toString()
        holder.tvHora.text = citas[position].hora.toString()
        holder.tvPersonas.text = citas[position].personas
        holder.tvId.text = citas[position].id.toString()

    }

    override fun getItemCount(): Int {
        return citas.size
    }
}