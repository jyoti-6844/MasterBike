package ardents.`in`.masterbike.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ardents.`in`.masterbike.Model.PackagingModel
import ardents.`in`.masterbike.Repository.PackagingRepo
import ardents.`in`.masterbike.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PackagingViewModel:ViewModel() {
    val packagingRepo=PackagingRepo()
    val periodicPackagingData:LiveData<NetworkResult<List<PackagingModel>>>
        get() = packagingRepo.periodicPackagingData

    val packageBookingData:LiveData<NetworkResult<List<PackagingModel>>>
        get() = packagingRepo.packageBookingData

    fun periodicPackagingData(model_id:String){
        viewModelScope.launch(Dispatchers.IO) {
            packagingRepo.getPeriodicPackagingData(model_id)
        }
    }

    fun packageBookingData(model_id:String){
        viewModelScope.launch(Dispatchers.IO) {
            packagingRepo.getPackageBookingData(model_id)
        }
    }
}