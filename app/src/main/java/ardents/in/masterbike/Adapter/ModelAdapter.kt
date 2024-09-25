package ardents.`in`.masterbike.Adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ardents.`in`.masterbike.Activity.InsuranceClaimActivity
import ardents.`in`.masterbike.Activity.ModelsActivity
import ardents.`in`.masterbike.Activity.PackageActivity
import ardents.`in`.masterbike.Activity.ServicesActivity
import ardents.`in`.masterbike.Model.BrandModel
import ardents.`in`.masterbike.Model.Models
import ardents.`in`.masterbike.databinding.ActivityModelsBinding
import ardents.`in`.masterbike.databinding.BrandlayBinding
import ardents.`in`.masterbike.databinding.ModellayBinding
import ardents.`in`.masterbike.utils.SharedPrefManager
import com.bumptech.glide.Glide

class ModelAdapter(val context: Context, var modelList: List<Models>,val serviceType:String): RecyclerView.Adapter<ModelAdapter.ViewHolder>() {
    class ViewHolder(val binding: ModellayBinding):RecyclerView.ViewHolder(binding.root) {

    }
    fun updateList(list: List<Models>){
        modelList=list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater= LayoutInflater.from(context)
        return ModelAdapter.ViewHolder(ModellayBinding.inflate(layoutInflater, parent, false))
    }

    override fun getItemCount(): Int {
        return modelList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load("http://crm.masterbike.in/"+modelList.get(position).Model_URL).into(holder.binding.imgModel)
        holder.binding.txtModel.text=modelList.get(position).Model_Name
        holder.binding.cardModel.setOnClickListener {
            SharedPrefManager.getInstance(context).setModel(modelList.get(position).Model_Name)
            if(serviceType == "PeriodicService" || serviceType == "PackageBooking"){
                val intent= Intent(context, PackageActivity::class.java)
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                intent.putExtra("modelId",modelList.get(position).Model_Id.toString())
                intent.putExtra("serviceType",serviceType)
                context.startActivity(intent)
            }else if (serviceType == "Insurance"){
                val intent= Intent(context, InsuranceClaimActivity::class.java)
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(intent)
            }
            else{
                val intent= Intent(context, ServicesActivity::class.java)
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                // Log.d("modeldata","id=="+brandList.get(position).Brand_Id.toString())
                context.startActivity(intent)
            }

        }
    }
}