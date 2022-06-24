package com.example.notiou

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.notiou.data.Note

class ListAdapter(
    val context: Context,
    val noteClickDeleteInterface: NoteClickDeleteInterface
    ): RecyclerView.Adapter<ListAdapter.MyViewHolder>() {
    private var noteList = emptyList<Note>()
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)  {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false))
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = noteList[position]
        holder.itemView.findViewById<TextView>(R.id.idTVNote).text = currentItem.noteTitle
        holder.itemView.findViewById<TextView>(R.id.idTVDate).text = currentItem.timeStamp

        holder.itemView.findViewById<ImageView>(R.id.idIVDelete).setOnClickListener {
            //on below line we are calling a note click interface and we are passing a position to it.
            noteClickDeleteInterface.onDeleteIconClick(currentItem)
        }

        holder.itemView.findViewById<CardView>(R.id.rowLayout).setOnClickListener {
            val action = NotesListFragmentDirections.actionNotesListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(noteList: List<Note>) {
        this.noteList = noteList
        notifyDataSetChanged()
    }
}

interface NoteClickDeleteInterface {
    //creating a method for click action on delete image view.
    fun onDeleteIconClick(note: Note)
}