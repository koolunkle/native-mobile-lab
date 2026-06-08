package com.example.myrecyclerviewapp

import android.app.AlertDialog
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myrecyclerviewapp.databinding.ActivityMainBinding
import com.example.myrecyclerviewapp.model.CatBreed
import com.example.myrecyclerviewapp.model.CatUiModel
import com.example.myrecyclerviewapp.model.Gender
import com.example.myrecyclerviewapp.model.ListItemUiModel
import com.example.myrecyclerviewapp.ui.ListItemAdapter
import com.example.myrecyclerviewapp.ui.TitleViewHolder

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var currentList: List<ListItemUiModel> = emptyList()

    /*private val employeesAdapter: EmployeesAdapter by lazy {
        EmployeesAdapter(
            layoutInflater,
            GlideImageLoader(this)
        )
    }*/

    private val listItemAdapter: ListItemAdapter by lazy {
        ListItemAdapter(
            layoutInflater = layoutInflater,
            imageLoader = GlideImageLoader(this),
            onClickListener = { cat ->
                showSelectionDialog(cat)
            }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initViews()
        setValues()
        setupEvents()
    }

    private fun initViews() {
        binding.recyclerView.adapter = listItemAdapter
        binding.recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val itemTouchHelper = ItemTouchHelper(SwipeToDeleteCallback())
        itemTouchHelper.attachToRecyclerView(binding.recyclerView)
    }

    private fun setValues() {
        val initialData = listOf(
            ListItemUiModel.Title("Sleeper Agents"), ListItemUiModel.Cat(
                CatUiModel(
                    gender = Gender.Male,
                    breed = CatBreed.BalineseJavanese,
                    name = "Fred",
                    biography = "Silent and deadly",
                    imageUrl = "https://cdn2.thecatapi.com/images/DBmIBhhyv.jpg"
                )
            ), ListItemUiModel.Cat(
                CatUiModel(
                    gender = Gender.Female,
                    breed = CatBreed.ExoticShorthair,
                    name = "Wilma",
                    biography = "Cuddly assassin",
                    imageUrl = "https://cdn2.thecatapi.com/images/KJF8fB_20.jpg"
                )
            ), ListItemUiModel.Title("Active Agents"), ListItemUiModel.Cat(
                CatUiModel(
                    gender = Gender.Unknown,
                    breed = CatBreed.AmericanCurl,
                    name = "Curious George",
                    biography = "Award winning investigator",
                    imageUrl = "https://cdn2.thecatapi.com/images/vJB8rwfdX.jpg"
                )
            )
        )
        updateList(initialData)
    }

    private fun setupEvents() {
        binding.mainAddItemButton.setOnClickListener {
            addList()
        }
    }

    private fun addList() {
        val newCat = ListItemUiModel.Cat(
            CatUiModel(
                gender = Gender.Female,
                breed = CatBreed.BalineseJavanese,
                name = "Anonymous",
                biography = "Unknown",
                imageUrl = "https://cdn2.thecatapi.com/images/zJkeHza2K.jpg"
            )
        )

        val newList = currentList + newCat

        updateList(newList)
    }

    private fun updateList(newList: List<ListItemUiModel>) {
        currentList = newList
        listItemAdapter.submitList(newList)
    }

    private fun removeList(removedCat: CatUiModel) {
        val newList = currentList.filterNot {
            it is ListItemUiModel.Cat && it.cat.id == removedCat.id
        }
        updateList(newList)
    }

    private fun showSelectionDialog(cat: CatUiModel) {
        AlertDialog.Builder(this).setTitle("Agent Selected")
            .setMessage("You have selected agent ${cat.name}").setPositiveButton("OK") { _, _ -> }
            .show()
    }

    inner class SwipeToDeleteCallback : ItemTouchHelper.SimpleCallback(
        0, ItemTouchHelper.START or ItemTouchHelper.END
    ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean = false

        override fun getSwipeDirs(
            recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder
        ): Int {
            if (viewHolder is TitleViewHolder) {
                return 0
            }
            return super.getSwipeDirs(recyclerView, viewHolder)
        }

        override fun onSwiped(
            viewHolder: RecyclerView.ViewHolder, direction: Int
        ) {
            val position = viewHolder.bindingAdapterPosition

            if (position != RecyclerView.NO_POSITION) {
                val item = listItemAdapter.currentList[position]
                if (item is ListItemUiModel.Cat) {
                    removeList(item.cat)
                }
            }
        }

    }
}