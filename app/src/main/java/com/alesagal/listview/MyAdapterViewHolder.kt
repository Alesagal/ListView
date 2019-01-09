package com.alesagal.listview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter


abstract class MyAdapterViewHolder<T, VH : MyAdapterViewHolder.ViewHolder>(
    context: Context,
    mData: List<T>,
    private val layoutId: Int,
    private val viewHolderClass: Class<out ViewHolder>
) : ArrayAdapter<T>(context, layoutId, mData) {

    private val mInflater: LayoutInflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val viewHolder: VH

        if (convertView == null) {
            view = mInflater.inflate(layoutId, parent, false)
            viewHolder = viewHolderClass.constructors[0].newInstance(view) as VH
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = convertView.tag as VH
        }

        // Write the information into the row.
        bindViewHolder(viewHolder, position)

        return view
    }

    abstract fun <VH> bindViewHolder(viewHolder: VH, position: Int)

    abstract class ViewHolder(val view: View)
}
