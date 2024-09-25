package ardents.`in`.masterbike.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import ardents.`in`.masterbike.databinding.SlotsLayBinding
import ardents.`in`.masterbike.Model.TimeSlotModel
import ardents.`in`.masterbike.R

class SlotAdapter(val context: Context,var slotList: List<TimeSlotModel>,private val onSlotSelected:(TimeSlotModel) -> Unit): RecyclerView.Adapter<SlotAdapter.ViewHolder>() {
    private var selectedPosition = -1
    class ViewHolder(val binding:SlotsLayBinding):RecyclerView.ViewHolder(binding.root) {

    }
    fun updateList(list: List<TimeSlotModel>){
        slotList=list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater=LayoutInflater.from(context)
        return ViewHolder(SlotsLayBinding.inflate(layoutInflater,parent,false))
    }

    override fun getItemCount(): Int {
        return slotList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val slot = slotList[position]

        holder.binding.slotName.text = slot.PrefredTimeSlot_Name
        holder.binding.slotTime.text = "${slot.PrefredTimeSlot_StartTime} - ${slot.PrefredTimeSlot_EndTime}"

        // Update UI to reflect selection state
        if (position == selectedPosition) {
            holder.binding.slotTime.setTextColor(ContextCompat.getColor(context, android.R.color.white))
            holder.binding.slotTime.setBackgroundColor(ContextCompat.getColor(context, R.color.appColor))
        } else {
            holder.binding.slotTime.setTextColor(ContextCompat.getColor(context, android.R.color.black))
            holder.binding.slotTime.setBackgroundColor(ContextCompat.getColor(context, R.color.mediumgrey))
        }

        // Handle click on the slot
        holder.binding.slotTime.setOnClickListener {
            // Remember the previous selection
            val previousSelectedPosition = selectedPosition

            // Update the selected position
            selectedPosition = position

            // Notify adapter to refresh UI for previous and current selection
            notifyItemChanged(previousSelectedPosition)
            notifyItemChanged(selectedPosition)

            // Invoke the callback to pass the selected slot to the Activity/Fragment
            onSlotSelected(slot) // This sends the selected slot data back
        }
    }
}
