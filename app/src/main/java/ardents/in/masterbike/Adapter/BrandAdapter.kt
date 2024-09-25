package ardents.`in`.masterbike.Adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ardents.`in`.masterbike.Activity.ModelsActivity
import ardents.`in`.masterbike.databinding.BrandlayBinding
import ardents.`in`.masterbike.Model.BrandModel
import ardents.`in`.masterbike.utils.SharedPrefManager
import com.bumptech.glide.Glide

class BrandAdapter(val context: Context, var brandList: List<BrandModel>,val serviceType:String): RecyclerView.Adapter<BrandAdapter.ViewHolder>() {
    class ViewHolder(val binding:BrandlayBinding): RecyclerView.ViewHolder(binding.root) {

    }

    fun updateList(list: List<BrandModel>){
        brandList=list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater=LayoutInflater.from(context)
        return ViewHolder(BrandlayBinding.inflate(layoutInflater,parent,false))
    }

    override fun getItemCount(): Int {
        return brandList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load("http://crm.masterbike.in/"+brandList.get(position).Brand_URL).into(holder.binding.imgBrand)
        holder.binding.txtBrand.text=brandList.get(position).Brand_Name
        holder.binding.cardBrand.setOnClickListener {
            val intent=Intent(context,ModelsActivity::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.putExtra("brandId",brandList.get(position).Brand_Id.toString())
            SharedPrefManager.getInstance(context).setBrand(brandList.get(position).Brand_Name)
            intent.putExtra("serviceType",serviceType)
           // Log.d("modeldata","id=="+brandList.get(position).Brand_Id.toString())
            context.startActivity(intent)
        }
    }
}