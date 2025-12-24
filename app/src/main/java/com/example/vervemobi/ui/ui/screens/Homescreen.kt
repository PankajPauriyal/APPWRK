package com.example.vervemobi.ui.ui.screens

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vervemobi.databinding.ActivityHomescreenBinding
import com.example.vervemobi.ui.ui.FilterType
import com.example.vervemobi.ui.ui.adapter.ModuleAdapter
import com.example.vervemobi.ui.ui.viewmodel.ModuleViewModel

class Homescreen : AppCompatActivity() {

    private lateinit var binding: ActivityHomescreenBinding
    private lateinit var viewModel: ModuleViewModel
    private lateinit var adapter: ModuleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        // ✅ ViewBinding init
        binding = ActivityHomescreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Edge-to-edge insets
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )
            insets
        }

        // RecyclerView setup
        binding.rvModules.layoutManager = LinearLayoutManager(this)

        // ViewModel init
        viewModel = ViewModelProvider(this)[ModuleViewModel::class.java]

        // 🔹 Filter buttons (NO findViewById)
        binding.btnAll.setOnClickListener {
            viewModel.setFilter(FilterType.ALL)
        }

        binding.btnCompleted.setOnClickListener {
            viewModel.setFilter(FilterType.COMPLETED)
        }

        binding.btnPending.setOnClickListener {
            viewModel.setFilter(FilterType.PENDING)
        }

        observeData()
    }

    private fun observeData() {
        viewModel.modules.observe(this) { list ->
            adapter = ModuleAdapter(list) { module ->
                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra("module_id", module.id)
                startActivity(intent)
            }
            binding.rvModules.adapter = adapter
        }
    }

    override fun onResume() {
        super.onResume()
        // Reload so status updates immediately after returning from Detail
        viewModel.reloadModules()
    }
}
