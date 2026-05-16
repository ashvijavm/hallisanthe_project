package com.hallisanthe.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.hallisanthe.R
import com.hallisanthe.databinding.ItemProductBinding
import com.hallisanthe.model.Product

class ProductAdapter(
    private val onWishlistClick: (Product) -> Unit,
    private val onClick: (Product) -> Unit
) : ListAdapter<Product, ProductAdapter.ProductViewHolder>(DIFF) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ProductViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            val context = binding.root.context
            val config = context.resources.configuration
            val lang = config.locale.language

            binding.tvName.text = when (lang) {
                "kn" -> if (product.nameKn.isNotEmpty()) product.nameKn else product.name
                "hi" -> if (product.nameHi.isNotEmpty()) product.nameHi else product.name
                "ta" -> if (product.nameTa.isNotEmpty()) product.nameTa else product.name
                else -> product.name
            }

            binding.tvPrice.text = "₹ ${String.format("%.0f", product.price)}"
            binding.tvCategory.text = product.category
            binding.tvSeller.text = "by ${product.sellerName}"

            // Wishlist icon state
            binding.ivWishlist.setImageResource(
                if (product.isWishlisted) R.drawable.ic_wishlist else R.drawable.ic_wishlist
            )
            binding.ivWishlist.alpha = if (product.isWishlisted) 1.0f else 0.4f

            if (product.imageUrl.isNotEmpty()) {
                Glide.with(binding.root.context)
                    .load(product.imageUrl)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .centerCrop()
                    .placeholder(R.drawable.ic_product_placeholder)
                    .into(binding.ivProduct)
            } else {
                binding.ivProduct.setImageResource(R.drawable.ic_product_placeholder)
            }

            binding.root.setOnClickListener { onClick(product) }
            binding.ivWishlist.setOnClickListener { onWishlistClick(product) }
        }
    }

    companion object {
        val DIFF = object : DiffUtil.ItemCallback<Product>() {
            override fun areItemsTheSame(a: Product, b: Product) = a.id == b.id
            override fun areContentsTheSame(a: Product, b: Product) = a == b
        }
    }
}
