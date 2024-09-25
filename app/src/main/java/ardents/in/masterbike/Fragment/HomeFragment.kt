package ardents.`in`.masterbike.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ardents.`in`.masterbike.Activity.BrandActivity
import ardents.`in`.masterbike.Activity.InsuranceClaimActivity
import ardents.`in`.masterbike.Activity.ServicesActivity
import ardents.`in`.masterbike.Model.BannerModel
import ardents.`in`.masterbike.ViewModel.SignupViewModel
import ardents.`in`.masterbike.databinding.FragmentHomeBinding
import ardents.`in`.masterbike.utils.SharedPrefManager
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
   // val serviceList:MutableList<ServiceModel> = mutableListOf()
    lateinit var bannerList: List<BannerModel>
    lateinit var viewModel: SignupViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding=FragmentHomeBinding.inflate(inflater,container, false)
       // val fullAddress = arguments?.getString("fulladdress")


        viewModel=ViewModelProvider(this).get(SignupViewModel::class.java)



        viewModel.bannerData()
        bannerData()
        binding.layPeriodicService.setOnClickListener {
            val intent=Intent(requireContext(),BrandActivity::class.java)
            intent.putExtra("serviceType","PeriodicService")
            startActivity(intent)
        }
        binding.layCustomService.setOnClickListener {
            val intent=Intent(requireContext(),BrandActivity::class.java)
            intent.putExtra("serviceType","CustomService")
            startActivity(intent)
        }
        binding.layPackageBooking.setOnClickListener {
            val intent=Intent(requireContext(),BrandActivity::class.java)
            intent.putExtra("serviceType","PackageBooking")
            startActivity(intent)
        }
        binding.layInsuranceClaim.setOnClickListener {
            val intent=Intent(requireContext(),BrandActivity::class.java)
            intent.putExtra("serviceType","Insurance")
            startActivity(intent)
        }
        binding.address.text=SharedPrefManager.getInstance(requireContext()).getAddress()



        // Inflate the layout for this fragment
        return binding.root
    }

    fun bannerData(){
        viewModel.bannerdata.observe(viewLifecycleOwner, Observer {
            if (it.data!=null){
                bannerList = it.data
                val sliderList = ArrayList<SlideModel>()
                for (data in bannerList){
                    sliderList.add(SlideModel("http://crm.masterbike.in/"+data.AppBanner_ImageURL,ScaleTypes.FIT))
                }
                binding.imageSlider.setImageList(sliderList)
            }
            else{
                Toast.makeText(requireContext(),it.message,Toast.LENGTH_SHORT).show()
            }
        })
    }


}