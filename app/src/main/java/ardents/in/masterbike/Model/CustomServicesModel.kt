package ardents.`in`.masterbike.Model

data class CustomServicesModel(
    val CustomService_Id: Int,
    val CustomService_Name: String,
    val CustomService_Status: Int,
    val CustomService_Type: String,
    var isSelected: Boolean = false
)