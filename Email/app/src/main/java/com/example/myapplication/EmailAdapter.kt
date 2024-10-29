package com.example.myapplication
import android.content.ClipData.Item
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EmailAdapter(private val emailList: List<Email>) : RecyclerView.Adapter<EmailAdapter.EmailViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmailViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return EmailViewHolder(view)
    }

    override fun onBindViewHolder(holder: EmailViewHolder, position: Int) {
        val email = emailList[position]
        holder.sender.text = email.sender
        holder.subject.text = email.subject
        holder.time.text = email.time
        holder.icon.text = email.sender.firstOrNull()?.toString() // Lấy ký tự đầu tiên làm icon

        // Thiết lập màu nền ngẫu nhiên cho CardView
        val cardView = holder.itemView.findViewById<androidx.cardview.widget.CardView>(R.id.card_view)
        cardView.setCardBackgroundColor(getRandomColor())
    }

    override fun getItemCount(): Int = emailList.size

    class EmailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val sender: TextView = itemView.findViewById(R.id.sender)
        val subject: TextView = itemView.findViewById(R.id.subject)
        val time: TextView = itemView.findViewById(R.id.time)
        val icon: TextView = itemView.findViewById(R.id.icon)
    }

    // Hàm tạo màu ngẫu nhiên
    private fun getRandomColor(): Int {
        val rnd = java.util.Random()
        return android.graphics.Color.rgb(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
    }
}