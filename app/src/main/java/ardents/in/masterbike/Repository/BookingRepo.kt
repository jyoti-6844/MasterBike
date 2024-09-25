package ardents.`in`.masterbike.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ardents.`in`.masterbike.Model.BookingData
import ardents.`in`.masterbike.Network.RetrofitClient
import ardents.`in`.masterbike.utils.NetworkResult
import ardents.`in`.masterbike.Model.TimeSlotModel
import ardents.`in`.masterbike.Model.CreateBookingData
import ardents.`in`.masterbike.Model.CreateBookingRequest

class BookingRepo {
    val _timeSlotData=MutableLiveData<NetworkResult<List<TimeSlotModel>>>()
    val timeSlotData:LiveData<NetworkResult<List<TimeSlotModel>>>
        get() = _timeSlotData

    val _showBookingData=MutableLiveData<NetworkResult<List<BookingData>>>()
    val showBookingData:LiveData<NetworkResult<List<BookingData>>>
        get() = _showBookingData

    val _bookingData=MutableLiveData<NetworkResult<CreateBookingData>>()
    val bookingData:LiveData<NetworkResult<CreateBookingData>>
        get() = _bookingData

    suspend fun gettimeSlotData(){
        try {
            val response=RetrofitClient.apiServices.timeSlot()
            if (response.isSuccessful && response.body()!=null){
                _timeSlotData.postValue(NetworkResult.Success(response.body()!!))
            }else{
                _timeSlotData.postValue(NetworkResult.Error(response.errorBody()?.string()))
            }
        }catch (e:Exception){
            _timeSlotData.postValue(NetworkResult.Error(e.message))
        }
    }


    suspend fun getBookingData(loginMail:String,loginId:String){
        try {
            val response=RetrofitClient.apiServices.showBooking(loginMail,loginId)
            if (response.isSuccessful && response.body()!=null){
                _showBookingData.postValue(NetworkResult.Success(response.body()!!))
            }else{
                _showBookingData.postValue(NetworkResult.Error(response.errorBody()?.string()))
            }
        }catch (e:Exception){
            _showBookingData.postValue(NetworkResult.Error(e.message))
        }
    }

    suspend fun createBooking(body: CreateBookingRequest){
        try {
            val response=RetrofitClient.apiServices.createBooking(body)
            if (response.isSuccessful && response.body()!=null){
                _bookingData.postValue(NetworkResult.Success(response.body()!!))
            }else{
                _bookingData.postValue(NetworkResult.Error(response.errorBody()?.string()))
            }
        }catch (e:Exception){
            _bookingData.postValue(NetworkResult.Error(e.message))
        }
    }
}