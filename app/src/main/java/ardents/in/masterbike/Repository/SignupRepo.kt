package ardents.`in`.masterbike.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ardents.`in`.masterbike.Model.BannerModel
import ardents.`in`.masterbike.Network.RetrofitClient
import ardents.`in`.masterbike.utils.NetworkResult
import ardents.`in`.masterbike.Model.SignUpModel

class SignupRepo {
    val _signupData= MutableLiveData<NetworkResult<SignUpModel>>()
    val signupData: LiveData<NetworkResult<SignUpModel>>
        get() = _signupData


    val _loginData=MutableLiveData<NetworkResult<SignUpModel>>()
    val loginData:LiveData<NetworkResult<SignUpModel>>
        get() = _loginData

    val _bannerData=MutableLiveData<NetworkResult<List<BannerModel>>>()
    val bannerData:LiveData<NetworkResult<List<BannerModel>>>
        get() = _bannerData


    suspend fun getSignupData(mail:String,passwd:String,mobile:String,name:String){
        try {
            _signupData.postValue(NetworkResult.Loading())
            val response = RetrofitClient.apiServices.singnUp(mail,passwd,mobile,name)
            if (response.isSuccessful && response.body()!=null){
                _signupData.postValue(NetworkResult.Success(response.body()!!))
            }else{
                _signupData.postValue(NetworkResult.Error(response.errorBody()?.string()))
            }
        }catch (e:Exception){
            _signupData.postValue(NetworkResult.Error(e.message))
        }
    }


    suspend fun getLoginData(mail:String,passwd:String,phone:String){
        try {
            _loginData.postValue(NetworkResult.Loading())
            val response=RetrofitClient.apiServices.login(mail,passwd,phone)
            if (response.isSuccessful && response.body()!=null){
                _loginData.postValue(NetworkResult.Success(response.body()!!))
            }else{
                _loginData.postValue(NetworkResult.Error(response.errorBody()?.string()))
            }
        }catch (e:Exception){
            _loginData.postValue(NetworkResult.Error(e.message))
        }
    }


    suspend fun getBannerData(){
        try {
            val response=RetrofitClient.apiServices.banner()
            if (response.isSuccessful && response.body()!=null){
                _bannerData.postValue(NetworkResult.Success(response.body()!!))
            }else{
                _bannerData.postValue(NetworkResult.Error(response.errorBody()?.string()))
            }
        }catch (e:Exception){
            _bannerData.postValue(NetworkResult.Error(e.message))
        }
    }
}