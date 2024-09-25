package ardents.`in`.masterbike.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ardents.`in`.masterbike.Model.BookingData
import ardents.`in`.masterbike.Repository.BookingRepo
import ardents.`in`.masterbike.utils.NetworkResult
import ardents.`in`.masterbike.Model.TimeSlotModel
import ardents.`in`.masterbike.Model.CreateBookingData
import ardents.`in`.masterbike.Model.CreateBookingRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookingViewModel:ViewModel() {
    val bookingRepo=BookingRepo()
    val timeSlotData:LiveData<NetworkResult<List<TimeSlotModel>>>
        get() = bookingRepo.timeSlotData

    val showBookingData:LiveData<NetworkResult<List<BookingData>>>
        get() = bookingRepo.showBookingData

    val bookingData:LiveData<NetworkResult<CreateBookingData>>
        get() = bookingRepo.bookingData

    fun timeSlotData(){
        viewModelScope.launch(Dispatchers.IO){
            bookingRepo.gettimeSlotData()
        }
    }


    fun bookingData(loginMail:String,loginId:String){
        viewModelScope.launch(Dispatchers.IO) {
            bookingRepo.getBookingData(loginMail,loginId)
        }
    }

    fun createBooking(body: CreateBookingRequest){
        viewModelScope.launch(Dispatchers.IO) {
            bookingRepo.createBooking(body)
        }
    }
}