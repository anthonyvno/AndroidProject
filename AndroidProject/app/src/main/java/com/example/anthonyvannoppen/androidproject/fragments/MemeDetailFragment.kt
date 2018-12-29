package com.example.anthonyvannoppen.androidproject.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.anthonyvannoppen.androidproject.R
import com.example.anthonyvannoppen.androidproject.domain.Meme
import com.squareup.picasso.Picasso
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

    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments!!.let {
            if (it.containsKey(ARG_MEME)) {
                // Load de meme
                meme = it.getSerializable(ARG_MEME) as Meme
            }
        }

    }*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_meme_detail, container, false)
        val rootView = inflater.inflate(R.layout.fragment_meme_detail, container, false)

        //activity?.toolbar_layout?.title = comic.name

        // Opvullen van de content met de gekozen meme
        meme.let {
            rootView.text_detail_titel.text = it.titel
            rootView.text_detail_beschrijving.text = it.beschrijving
            rootView.text_detail_op.text = "By "+it.op
            Picasso.get().load(it.afbeelding).fit().into(rootView.image_detail_afbeelding)
            /*rootView.name.text = it.name
            rootView.description.text = it.description
            rootView.comic_image.setImageResource*/
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
