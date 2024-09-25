package ardents.`in`.masterbike.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ardents.`in`.masterbike.Model.CompanyModel
import ardents.`in`.masterbike.Repository.InsuranceRepo
import ardents.`in`.masterbike.utils.NetworkResult
import kotlinx.coroutines.launch

class InsuranceViewModel:ViewModel() {
    val insuranceRepo=InsuranceRepo()
    val companyData:LiveData<NetworkResult<List<CompanyModel>>>
        get() = insuranceRepo.companyData

    fun companyData(){
        viewModelScope.launch() {
            insuranceRepo.getCompanyData()
        }
    }
}