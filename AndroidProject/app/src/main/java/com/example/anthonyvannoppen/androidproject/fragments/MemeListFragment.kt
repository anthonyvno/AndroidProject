package com.example.anthonyvannoppen.androidproject.fragments

import android.os.Bundle
import android.support.v4.app.Fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.anthonyvannoppen.androidproject.R

import com.example.anthonyvannoppen.androidproject.domain.Comment
import com.example.anthonyvannoppen.androidproject.domain.Meme


import kotlinx.android.synthetic.main.fragment_meme_list.*

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [MemeListFragment.OnListFragmentInteractionListener] interface.
 */
class MemeListFragment : Fragment() {


    private var memes: List<Meme>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_meme_list, container, false)

        /*// Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = MyMemeRecyclerViewAdapter(DummyContent.ITEMS, listener)
            }
        }*/
        return view
    }


    override fun onStart() {
        super.onStart()

        memes = createMemes()


        fragment_meme_list.adapter = MyMemeRecyclerViewAdapter(this, memes!!)
        //fragment_meme_list.layoutManager = LinearLayoutManager(this)
    }
    private fun createMemes(): List<Meme> {
        val memeList = mutableListOf<Meme>()

        val resources = activity!!.applicationContext.resources
        val titels = resources.getStringArray(R.array.titels)
        val beschrijvingen = resources.getStringArray(R.array.beschrijvingen)
        val ops = resources.getStringArray(R.array.ops)
        val afbeeldingen = resources.getStringArray(R.array.afbeeldingen)
        val categorieen = resources.getStringArray(R.array.categorieen)

        for (i in 0 until titels.size) {
            val legelijst = listOf<Comment>()
            val theComic = Meme(ops[i], titels[i], afbeeldingen[i], beschrijvingen[i],categorieen[i],legelijst)
            memeList.add(theComic)
        }

        return memeList
    }

    fun startNewActivityForDetail(item: Meme) {
        val memeDetailFragment = MemeDetailFragment()
        this.fragmentManager!!.beginTransaction()
            .replace(R.id.container_main,memeDetailFragment)
            .addToBackStack(null)
            .commit()
        memeDetailFragment.addObject(item)
        //startActivity(intent)
    }

    companion object {

        fun newInstance(list: ArrayList<Meme>): MemeDetailFragment {
            val args = Bundle()
            args.putSerializable("list", list)
            val fragment = MemeDetailFragment()
            fragment.arguments = args

            return fragment
        }
    }
}
