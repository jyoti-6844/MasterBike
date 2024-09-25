package ardents.`in`.masterbike.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import ardents.`in`.masterbike.Model.BookingData
import ardents.`in`.masterbike.databinding.BookinglayBinding

class BookingAdapter(val context: Context, var bookingList:List<BookingData>): RecyclerView.Adapter<BookingAdapter.ViewHoldder>() {
    class ViewHoldder(val binding:BookinglayBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHoldder {
        val layoutInflater=LayoutInflater.from(context)
        return ViewHoldder(BookinglayBinding.inflate(layoutInflater,parent,false))
    }
    fun updateList(list: List<BookingData>){
        bookingList=list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return bookingList.size
    }

    override fun onBindViewHolder(holder: ViewHoldder, position: Int) {
       holder.binding.bookingId.text="Booking Id - "+bookingList.get(position).Booking_Id.toString()
        holder.binding.bookingDate.text="Date - "+bookingList.get(position).Booking_Date
        holder.binding.vehicleNo.text="Vehicle No. "+bookingList.get(position).Booking_VehicleNo
        holder.binding.bookingAddress.text="Address - "+bookingList.get(position).Booking_Address
        holder.binding.bookingTime.text="Time - "+bookingList.get(position).PrefredTimeSlot_SlotName
    }
}