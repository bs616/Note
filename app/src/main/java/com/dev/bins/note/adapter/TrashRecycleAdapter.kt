package com.dev.bins.note.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.dev.bins.note.R
import com.dev.bins.note.model.Note
import com.dev.bins.note.ui.DetailActivity
import java.text.SimpleDateFormat

/**
 * Created by bin on 11/26/15.
 */
class TrashRecycleAdapter(private val context: Context) : RecyclerView.Adapter<Holder>() {
    private val sdf: SimpleDateFormat
    private val notes: MutableList<Note>

    init {
        sdf = SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss")
        notes = ArrayList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycleview_item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val note = notes[position]
        val title = note.title
        if (title == "无标题") {
            holder.title.text = note.id.toString()
        } else {
            holder.title.text = title
        }
        holder.content.text = note.content
        val date = sdf.format(note.date)
        holder.time.text = date
        holder.cardView.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("id", note.id)
            context.startActivity(intent)
        }


    }

    override fun getItemCount(): Int {
        return notes.size
    }


    fun add(data: List<Note>) {
        notes.clear()
        notes.addAll(data)
        notifyDataSetChanged()
    }
}
