package com.example.vervemobi.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.vervemobi.ui.ui.PrefHelper
import com.example.vervemobi.ui.ui.model.TrainingModule

class ModuleViewModel(application: Application) : AndroidViewModel(application) {

    private val prefHelper = PrefHelper(application)

    private val _modules = MutableLiveData<List<TrainingModule>>()
    val modules: LiveData<List<TrainingModule>> = _modules

    init {
        loadModules()
    }

    private fun loadModules() {
        val list = listOf(
            TrainingModule(1, "Kotlin Basics", "Learn Kotlin fundamentals", false),
            TrainingModule(2, "Android UI", "XML layouts and views", false),
            TrainingModule(3, "MVVM", "Architecture pattern", false)
        ).map {
            it.copy(isCompleted = prefHelper.getStatus(it.id))
        }

        _modules.value = list
    }

    // 🔥 HERE STATUS IS SAVED
    fun toggleStatus(module: TrainingModule) {
        val updatedList = _modules.value?.map {
            if (it.id == module.id) {
                val newStatus = !it.isCompleted
                prefHelper.saveStatus(it.id, newStatus) // ✅ SAVE
                it.copy(isCompleted = newStatus)
            } else {
                it
            }
        } ?: emptyList()

        _modules.value = updatedList
    }
}
