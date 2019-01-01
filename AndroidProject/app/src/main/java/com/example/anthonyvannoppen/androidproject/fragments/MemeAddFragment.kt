package com.example.anthonyvannoppen.androidproject.fragments

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner

import com.example.anthonyvannoppen.androidproject.R
import com.example.anthonyvannoppen.androidproject.domain.Comment
import com.example.anthonyvannoppen.androidproject.domain.Meme
import com.example.anthonyvannoppen.androidproject.ui.MemeViewModel
import kotlinx.android.synthetic.main.fragment_meme_add.*


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [MemeAddFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [MemeAddFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class MemeAddFragment : Fragment() {

    private lateinit var meme: Meme
    private lateinit var viewModel: MemeViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val spinner: Spinner = view.findViewById(R.id.spinner_add_categorie)
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            context!!,
            R.array.categorieen,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(activity!!).get(MemeViewModel::class.java)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_meme_add, container, false)
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStart() {
        super.onStart()


        button_add_submit.setOnClickListener{
            val titel = text_add_titel.text.toString()
            val op = text_add_op.text.toString()
            val beschrijving = text_add_beschrijving.text.toString()
            val afbeelding = text_add_afbeelding.text.toString()
            val categorie = spinner_add_categorie.selectedItem.toString()
            meme = Meme("",op,titel,afbeelding,beschrijving,categorie, listOf())
            Log.d("",meme.toString())
           viewModel.postMeme(meme)
            val memeListFragment =  MemeListFragment()
            this.fragmentManager!!.beginTransaction()
                .replace(R.id.container_main, memeListFragment)
                .addToBackStack(null)
                .commit()

        }
    }






    companion object {

        fun newInstance(): MemeAddFragment {
            val args = Bundle()
            //args.putSerializable("list", list)
            val fragment = MemeAddFragment()
            fragment.arguments = args

            return fragment
        }
    }
}
