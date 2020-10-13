package com.vmo.ecom.presentation.ui.shop.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vmo.ecom.R
import com.vmo.ecom.domain.model.WorkingWeekDay
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_opening_hours.view.*

class OpeningHourAdapter(
    private val openingHours: MutableList<WorkingWeekDay>
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HourViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val workingWeekDay = openingHours[position]
        (holder as HourViewHolder).bind(workingWeekDay)
    }

    override fun getItemCount(): Int {
        return openingHours.size
    }

    class HourViewHolder(override val containerView: View): RecyclerView.ViewHolder(containerView), LayoutContainer {
        companion object {
            fun create(parent: ViewGroup): HourViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_opening_hours, parent, false)
                return HourViewHolder(view)
            }
        }

        fun bind(workingWeekDay: WorkingWeekDay) {
            itemView.tvWeekDay.text = workingWeekDay.weekDay
            itemView.tvHours.text = workingWeekDay.openHour + " - " + workingWeekDay.closeHour
        }
    }
}