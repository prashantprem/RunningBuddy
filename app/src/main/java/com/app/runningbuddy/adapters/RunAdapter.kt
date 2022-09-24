package com.app.runningbuddy.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.runningbuddy.db.Run
import com.app.runningbuddy.other.TrackingUtility
import com.app.runningbuddy.R
import com.app.runningbuddy.databinding.ItemRunBinding
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.*

class RunAdapter : RecyclerView.Adapter<RunAdapter.RunViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<Run>() {
        override fun areItemsTheSame(oldItem: Run, newItem: Run): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Run, newItem: Run): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    // ListDiffer to efficiently deal with changes in the RecyclerView
    val differ = AsyncListDiffer(this, diffCallback)

     class RunViewHolder(val binding: ItemRunBinding) : RecyclerView.ViewHolder(binding.root)

    fun submitList(list: List<Run>) = differ.submitList(list)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RunViewHolder {
        val binding =  ItemRunBinding.inflate( LayoutInflater.from(parent.context),parent,false)
        return RunViewHolder(
            binding
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: RunViewHolder, position: Int) {
        val run = differ.currentList[position]

        // set item data
        holder.binding.apply {


            Glide.with(this.root).load(run.img).into(ivRunImage)

            val calendar = Calendar.getInstance().apply {
                timeInMillis = run.timestamp
            }
            val dateFormat = SimpleDateFormat("dd.MM.yy", Locale.getDefault())
            tvDate.text = dateFormat.format(calendar.time)

            "${run.avgSpeedInKMH}km/h".also {
                tvAvgSpeed.text = it
            }
            "${run.distanceInMeters / 1000f}km".also {
                tvDistance.text = it
            }
            tvTime.text = TrackingUtility.getFormattedStopWatchTime(run.timeInMillis)
            "${run.caloriesBurned}kcal".also {
                tvCalories.text = it
            }
        }
    }
}