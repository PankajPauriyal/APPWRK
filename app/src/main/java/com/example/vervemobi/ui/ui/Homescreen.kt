package com.example.vervemobi.ui.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vervemobi.R
import com.example.vervemobi.viewmodel.ModuleViewModel

class Homescreen : AppCompatActivity() {

    private lateinit var viewModel: ModuleViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ModuleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_homescreen)

        // Window Insets (Edge to Edge)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
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
        recyclerView = findViewById(R.id.rvModules)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // ViewModel init
        viewModel = ViewModelProvider(this)[ModuleViewModel::class.java]

        observeData()
    }

    private fun observeData() {
        viewModel.modules.observe(this) { list ->

            adapter = ModuleAdapter(list) { module ->

                // 👉 OPEN DETAIL SCREEN
                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra("module_id", module.id)
                startActivity(intent)
            }

            recyclerView.adapter = adapter
        }
    }

}
