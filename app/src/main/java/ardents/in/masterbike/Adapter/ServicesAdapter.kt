package ardents.`in`.masterbike.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ardents.`in`.masterbike.Model.CustomServicesModel
import ardents.`in`.masterbike.databinding.ServiceslayBinding

class ServicesAdapter(val context: Context,var servicesList: List<CustomServicesModel>): RecyclerView.Adapter<ServicesAdapter.ViewHolder>() {
    class ViewHolder(val binding: ServiceslayBinding):RecyclerView.ViewHolder(binding.root) {
    }

    fun updateList(list: List<CustomServicesModel>){
        servicesList=list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
      val layoutInflater=LayoutInflater.from(context)
      return ViewHolder(ServiceslayBinding.inflate(layoutInflater,parent,false))
    }

    override fun getItemCount(): Int {
        return servicesList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.txtServiceName.text=servicesList.get(position).CustomService_Name
        holder.binding.cbService.isChecked = servicesList.get(position).isSelected
        holder.binding.cbService.setOnCheckedChangeListener { compoundButton, b ->
            servicesList.get(position).isSelected=b
        }
    }
}