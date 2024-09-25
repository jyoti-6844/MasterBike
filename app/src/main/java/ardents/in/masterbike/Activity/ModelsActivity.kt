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
import ardents.`in`.masterbike.Adapter.ModelAdapter
import ardents.`in`.masterbike.Model.Models
import ardents.`in`.masterbike.R
import ardents.`in`.masterbike.ViewModel.BrandViewModel
import ardents.`in`.masterbike.databinding.ActivityModelsBinding

class ModelsActivity : AppCompatActivity() {
    lateinit var binding:ActivityModelsBinding
    lateinit var viewModel: BrandViewModel
    lateinit var adapter: ModelAdapter
    var modelsList:List<Models> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // enableEdgeToEdge()
        binding=ActivityModelsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val brandid=intent.getStringExtra("brandId")
        val brandName=intent.getStringExtra("brandName")
        val serviceType=intent.getStringExtra("serviceType")
        Log.d("modeldata",serviceType.toString())
        viewModel=ViewModelProvider(this).get(BrandViewModel::class.java)
        adapter=ModelAdapter(this,modelsList,serviceType.toString())
        binding.brandRecycler.adapter=adapter
        viewModel.modelData(brandid.toString())
        brandData()

        binding.toolbar.setOnClickListener {
            finish()
        }
    }

    fun brandData(){
        viewModel.modelData.observe(this, Observer {
            if (it.data!=null){
                modelsList= it.data
                adapter.updateList(modelsList)
                Log.d("modeldata",it.data.toString())
            }else if (it.message.isNullOrEmpty()){
                Log.d("modeldata",it.message.toString())
               // Toast.makeText(this,it.message.toString(), Toast.LENGTH_SHORT).show()
                Toast.makeText(this,"No Data", Toast.LENGTH_SHORT).show()
            }

        })
    }
}