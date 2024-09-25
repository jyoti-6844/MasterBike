package ardents.`in`.masterbike.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ardents.`in`.masterbike.Repository.BrandRepo
import ardents.`in`.masterbike.utils.NetworkResult
import ardents.`in`.masterbike.Model.BrandModel
import ardents.`in`.masterbike.Model.CustomServicesModel
import ardents.`in`.masterbike.Model.Models
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BrandViewModel:ViewModel() {

    val brandRepo=BrandRepo()
    val brandData:LiveData<NetworkResult<List<BrandModel>>>
        get() = brandRepo.brandData

    val modelData:LiveData<NetworkResult<List<Models>>>
        get() = brandRepo.modelData

    val customServicesData:LiveData<NetworkResult<List<CustomServicesModel>>>
        get() = brandRepo.customServiceData

    fun brandData(){
        viewModelScope.launch(Dispatchers.IO) {
            brandRepo.getBrandData()
        }
    }

    fun modelData(brandid:String){
        viewModelScope.launch(Dispatchers.IO) {
            brandRepo.getModelData(brandid)
        }
    }

    fun customServicesData(number:String){
        viewModelScope.launch(Dispatchers.IO) {
            brandRepo.getCustomServicesData(number)
        }
    }
}