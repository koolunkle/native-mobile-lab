package com.example.myrecyclerviewapp.ui

import androidx.recyclerview.widget.RecyclerView
import com.example.myrecyclerviewapp.ImageLoader
import com.example.myrecyclerviewapp.databinding.ItemEmployeeBinding
import com.example.myrecyclerviewapp.model.EmployeeRole
import com.example.myrecyclerviewapp.model.EmployeeUiModel
import com.example.myrecyclerviewapp.model.Gender

private const val FEMALE_SYMBOL = "\u2640"
private const val MALE_SYMBOL = "\u2642"
private const val UNKNOWN_SYMBOL = "?"

class EmployeeViewHolder(
    // containerView: View,
    private val binding: ItemEmployeeBinding,
    private val imageLoader: ImageLoader
) : RecyclerView.ViewHolder(binding.root) {

    fun bindData(employeeData: EmployeeUiModel) {
        imageLoader.loadImage(employeeData.imageUrl, binding.itemEmployeePhoto)
        binding.itemEmployeeName.text = employeeData.name
        binding.itemEmployeeRole.text = when (employeeData.role) {
            EmployeeRole.HumanResources -> "Human Resources"
            EmployeeRole.Management -> "Management"
            EmployeeRole.Technology -> "Technology"
        }
        binding.itemEmployeeBiography.text = employeeData.biography
        binding.itemEmployeeGender.text = when (employeeData.gender) {
            Gender.Female -> FEMALE_SYMBOL
            Gender.Male -> MALE_SYMBOL
            Gender.Unknown -> UNKNOWN_SYMBOL
        }
    }
}