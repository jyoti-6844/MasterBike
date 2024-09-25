package ardents.`in`.masterbike.Activity

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ardents.`in`.masterbike.Adapter.SlotAdapter
import ardents.`in`.masterbike.Model.CreateBookingRequest
import ardents.`in`.masterbike.R
import ardents.`in`.masterbike.ViewModel.BookingViewModel
import ardents.`in`.masterbike.databinding.ActivityBookSlotBinding
import ardents.`in`.masterbike.Model.TimeSlotModel
import ardents.`in`.masterbike.utils.Helper
import ardents.`in`.masterbike.utils.SharedPrefManager
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class BookSlotActivity : AppCompatActivity() {
    lateinit var binding:ActivityBookSlotBinding
    lateinit var viewModel:BookingViewModel
    lateinit var adapter:SlotAdapter
    lateinit var slot:String

    var slotList:List<TimeSlotModel> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // enableEdgeToEdge()
        binding=ActivityBookSlotBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val price=intent.getStringExtra("price").toString()
        val cc=intent.getStringExtra("cc").toString()
        val sharedPref=SharedPrefManager.getInstance(this)
        binding.toobar.setOnClickListener { finish() }
        viewModel=ViewModelProvider(this).get(BookingViewModel::class.java)
        adapter=SlotAdapter(this,slotList){
            slot=it.PrefredTimeSlot_Name+">>"+it.PrefredTimeSlot_StartTime+"-"+it.PrefredTimeSlot_EndTime
          //  Toast.makeText(this,"slot= $slot",Toast.LENGTH_SHORT).show()
        }
        binding.slotsRecycler.adapter=adapter
        viewModel.timeSlotData()
        timeSlotData()

        binding.txtPrice.text="रु $price"
        binding.txtCC.text=cc

        binding.edtDate.setOnClickListener{
            Helper.showDatePicker(binding.edtDate,this@BookSlotActivity)
        }
        binding.address.text=SharedPrefManager.getInstance(this).getAddress()

        binding.btnBook.setOnClickListener {
            val landmark=binding.edtLandmark.text.toString()
            val pincode=binding.edtPincode.text.toString()
            val vehicle=binding.edtVehicleNo.text.toString()
            val desc=binding.edtDesc.text.toString()
            val date=binding.edtDate.text.toString()
            if (!Helper.validateEditText(binding.edtLandmark) ||
                !Helper.validateEditText(binding.edtPincode)||
                !Helper.validateEditText(binding.edtVehicleNo) ||
                !Helper.validateEditText(binding.edtDesc) ||
                !Helper.validateEditText(binding.edtDate)){
                return@setOnClickListener
            }else{
                viewModel.createBooking(CreateBookingRequest(price,sharedPref.getLoginResponse()!!.Login_Id,binding.address.text.toString(),binding.edtDate.text.toString(),binding.edtDesc.text.toString(),sharedPref.getLoginResponse()!!.Login_Email,binding.edtLandmark.text.toString(),
                    sharedPref.getLat(),sharedPref.getLoginResponse()!!.Login_Id,sharedPref.getLong(),sharedPref.getLoginResponse()!!.Login_Mobile,sharedPref.getLoginResponse()!!.Login_Name,binding.edtPincode.text.toString(),binding.edtVehicleNo.text.toString(),sharedPref.getBrand(),sharedPref.getLoginResponse()!!.Login_Id,sharedPref.getModel(),slot))
                booking()
            }

        }
    }

    fun timeSlotData(){
        viewModel.timeSlotData.observe(this, Observer {
            if (it.data!=null){
                slotList=it.data
                adapter.updateList(slotList)
            }else{
                Toast.makeText(this,it.message,Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun booking(){
        viewModel.bookingData.observe(this, Observer {
            if (it.data!=null){
                Toast.makeText(this,it.data.Response,Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this,it.message,Toast.LENGTH_SHORT).show()
            }
        })
    }


}