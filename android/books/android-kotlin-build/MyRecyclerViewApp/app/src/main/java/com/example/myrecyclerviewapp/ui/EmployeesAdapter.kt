package com.example.myrecyclerviewapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.myrecyclerviewapp.ImageLoader
import com.example.myrecyclerviewapp.databinding.ItemEmployeeBinding
import com.example.myrecyclerviewapp.model.EmployeeUiModel

// RecyclerView.Adapter<EmployeeViewHolder>()

class EmployeesAdapter(
    private val layoutInflater: LayoutInflater, private val imageLoader: ImageLoader
) : ListAdapter<EmployeeUiModel, EmployeeViewHolder>(EmployeeDiffCallback) {

    // private val employees = mutableListOf<EmployeeUiModel>()

    /*fun setData(newEmployees: List<EmployeeUiModel>) {
        employees.clear()
        employees.addAll(newEmployees)
        notifyDataSetChanged()
    }*/

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): EmployeeViewHolder {
        // val view = layoutInflater.inflate(R.layout.item_employee, parent, false)
        // val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemEmployeeBinding.inflate(layoutInflater, parent, false)
        return EmployeeViewHolder(binding, imageLoader)
    }

    override fun onBindViewHolder(
        holder: EmployeeViewHolder, position: Int
    ) {
        // holder.bindData(employees[position])
        holder.bindData(getItem(position))
    }

    // override fun getItemCount(): Int = employees.size

    companion object {
        private val EmployeeDiffCallback = object : DiffUtil.ItemCallback<EmployeeUiModel>() {
            override fun areItemsTheSame(
                oldItem: EmployeeUiModel, newItem: EmployeeUiModel
            ): Boolean {
                return oldItem.imageUrl == newItem.imageUrl
            }

            override fun areContentsTheSame(
                oldItem: EmployeeUiModel, newItem: EmployeeUiModel
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}