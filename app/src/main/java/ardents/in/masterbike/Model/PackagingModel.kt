package ardents.`in`.masterbike.Model

data class PackagingModel(
    val Package_AddedBy: Int,
    val Package_Amount: Double,
    val Package_C_GST: Double,
    val Package_Discount: Double,
    val Package_Final_Amount: Double,
    val Package_From_CC: Int,
    val Package_Id: Int,
    val Package_S_GST: Double,
    val Package_Status: Int,
    val Package_To_CC: Int,
    val Package_Total_Duration: Int,
    val Package_Total_Service: Int,
    val Package_Type: String,
    val Package_Type_Text: String,
    val obj_tbl_Package_Component: List<ObjTblPackageComponent>
)