package com.example.vervemobi.ui.ui


import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.vervemobi.R
import com.example.vervemobi.viewmodel.ModuleViewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var viewModel: ModuleViewModel
    private var moduleId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        moduleId = intent.getIntExtra("module_id", -1)

        viewModel = ViewModelProvider(this)[ModuleViewModel::class.java]

        val tvTitle = findViewById<TextView>(R.id.tvTitle)
        val tvDesc = findViewById<TextView>(R.id.tvDesc)
        val tvStatus = findViewById<TextView>(R.id.tvStatus)
        val btnToggle = findViewById<Button>(R.id.btnToggle)

        viewModel.modules.observe(this) { list ->
            val module = list.first { it.id == moduleId }

            tvTitle.text = module.title
            tvDesc.text = module.description
            tvStatus.text =
                "Status: " + if (module.isCompleted) "Completed" else "Pending"

            btnToggle.text =
                if (module.isCompleted) "Mark as Pending" else "Mark as Completed"

            btnToggle.setOnClickListener {
                viewModel.toggleStatus(module)
                finish() // go back to HomeScreen
            }
        }
    }
}
