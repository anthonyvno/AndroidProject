package com.example.anthonyvannoppen.androidproject.domain

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.anthonyvannoppen.androidproject.R
import com.example.anthonyvannoppen.androidproject.fragments.MemeDetailFragment
import kotlinx.android.synthetic.main.fragment_comment.view.*

class MyCommentRecyclerViewAdapter(private val parentActivity: MemeDetailFragment,
                                   private val comments: List<Comment>) :
    RecyclerView.Adapter<MyCommentRecyclerViewAdapter.ViewHolder>() {

    private val onClickListener: View.OnClickListener

    init {
        onClickListener = View.OnClickListener { v ->
            // Every view has a tag that can be used to store data related to that view
            // Here each item in the RecyclerView keeps a reference to the comic it represents.
            // This allows us to reuse a single listener for all items in the list
            val item = v.tag as Comment


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_comment, parent, false)
        return ViewHolder(view)
    }

    //vul de viewholder
    @SuppressLint("NewApi")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val comment = comments[position]
        holder.tekst.text = comment.tekst
        holder.op.text = "By " + comment.op


        with(holder.itemView) {
            tag = comment // Save the meme represented by this view
            setOnClickListener(onClickListener)
        }
    }

    override fun getItemCount() = comments.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tekst: TextView = view.text_comment_tekst
        val op: TextView = view.text_comment_op
    }

}