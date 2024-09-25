package ardents.`in`.masterbike.utils

import android.content.Context
import ardents.`in`.masterbike.Model.SignUpModel
import com.google.gson.Gson

class SharedPrefManager(context:Context) {

    private val SHARED_PREF_LOGIN="login"
    private val SHARED_PREF_ADDRESS="address"
    private val SHARED_PREF_BRAND="brand"
    private val SHARED_PREF_MODEL="model"
    private val SHARED_PREF_LAT="lat"
    private val SHARED_PREF_LONG="long"
    val LOGIN_RESPONSE="response"
    val LOGIN_MAIL="email"
    val BRAND_NAME="brandName"
    val MODEL_NAME="modelName"
    val ADDRESS="fullAddress"
    val LATITUDE="lat"
    val LONGITUDE="long"
    private val gson = Gson()
    var mContext: Context
    init {
        mContext=context
    }
    companion object{
        private var mInstance:SharedPrefManager?=null

        @Synchronized
        fun getInstance(context: Context):SharedPrefManager{
            if (mInstance==null){
                mInstance= SharedPrefManager(context)
            }
            return mInstance!!
        }
    }


    fun setLoginResponse(response:SignUpModel){
        val sharedPreferences=mContext.getSharedPreferences(SHARED_PREF_LOGIN,Context.MODE_PRIVATE)
        val editor=sharedPreferences.edit()
        val json=gson.toJson(response)
        editor.putString(LOGIN_RESPONSE,json)
        editor.apply()
    }

    fun setAddress(address:String){
        val sharedPreferences=mContext.getSharedPreferences(SHARED_PREF_ADDRESS,Context.MODE_PRIVATE)
        val editor=sharedPreferences.edit()
        editor.putString(ADDRESS,address)
        editor.apply()
    }

    fun setBrand(address:String){
        val sharedPreferences=mContext.getSharedPreferences(SHARED_PREF_BRAND,Context.MODE_PRIVATE)
        val editor=sharedPreferences.edit()
        editor.putString(BRAND_NAME,address)
        editor.apply()
    }
    fun setModel(address:String){
        val sharedPreferences=mContext.getSharedPreferences(SHARED_PREF_MODEL,Context.MODE_PRIVATE)
        val editor=sharedPreferences.edit()
        editor.putString(MODEL_NAME,address)
        editor.apply()
    }
    fun setLat(address:String){
        val sharedPreferences=mContext.getSharedPreferences(SHARED_PREF_LAT,Context.MODE_PRIVATE)
        val editor=sharedPreferences.edit()
        editor.putString(LATITUDE,address)
        editor.apply()
    }
    fun setLong(address:String){
        val sharedPreferences=mContext.getSharedPreferences(SHARED_PREF_LONG,Context.MODE_PRIVATE)
        val editor=sharedPreferences.edit()
        editor.putString(LONGITUDE,address)
        editor.apply()
    }


    fun getLoginResponse(): SignUpModel? {
        val sharedPreferences=mContext.getSharedPreferences(SHARED_PREF_LOGIN,Context.MODE_PRIVATE)
        val json=sharedPreferences.getString(LOGIN_RESPONSE,null)
      //  val response=sharedPreferences.getString(LOGIN_RESPONSE,null)
        return if (json != null) {
            try {
                gson.fromJson(json, SignUpModel::class.java)
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        } else {
            null
        }
    }

    fun getAddress():String{
        val sharedPreferences=mContext.getSharedPreferences(SHARED_PREF_ADDRESS,Context.MODE_PRIVATE)
      //  val address=sharedPreferences.getString(ADDRESS,null)
        return sharedPreferences.getString(ADDRESS,null).toString()
    }
    fun getBrand():String{
        val sharedPreferences=mContext.getSharedPreferences(SHARED_PREF_BRAND,Context.MODE_PRIVATE)
        //  val address=sharedPreferences.getString(ADDRESS,null)
        return sharedPreferences.getString(BRAND_NAME,null).toString()
    }
    fun getModel():String{
        val sharedPreferences=mContext.getSharedPreferences(SHARED_PREF_MODEL,Context.MODE_PRIVATE)
        //  val address=sharedPreferences.getString(ADDRESS,null)
        return sharedPreferences.getString(MODEL_NAME,null).toString()
    }
    fun getLat():String{
        val sharedPreferences=mContext.getSharedPreferences(SHARED_PREF_LAT,Context.MODE_PRIVATE)
        //  val address=sharedPreferences.getString(ADDRESS,null)
        return sharedPreferences.getString(LATITUDE,null).toString()
    }
    fun getLong():String{
        val sharedPreferences=mContext.getSharedPreferences(SHARED_PREF_LONG,Context.MODE_PRIVATE)
        //  val address=sharedPreferences.getString(ADDRESS,null)
        return sharedPreferences.getString(LONGITUDE,null).toString()
    }


    fun clearLoginResponse() {
        val sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_LOGIN, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear().commit()
        editor.apply()

    }

}
