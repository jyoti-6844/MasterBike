package ardents.`in`.masterbike.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ardents.`in`.masterbike.Network.RetrofitClient
import ardents.`in`.masterbike.utils.NetworkResult
import ardents.`in`.masterbike.Model.BrandModel
import ardents.`in`.masterbike.Model.CustomServicesModel
import ardents.`in`.masterbike.Model.Models

class BrandRepo {

    val _brandData=MutableLiveData<NetworkResult<List<BrandModel>>>()
    val brandData:LiveData<NetworkResult<List<BrandModel>>>
    get() = _brandData

    val _modelData=MutableLiveData<NetworkResult<List<Models>>>()
    val modelData:LiveData<NetworkResult<List<Models>>>
        get() = _modelData

    val _customServiceData=MutableLiveData<NetworkResult<List<CustomServicesModel>>>()
    val customServiceData:LiveData<NetworkResult<List<CustomServicesModel>>>
        get() = _customServiceData




    suspend fun getBrandData(){
        try {
            val response=RetrofitClient.apiServices.brand()
            if (response.isSuccessful && response.body()!=null){
                _brandData.postValue(NetworkResult.Success(response.body()!!))
            }else{
                _brandData.postValue(NetworkResult.Error(response.errorBody()?.string()))
            }
        }catch (e:Exception){
            _brandData.postValue(NetworkResult.Error(e.message))
        }
    }


    suspend fun getModelData(brandid:String){
        try {
            val response=RetrofitClient.apiServices.model(brandid)
            if (response.isSuccessful && response.body()!=null){
                _modelData.postValue(NetworkResult.Success(response.body()!!))
            }else{
                _modelData.postValue(NetworkResult.Error(response.errorBody()?.string()))
            }
        }catch (e:Exception){
            _modelData.postValue(NetworkResult.Error(e.message))
        }
    }


    suspend fun getCustomServicesData(number:String){
        try {
            val response= RetrofitClient.apiServices.customService(number)
            if (response.isSuccessful && response.body()!=null){
                _customServiceData.postValue(NetworkResult.Success(response.body()!!))
            }else{
                _customServiceData.postValue(NetworkResult.Error(response.errorBody()?.string()))
            }
        }catch (e:Exception){
            _customServiceData.postValue(NetworkResult.Error(e.message))
        }
    }
}