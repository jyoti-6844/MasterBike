package ardents.`in`.masterbike.Fragment

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ardents.`in`.masterbike.Adapter.BookingAdapter
import ardents.`in`.masterbike.Model.BookingData
import ardents.`in`.masterbike.R
import ardents.`in`.masterbike.ViewModel.BookingViewModel
import ardents.`in`.masterbike.databinding.FragmentBookingBinding
import ardents.`in`.masterbike.utils.SharedPrefManager
import retrofit2.http.Tag


class BookingFragment : Fragment() {
    lateinit var  binding:FragmentBookingBinding
    lateinit var viewModel:BookingViewModel
    var bookingList:List<BookingData> = mutableListOf()
    lateinit var adapter:BookingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentBookingBinding.inflate(inflater,container,false)
        viewModel=ViewModelProvider(this).get(BookingViewModel::class.java)
        adapter=BookingAdapter(requireContext(),bookingList)
        binding.bookingRecycler.adapter=adapter
        viewModel.bookingData(SharedPrefManager.getInstance(requireContext()).getLoginResponse()!!.Login_Email,SharedPrefManager.getInstance(requireContext()).getLoginResponse()!!.Login_Id)
        bookingData()





        return binding.root
    }
    fun bookingData(){
        viewModel.showBookingData.observe(viewLifecycleOwner, Observer {
            if (it.data!=null){
                Log.d("jyotidata","data======="+it.data.toString())
                bookingList = it.data!!
                adapter.updateList(bookingList)
            }else if(it.message.isNullOrEmpty()){
                Toast.makeText(requireContext(),"No data",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(requireContext(),"Failed to load Bookings",Toast.LENGTH_SHORT).show()
            }
        })
    }


}