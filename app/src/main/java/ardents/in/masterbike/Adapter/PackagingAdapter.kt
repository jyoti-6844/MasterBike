package ardents.`in`.masterbike.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ardents.`in`.masterbike.Activity.BookSlotActivity
import ardents.`in`.masterbike.Model.ObjTblPackageComponent
import ardents.`in`.masterbike.Model.PackagingModel
import ardents.`in`.masterbike.databinding.PackaginglayBinding

class PackagingAdapter(val context: Context,var packageList: List<PackagingModel>): RecyclerView.Adapter<PackagingAdapter.ViewHolder>() {
    lateinit var componentList:List<ObjTblPackageComponent>
    class ViewHolder(val binding:PackaginglayBinding):RecyclerView.ViewHolder(binding.root) {

    }

    fun updateList(list:List<PackagingModel>){
        packageList=list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater=LayoutInflater.from(context)
        return ViewHolder(PackaginglayBinding.inflate(layoutInflater,parent,false))
    }

    override fun getItemCount(): Int {
        return packageList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.txtRange.text=packageList.get(position).Package_From_CC.toString()+"-"+packageList.get(position).Package_To_CC.toString()+" CC"
        holder.binding.txtPackageAmount.text="रु "+packageList.get(position).Package_Amount
        holder.binding.txtDiscount.text="Discount- "+packageList.get(position).Package_Discount.toInt()+"%"
        val totalGst=packageList.get(position).Package_C_GST + packageList.get(position).Package_S_GST
        holder.binding.txtGst.text="GST रु$totalGst"
        holder.binding.txtFinalamount.text="रु "+packageList.get(position).Package_Final_Amount.toString()
        componentList=packageList.get(position).obj_tbl_Package_Component
        val componentNames = componentList.joinToString(separator = "\n") { it.Package_Component_Name }
        holder.binding.packageComponent.text=componentNames
        holder.binding.btnConfirm.setOnClickListener {
            val intent=Intent(context,BookSlotActivity::class.java)
            intent.putExtra("price",packageList.get(position).Package_Final_Amount.toString())
            intent.putExtra("cc",packageList.get(position).Package_From_CC.toString()+"-"+packageList.get(position).Package_To_CC.toString()+" CC")
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
    }
}