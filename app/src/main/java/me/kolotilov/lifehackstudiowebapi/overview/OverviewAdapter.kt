package me.kolotilov.lifehackstudiowebapi.overview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import me.kolotilov.lifehackstudiowebapi.R
import me.kolotilov.lifehackstudiowebapi.overview.OverviewFragmentDirections.Companion.actionOverviewFragmentToDetailsFragment
import me.kolotilov.lifehackstudiowebapi.utils.BASE_URL


class OverviewAdapter(
    private val data: List<OverviewData>,
    private val fragment: Fragment
) : RecyclerView.Adapter<OverviewViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OverviewViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.overview_item, parent, false)
        return OverviewViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: OverviewViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            fragment.findNavController().navigate(actionOverviewFragmentToDetailsFragment(item.id))
        }
    }
}

class OverviewViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(data: OverviewData) {
        val nameText = itemView.findViewById<TextView>(R.id.overview_item_text)
        val iconImage = itemView.findViewById<ImageView>(R.id.overview_item_image)

        nameText.text = data.name
        Glide.with(itemView)
            .load(BASE_URL + data.imgSrc)
            .centerCrop()
            .placeholder(R.drawable.image_placeholder)
            .into(iconImage)
    }
}