package ru.susu.scsusu.presentation.plots

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.susu.scsusu.databinding.QuestElementBinding
import ru.susu.scsusu.domain.ButtonRes

class QuestAdapter(val onClicked: (buttonRes: ButtonRes) -> Unit) :
    RecyclerView.Adapter<QuestAdapter.QuestViewHolder>() {

    private var data: MutableList<ButtonRes> = mutableListOf()

    fun setData(newData: MutableList<ButtonRes>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    inner class QuestViewHolder(val binding: QuestElementBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestViewHolder {
        return QuestViewHolder(
            QuestElementBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: QuestViewHolder, position: Int) {
        holder.binding.textView.text =
            "(${position + 1}) ${holder.binding.root.context.getString(data[position].id)}"
        holder.binding.root.setOnClickListener {
            onClicked(data[position])
        }
    }

    override fun getItemCount(): Int = data.size
}