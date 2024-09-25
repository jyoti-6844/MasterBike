package ardents.`in`.masterbike.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ardents.`in`.masterbike.Model.CompanyModel
import ardents.`in`.masterbike.Network.RetrofitClient
import ardents.`in`.masterbike.utils.NetworkResult

class InsuranceRepo {
    val _companyData=MutableLiveData<NetworkResult<List<CompanyModel>>>()
    val companyData:LiveData<NetworkResult<List<CompanyModel>>>
        get() = _companyData


    suspend fun getCompanyData(){
        try {
            val response=RetrofitClient.apiServices.company()
            if (response.isSuccessful && response.body()!=null){
                _companyData.postValue(NetworkResult.Success(response.body()!!))
            }else{
                _companyData.postValue(NetworkResult.Error(response.errorBody()?.string()))
            }
        }catch (e:Exception){
            _companyData.postValue(NetworkResult.Error(e.message))
        }
    }

}