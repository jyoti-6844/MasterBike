package ardents.`in`.masterbike.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ardents.`in`.masterbike.Adapter.ServicesAdapter
import ardents.`in`.masterbike.Model.CustomServicesModel
import ardents.`in`.masterbike.R
import ardents.`in`.masterbike.ViewModel.BrandViewModel
import ardents.`in`.masterbike.databinding.ActivityServicesBinding

class ServicesActivity : AppCompatActivity() {
    lateinit var binding:ActivityServicesBinding
    lateinit var viewModel: BrandViewModel
    lateinit var adapter: ServicesAdapter
    var customServicesList:List<CustomServicesModel> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // enableEdgeToEdge()
        binding=ActivityServicesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        viewModel=ViewModelProvider(this).get(BrandViewModel::class.java)

        binding.toobar.setOnClickListener {
            finish()
        }
        binding.btnNext.setOnClickListener {
            startActivity(Intent(this,BookSlotActivity::class.java))
        }

        adapter= ServicesAdapter(this,customServicesList)
        binding.serviceRecycler.adapter=adapter
        viewModel.customServicesData("1")
        customServicesData()
    }

    fun customServicesData(){
        viewModel.customServicesData.observe(this, Observer {
            if (it.data!=null){
                customServicesList=it.data
                adapter.updateList(customServicesList)
            }else{
                Toast.makeText(this,it.message.toString(),Toast.LENGTH_SHORT).show()
            }
        })
    }
}