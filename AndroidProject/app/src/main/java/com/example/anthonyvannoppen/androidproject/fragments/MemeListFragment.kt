package com.example.anthonyvannoppen.androidproject.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.*

import com.example.anthonyvannoppen.androidproject.R

import com.example.anthonyvannoppen.androidproject.domain.Meme
import com.example.anthonyvannoppen.androidproject.domain.MyMemeRecyclerViewAdapter
import com.example.anthonyvannoppen.androidproject.ui.MemeViewModel


import kotlinx.android.synthetic.main.fragment_meme_list.*

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [MemeListFragment.OnListFragmentInteractionListener] interface.
 */
class MemeListFragment : Fragment() {

    private lateinit var viewModel: MemeViewModel
    private var memes: List<Meme>? = null

    private lateinit var adapter: MyMemeRecyclerViewAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        viewModel = ViewModelProviders.of(activity!!).get(MemeViewModel::class.java)


        return inflater.inflate(R.layout.fragment_meme_list, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStart() {
        super.onStart()


        //vul de recyclerview aan de hand van alle items of  gesorteerd per categorie
        if(memes==null){
            viewModel.getMemes().observe(this, Observer {
                Log.d("",it.toString())
                adapter = MyMemeRecyclerViewAdapter(
                    this,
                    it!!.sortedBy { meme -> meme.titel })
                fragment_meme_list.adapter = adapter
                adapter.notifyDataSetChanged()


            })
        } else {
            adapter = MyMemeRecyclerViewAdapter(this, memes!!)
            fragment_meme_list.adapter = adapter

        }


        fragment_meme_list.layoutManager= LinearLayoutManager(activity)

    }

    override fun onResume() {
        super.onResume()
        //adapter.notifyDataSetChanged()
    }


    fun startNewFragmentForDetail(item: Meme) {
        val memeDetailFragment = MemeDetailFragment()
        this.fragmentManager!!.beginTransaction()
            .replace(R.id.container_main,memeDetailFragment)
            .addToBackStack(null)
            .commit()
        memeDetailFragment.addObject(item)
    }

    fun sort(meme:List<Meme>){
        this.memes = meme
    }


    // niet in gebruik
    /*companion object {

        fun newInstance(list: ArrayList<Meme>): MemeDetailFragment {
            val args = Bundle()
            args.putSerializable("list", list)
            val fragment = MemeDetailFragment()
            fragment.arguments = args

            return fragment
        }
    }*/
}
