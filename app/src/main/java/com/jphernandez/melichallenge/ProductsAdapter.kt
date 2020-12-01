package com.jphernandez.melichallenge

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class ProductsAdapter (val onProductClick: (Product) -> Unit): ListAdapter<Product,ProductsAdapter.ViewHolder>(ProductDiffCallback) {

    class ViewHolder(view: View, val onProductClick: (Product) -> Unit): RecyclerView.ViewHolder(view) {
        val productNameTextView: TextView = view.findViewById(R.id.productName)
        val productPriceTextView: TextView = view.findViewById(R.id.productPrice)
        val productImageTextView: ImageView = view.findViewById(R.id.productImage)
        var product: Product? = null

        init {
            itemView.setOnClickListener {
                product?.let{
                    onProductClick(it)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_list_item, parent, false)
        return ViewHolder(view, onProductClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.product = getItem(position)
        holder.productNameTextView.text = getItem(position).title
        val price = "$ ${getItem(position).price}"
        holder.productPriceTextView.text = price
        displayThumbnail(getItem(position).thumbnail, holder.productImageTextView)
    }

    private fun displayThumbnail(url: String?, imageView: ImageView) {
        Glide.with(imageView.context)
            .load(url)
            .centerCrop()
            .placeholder(R.drawable.thumbnail_placeholder)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(imageView)
    }
}

object ProductDiffCallback: DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product) = oldItem == newItem
    override fun areContentsTheSame(oldItem: Product, newItem: Product) = oldItem.id == newItem.id
}