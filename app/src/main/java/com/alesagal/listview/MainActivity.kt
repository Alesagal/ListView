package com.alesagal.listview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.AdapterView.OnItemClickListener


class MainActivity : AppCompatActivity() {

    private lateinit var mAdapter1: MyAdapter<String>
    private lateinit var mAdapter2: MyAdapterViewHolder<String, out ViewHolderImpl>
    private lateinit var mList1: ArrayList<String>
    private lateinit var mList2: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mList1 = ArrayList(listOf("unoh", "doh", "træh"))
        mList2 = ArrayList(listOf("unoh", "doh", "træh"))

        // Without ViewHolder.
        mAdapter1 = object : MyAdapter<String>(this, mList1, R.layout.list_view) {
            override fun bindViewHolder(view: View, position: Int) {
                view.findViewById<TextView>(R.id.lblText).text = mList1[position]
            }
        }

        listView1.adapter = mAdapter1

        listView1.onItemClickListener = OnItemClickListener { adapter, view, position, id ->
            mList1.add(position.toString())
            mAdapter1.notifyDataSetChanged()
            //(listView1.adapter as MyAdapter).notifyDataSetChanged()
        }

        // --------------------------------------

        // With ViewHolder.
        mAdapter2 = object : MyAdapterViewHolder<String, ViewHolderImpl>(this, mList2, R.layout.list_view, ViewHolderImpl::class.java) {
            override fun <VH> bindViewHolder(viewHolder: VH, position: Int) {
                // Smart cast.
                (viewHolder as ViewHolderImpl)
                viewHolder.lblText.text = mList2[position]
            }
        }

        listView2.adapter = mAdapter2

        listView2.onItemClickListener = OnItemClickListener { adapter, view, position, id ->
            mList2.add(position.toString())
            mAdapter2.notifyDataSetChanged()
            //(listView2.adapter as MyAdapterViewHolder<*>).notifyDataSetChanged()
        }
    }

    class ViewHolderImpl(view: View) : MyAdapterViewHolder.ViewHolder(view) {
        val lblText = view.findViewById<TextView>(R.id.lblText)!!
    }
}
