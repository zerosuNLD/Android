package com.example.bai2

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var editTextSearch: EditText
    private lateinit var recyclerViewStudents: RecyclerView
    private lateinit var studentAdapter: StudentAdapter
    private lateinit var studentList: List<Student>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        editTextSearch = findViewById(R.id.editTextSearch)
        recyclerViewStudents = findViewById(R.id.recyclerViewStudents)

        // Khởi tạo danh sách sinh viên
        studentList = listOf(
            Student("Nguyễn Văn A", "20210001"),
            Student("Trần Thị B", "2021002"),
            Student("Lê Văn C", "2021003"),
            Student("Nguyễn Văn D", "20210004"),
        )

        // Thiết lập RecyclerView với adapter
        studentAdapter = StudentAdapter(studentList)
        recyclerViewStudents.layoutManager = LinearLayoutManager(this)
        recyclerViewStudents.adapter = studentAdapter

        // Xử lý tìm kiếm khi người dùng nhập từ khóa
        editTextSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val keyword = s.toString().trim()
                filterStudents(keyword)
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }
    private fun filterStudents(keyword: String) {
        val filteredList = if (keyword.length > 2) {
            studentList.filter {
                it.name.contains(keyword, ignoreCase = true) || it.mssv.contains(keyword, ignoreCase = true)
            }
        } else {
            studentList
        }
        studentAdapter.updateList(filteredList)
    }
}