package com.example.vervemobi.ui.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.vervemobi.ui.ui.FilterType
import com.example.vervemobi.ui.ui.PrefHelper
import com.example.vervemobi.ui.ui.model.TrainingModule

class ModuleViewModel(application: Application) : AndroidViewModel(application) {

    private val prefHelper = PrefHelper(application)

    private val _modules = MutableLiveData<List<TrainingModule>>()
    val modules: LiveData<List<TrainingModule>> = _modules

    private var currentFilter = FilterType.ALL

    init {
        loadModules()
    }

    private fun loadModules() {
        val list = listOf(
            TrainingModule(
                1,
                "Android Fundamentals",
                "Core Android components, lifecycle, and project structure",
                false
            ),
            TrainingModule(
                2,
                "Kotlin for Android",
                "Kotlin syntax, null safety, collections, and best practices",
                false
            ),
            TrainingModule(
                3,
                "UI Development with XML",
                "Designing responsive UI using XML, ConstraintLayout, and styles",
                false
            ),
            TrainingModule(
                4,
                "RecyclerView & Adapters",
                "Efficient list rendering using RecyclerView and ViewHolder pattern",
                false
            ),
            TrainingModule(
                5,
                "MVVM Architecture",
                "Implementing MVVM for scalable and maintainable Android apps",
                false
            ),
            TrainingModule(
                6,
                "ViewModel & LiveData",
                "Managing UI-related data with lifecycle awareness",
                false
            ),
            TrainingModule(
                7,
                "SharedPreferences & Data Storage",
                "Persisting simple data locally using SharedPreferences",
                false
            ),
            TrainingModule(
                8,
                "Room Database Basics",
                "Storing structured data locally using Room ORM",
                false
            ),
            TrainingModule(
                9,
                "Navigation Between Screens",
                "Handling navigation between activities and passing data",
                false
            ),

        ).map {
            it.copy(isCompleted = prefHelper.getStatus(it.id))
        }

        _modules.value = when (currentFilter) {
            FilterType.ALL -> list
            FilterType.COMPLETED -> list.filter { it.isCompleted }
            FilterType.PENDING -> list.filter { !it.isCompleted }
        }
    }

    fun toggleStatus(module: TrainingModule) {
        val newStatus = !module.isCompleted
        prefHelper.saveStatus(module.id, newStatus)
        loadModules()
    }

    fun setFilter(filter: FilterType) {
        currentFilter = filter
        loadModules()
    }

    fun reloadModules() {
        loadModules()
    }
}