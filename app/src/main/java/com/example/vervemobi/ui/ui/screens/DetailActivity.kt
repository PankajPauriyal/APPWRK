package com.example.vervemobi.ui.ui.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.vervemobi.databinding.ActivityDetailBinding
import com.example.vervemobi.ui.ui.viewmodel.ModuleViewModel
class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel: ModuleViewModel
    private var moduleId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ✅ ViewBinding init
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        moduleId = intent.getIntExtra("module_id", -1)

        viewModel = ViewModelProvider(this)[ModuleViewModel::class.java]



        viewModel.modules.observe(this) { list ->
            val module = list.first { it.id == moduleId }

            binding.tvTitle.text = module.title
            binding.tvDesc.text = module.description
            binding.tvStatus.text =
                "Status: " + if (module.isCompleted) "Completed" else "Pending"

            binding.btnToggle.text =
                if (module.isCompleted) "Mark as Pending"
                else "Mark as Completed"

            binding.btnToggle.setOnClickListener {
                viewModel.toggleStatus(module)
                finish()
            }
        }
    }
}

//pr request
