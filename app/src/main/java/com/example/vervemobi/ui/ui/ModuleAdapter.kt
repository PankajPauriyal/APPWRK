package com.example.vervemobi.ui.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vervemobi.R
import com.example.vervemobi.ui.ui.model.TrainingModule

class ModuleAdapter(
    private val list: List<TrainingModule>,
    private val onItemClick: (TrainingModule) -> Unit
) : RecyclerView.Adapter<ModuleAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(R.id.tvTitle)
        val tvDesc: TextView = view.findViewById(R.id.tvDesc)
        val tvStatus: TextView = view.findViewById(R.id.tvStatus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_module, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val module = list[position]

        holder.tvTitle.text = module.title
        holder.tvDesc.text = module.description

        if (module.isCompleted) {
            holder.tvStatus.text = "Completed"
            holder.tvStatus.setTextColor(Color.parseColor("#2E7D32")) // green
        } else {
            holder.tvStatus.text = "Pending"
            holder.tvStatus.setTextColor(Color.parseColor("#C62828")) // red
        }

        holder.itemView.setOnClickListener {
            onItemClick(module)
        }
    }

    override fun getItemCount(): Int = list.size

    //demo checking
}
