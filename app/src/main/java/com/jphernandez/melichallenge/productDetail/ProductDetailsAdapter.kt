package com.jphernandez.melichallenge.productDetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jphernandez.melichallenge.R
import com.jphernandez.melichallenge.data.ProductAttribute

class ProductDetailsAdapter (): ListAdapter<ProductAttribute, ProductDetailsAdapter.ViewHolder>(DetailDiffCallback){

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val detailTitle: TextView = view.findViewById(R.id.detail_title)
        val detailValue: TextView = view.findViewById(R.id.detail_value)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_detail_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.detailTitle.text = getItem(position).name
        holder.detailValue.text = getItem(position).value_name
    }

}

object DetailDiffCallback: DiffUtil.ItemCallback<ProductAttribute>() {
    override fun areItemsTheSame(oldItem: ProductAttribute, newItem: ProductAttribute) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: ProductAttribute, newItem: ProductAttribute) = oldItem.name == newItem.name && oldItem.value_name == newItem.value_name
}