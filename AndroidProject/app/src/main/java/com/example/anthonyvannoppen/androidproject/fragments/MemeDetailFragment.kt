package com.example.anthonyvannoppen.androidproject.fragments

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.anthonyvannoppen.androidproject.R
import com.example.anthonyvannoppen.androidproject.domain.Meme
import com.example.anthonyvannoppen.androidproject.ui.MemeViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_meme_detail.*
import kotlinx.android.synthetic.main.fragment_meme_detail.view.*

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [MemeDetailFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [MemeDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class MemeDetailFragment : Fragment() {

    private lateinit var meme:Meme
    private lateinit var viewModel:MemeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //initiate the viewmodel
        viewModel = ViewModelProviders.of(this).get(MemeViewModel::class.java)
        // Inflate the layout for this fragment

        val rootView = inflater.inflate(R.layout.fragment_meme_detail, container, false)


        // Opvullen van de content met de gekozen meme
        meme.let {
            rootView.text_detail_titel.text = it.titel
            rootView.text_detail_beschrijving.text = it.beschrijving
            rootView.text_detail_op.text = "By "+it.op
            rootView.text_detail_categorie.text = "- "+it.categorie
            Picasso.get().load(it.afbeelding).fit().into(rootView.image_detail_afbeelding)

        }
        return rootView


    }


    override fun onResume() {
        super.onResume()
        // hier komen de intents van de mogelijke buttons
    }

    //meegeven van de meme
    fun addObject(item: Meme){
        this.meme = item

    }



    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        const val ARG_MEME = "item_id"

        fun newInstance(mem: Meme): MemeDetailFragment {
            val args = Bundle()
            args.putSerializable(ARG_MEME, mem)
            val fragment = MemeDetailFragment()
            fragment.arguments = args

            return fragment
        }
    }
}
