package ardents.`in`.masterbike.Activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ardents.`in`.masterbike.Adapter.PackagingAdapter
import ardents.`in`.masterbike.Model.PackagingModel
import ardents.`in`.masterbike.R
import ardents.`in`.masterbike.ViewModel.PackagingViewModel
import ardents.`in`.masterbike.databinding.ActivityPackageBinding

class PackageActivity : AppCompatActivity() {
    lateinit var binding:ActivityPackageBinding
    lateinit var adapter:PackagingAdapter
    lateinit var viewModel:PackagingViewModel
    var packagingList:List<PackagingModel> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      //  enableEdgeToEdge()
        binding=ActivityPackageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.toolbar.setOnClickListener {
            finish()
        }
        val modelId=intent.getStringExtra("modelId")
        val serviceType=intent.getStringExtra("serviceType")
        Log.d("modelid",modelId.toString())
        viewModel=ViewModelProvider(this).get(PackagingViewModel::class.java)
        adapter=PackagingAdapter(this,packagingList)
        binding.packageRecycler.adapter=adapter
        if(serviceType == "PeriodicService"){
            viewModel.periodicPackagingData(modelId.toString())
            periodicPackagingData()
        }else{
            viewModel.packageBookingData(modelId.toString())
            packageBookingData()
        }

    }
    fun periodicPackagingData(){
        viewModel.periodicPackagingData.observe(this, Observer {
            if (it.data!=null){
                Log.d("modelid",it.data.toString())
                packagingList=it.data
                adapter.updateList(packagingList)
                Log.d("packagedata","periodic service==== "+it.data.toString())
            }else{
                Toast.makeText(this,it.message.toString(),Toast.LENGTH_SHORT).show()
            }
        })
    }
    fun packageBookingData(){
        viewModel.packageBookingData.observe(this, Observer {
            if (it.data!=null){
                Log.d("modelid",it.data.toString())
                packagingList=it.data
                adapter.updateList(packagingList)
                Log.d("packagedata","package booking===== "+it.data.toString())
            }else{
                Toast.makeText(this,it.message.toString(),Toast.LENGTH_SHORT).show()
            }
        })
    }
}