package ardents.`in`.masterbike.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ardents.`in`.masterbike.Model.PackagingModel
import ardents.`in`.masterbike.Network.RetrofitClient
import ardents.`in`.masterbike.utils.NetworkResult

class PackagingRepo {
    val _periodicPackagingData=MutableLiveData<NetworkResult<List<PackagingModel>>>()
    val periodicPackagingData:LiveData<NetworkResult<List<PackagingModel>>>
        get() = _periodicPackagingData

    val _packageBookingData=MutableLiveData<NetworkResult<List<PackagingModel>>>()
    val packageBookingData:LiveData<NetworkResult<List<PackagingModel>>>
        get() = _packageBookingData

    suspend fun getPeriodicPackagingData(model_id:String){
        try {
            val response=RetrofitClient.apiServices.periodicPackaging(model_id)
            if (response.isSuccessful && response.body()!=null){
                _periodicPackagingData.postValue(NetworkResult.Success(response.body()!!))
            }else{
                _periodicPackagingData.postValue(NetworkResult.Error(response.errorBody()?.string()))
            }
        }catch (e:Exception){
            _periodicPackagingData.postValue(NetworkResult.Error(e.message))
        }
    }


    suspend fun getPackageBookingData(model_id:String){
        try {
            val response=RetrofitClient.apiServices.packageBooking(model_id)
            if (response.isSuccessful && response.body()!=null){
                _packageBookingData.postValue(NetworkResult.Success(response.body()!!))
            }else{
                _packageBookingData.postValue(NetworkResult.Error(response.errorBody()?.string()))
            }
        }catch (e:Exception){
            _packageBookingData.postValue(NetworkResult.Error(e.message))
        }
    }
}