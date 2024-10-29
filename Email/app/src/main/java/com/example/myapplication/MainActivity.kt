package com.example.myapplication

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var emailAdapter: EmailAdapter
    private lateinit var emailList: MutableList<Email>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        emailList = mutableListOf()
        // Thêm dữ liệu mẫu vào danh sách
        emailList.add(Email("Edurila.com", "$19 Only (First 10 spots)", "12:34 PM"))
        emailList.add(Email("Chris Abad", "Help make Campaign Monitor better", "11:22 AM"))
        emailList.add(Email("Tuto.com", "8h de formation gratuite", "11:04 AM"))
        emailList.add(Email("support", "Société Ovh", "10:26 AM"))
        emailList.add(Email("Matt from Ionic", "The New Ionic Creator Is Here!", "10:00 AM"))
        emailList.add(Email("Matt from Ionic", "The New Ionic Creator Is Here!", "10:00 AM"))
        emailList.add(Email("Matt from Ionic", "The New Ionic Creator Is Here!", "10:00 AM"))
        emailList.add(Email("Matt from Ionic", "The New Ionic Creator Is Here!", "10:00 AM"))
        emailList.add(Email("Matt from Ionic", "The New Ionic Creator Is Here!", "10:00 AM"))
        emailList.add(Email("Matt from Ionic", "The New Ionic Creator Is Here!", "10:00 AM"))
        emailList.add(Email("Matt from Ionic", "The New Ionic Creator Is Here!", "10:00 AM"))
        emailList.add(Email("Matt from Ionic", "The New Ionic Creator Is Here!", "10:00 AM"))
        emailList.add(Email("Chris Abad", "Help make Campaign Monitor better", "11:22 AM"))
        emailList.add(Email("Chris Abad", "Help make Campaign Monitor better", "11:22 AM"))


        emailAdapter = EmailAdapter(emailList)
        recyclerView.adapter = emailAdapter
    }
}
