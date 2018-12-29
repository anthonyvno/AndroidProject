package com.example.anthonyvannoppen.androidproject.fragments

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.anthonyvannoppen.androidproject.R
import com.example.anthonyvannoppen.androidproject.domain.Meme
import com.squareup.picasso.Picasso


import kotlinx.android.synthetic.main.fragment_meme.view.*

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class MyMemeRecyclerViewAdapter(private val parentActivity: MemeListFragment,
                                    private val memes: List<Meme>) :
    RecyclerView.Adapter<MyMemeRecyclerViewAdapter.ViewHolder>() {

    private val onClickListener: View.OnClickListener

    init {
        onClickListener = View.OnClickListener { v ->
            // Every view has a tag that can be used to store data related to that view
            // Here each item in the RecyclerView keeps a reference to the comic it represents.
            // This allows us to reuse a single listener for all items in the list
            val item = v.tag as Meme

            parentActivity.startNewActivityForDetail(item)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_meme, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val meme = memes[position]
        holder.titel.text = meme.titel
        holder.op.text = meme.op
        Picasso.get().load("https://quizizz.zendesk.com/hc/article_attachments/115002501069/1024x1024.jpg").into(holder.afbeelding)

        with(holder.itemView) {
            tag = meme // Save the comic represented by this view
            setOnClickListener(onClickListener)
        }
    }

    override fun getItemCount() = memes.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titel: TextView = view.text_meme_titel
        val op: TextView = view.text_meme_op
        val afbeelding: ImageView = view.image_meme_afbeelding
    }
}

