package ardents.`in`.masterbike.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ardents.`in`.masterbike.Model.BannerModel
import ardents.`in`.masterbike.Repository.SignupRepo
import ardents.`in`.masterbike.utils.NetworkResult
import ardents.`in`.masterbike.Model.SignUpModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignupViewModel:ViewModel() {
    val signupRepo=SignupRepo()
    val signupData:LiveData<NetworkResult<SignUpModel>>
        get() = signupRepo.signupData

    val loginData:LiveData<NetworkResult<SignUpModel>>
        get() = signupRepo.loginData

    val bannerdata:LiveData<NetworkResult<List<BannerModel>>>
        get() = signupRepo.bannerData

    fun signupData(mail:String,passwd:String,mobile:String,name:String){
        viewModelScope.launch(Dispatchers.IO) {
            signupRepo.getSignupData(mail, passwd, mobile, name)
        }
    }

    fun loginData(mail:String,passwd:String,phone:String){
        viewModelScope.launch(Dispatchers.IO) {
            signupRepo.getLoginData(mail, passwd,phone)
        }
    }

    fun bannerData(){
        viewModelScope.launch(Dispatchers.IO) {
            signupRepo.getBannerData()
        }
    }

}