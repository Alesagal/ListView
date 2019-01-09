package com.alesagal.listview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter


abstract class MyAdapter<T>(
    context: Context,
    mData: List<T>,
    private val layoutId: Int
) : ArrayAdapter<T>(context, layoutId, mData) {

    private val mInflater: LayoutInflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View = convertView ?: mInflater.inflate(layoutId, parent, false)

        // Write the information into the row.
        bindViewHolder(view, position)

        return view
    }

    abstract fun bindViewHolder(view: View, position: Int)
}
