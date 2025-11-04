package ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sab.schoolrollcall.R
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var presenceViewModel: PresenceViewModel
    private lateinit var recyclerView: RecyclerView

    private val adapter = StudentAdapter(emptyList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerViewStudents)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        presenceViewModel = ViewModelProvider(this)[PresenceViewModel::class.java]

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                presenceViewModel.students.collect { list ->
                    adapter.updateList(list)
                }
            }
        }

        presenceViewModel.loadMock()
    }
}
