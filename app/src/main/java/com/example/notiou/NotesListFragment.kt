package com.example.notiou

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notiou.data.Note
import com.example.notiou.data.NoteViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NotesListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NotesListFragment : Fragment(), NoteClickDeleteInterface {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var viewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_notes_list, container, false)
        val adapter = ListAdapter(requireContext(), this)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel = ViewModelProvider(this)[NoteViewModel::class.java]
        viewModel.allNotes.observe(viewLifecycleOwner, Observer { notes ->
            adapter.setData(notes)
        })



        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<FloatingActionButton>(R.id.floatingActionButton).setOnClickListener {
            view.findNavController().navigate(R.id.action_notesListFragment_to_addNoteFragment)
        }
        view.findViewById<FloatingActionButton>(R.id.floatingActionButton2).setOnClickListener {
            view.findNavController().navigate(R.id.action_notesListFragment_to_newsFragment)
        }
    }

    override fun onDeleteIconClick(note: Note) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            //in on note click method we are calling delete method from our viw modal to delete our not.
            viewModel.deleteNote(note)
            Toast.makeText(requireContext(), "${note.noteTitle} Deleted", Toast.LENGTH_LONG).show()
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete ${note.noteTitle}?")
        builder.setMessage("Are you sure you want to delete ${note.noteTitle}?")
        builder.create().show()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment NotesListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NotesListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}