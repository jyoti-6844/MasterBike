package ardents.`in`.masterbike.Activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ardents.`in`.masterbike.Adapter.BrandAdapter
import ardents.`in`.masterbike.R
import ardents.`in`.masterbike.ViewModel.BrandViewModel
import ardents.`in`.masterbike.databinding.ActivityBrandBinding
import ardents.`in`.masterbike.Model.BrandModel

class BrandActivity : AppCompatActivity() {
    lateinit var binding:ActivityBrandBinding
    lateinit var viewModel: BrandViewModel
    lateinit var adapter: BrandAdapter
    var brandList:List<BrandModel> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityBrandBinding.inflate(layoutInflater)
      //  enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val serviceType=intent.getStringExtra("serviceType")
        viewModel=ViewModelProvider(this).get(BrandViewModel::class.java)
        adapter=BrandAdapter(this,brandList,serviceType.toString())
        binding.brandRecycler.adapter=adapter
        viewModel.brandData()
        brandData()

         binding.toolbar.setOnClickListener {
             finish()
         }


    }
    fun brandData(){
        viewModel.brandData.observe(this, Observer {
            if (it.data!=null){
                brandList= it.data
                adapter.updateList(brandList)
            }else{
                Toast.makeText(this,it.message.toString(),Toast.LENGTH_SHORT).show()
            }

        })
    }
}