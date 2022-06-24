package com.example.notiou

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.notiou.data.Note
import com.example.notiou.data.NoteViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [UpdateFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UpdateFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var noteViewModel: NoteViewModel
    private lateinit var editTitle: EditText
    private lateinit var editDesc: EditText

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
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        editTitle = view.findViewById(R.id.update_title)
        editDesc = view.findViewById(R.id.update_description)

        editTitle.setText(args.currentNote.noteTitle)
        editDesc.setText(args.currentNote.noteDescription)

        noteViewModel = ViewModelProvider(this)[NoteViewModel::class.java]

        updateNote(view)

        return view
    }

    private fun updateNote(view: View) {
        view.findViewById<Button>(R.id.update_button).setOnClickListener {
            if(editTitle.text.isNotEmpty() && editDesc.text.isNotEmpty()) {
                val updatedNote = Note(
                    args.currentNote.id,
                    editTitle.text.toString(),
                    editDesc.text.toString(),
                    args.currentNote.timeStamp
                )

                noteViewModel.updateNote(updatedNote)
                Toast.makeText(requireContext(), "Note Updated", Toast.LENGTH_LONG).show()

                val action = UpdateFragmentDirections.actionUpdateFragmentToNotesListFragment()
                view.findNavController().navigate(action)
            } else {
                Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_LONG).show()
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment UpdateFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            UpdateFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}