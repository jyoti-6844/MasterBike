package ardents.`in`.masterbike.Activity
import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.TaskStackBuilder
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ardents.`in`.masterbike.Fragment.HomeFragment
import ardents.`in`.masterbike.MainActivity
import ardents.`in`.masterbike.R
import ardents.`in`.masterbike.databinding.ActivitySplashBinding
import ardents.`in`.masterbike.utils.SharedPrefManager
import com.bumptech.glide.Glide
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.util.Locale

class SplashActivity : AppCompatActivity() {
    lateinit var binding: ActivitySplashBinding
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    lateinit var geocoder: Geocoder
    var fullAddress:String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // enableEdgeToEdge()
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        geocoder=Geocoder(this, Locale.getDefault())
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        checkLocationPermissions()

        Log.d("loginresponse", SharedPrefManager.getInstance(this).getLoginResponse().toString())
        Glide.with(this).load(R.drawable.masterbike).into(binding.imageview)


    }
    override fun onResume() {
        super.onResume()
        getLastKnownLocation()
        // After permissions are granted, check if location services are enabled
//        if (isLocationEnabled()) {
//            getLastKnownLocation()  // Get the location if enabled
//        } else {
//            showLocationDialog()  // Prompt user to enable location
//        }
    }
    private fun checkLocationPermissions() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Request permissions if not already granted
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                1000
            )
        } else {
            // Permissions are already granted, check if location is enabled
            if (isLocationEnabled()) {
                getLastKnownLocation()  // Get the location if enabled
            } else {
                showLocationDialog()  // Prompt user to enable location
            }
        }
    }

    private fun isLocationEnabled(): Boolean {
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }
    private fun showLocationDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Do you want to turn on location?")
            .setTitle("Location is not enabled")
            .setCancelable(false)
            .setPositiveButton("Yes") { dialog, id ->
                // Navigate to location settings to enable location
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
            }
            .setNegativeButton("No") { dialog, id ->
                finish()
               // dialog.dismiss()
               // Toast.makeText(this, "Location is required for this feature", Toast.LENGTH_SHORT).show()
            }
        val alert: AlertDialog = builder.create()
        alert.show()
    }

    private fun getLastKnownLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ) {
           // return
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ), 1000)
        }
        fusedLocationClient.getLastLocation().addOnSuccessListener { location: Location? ->
            if (location != null) {
                val lat = location.latitude
                val lon = location.longitude
                val addresses: List<Address>? = geocoder.getFromLocation(lat, lon, 1)
                if (addresses != null && addresses.isNotEmpty()) {
                    val fullAddress = addresses[0].getAddressLine(0)
                    // Navigate to next activity with the address
                    SharedPrefManager.getInstance(this).setAddress(fullAddress!!)
                    Handler(Looper.getMainLooper()).postDelayed({
                            val loginMsg = SharedPrefManager.getInstance(this).getLoginResponse()?.Message.toString()
                        if (!loginMsg.isNullOrEmpty() && loginMsg == "Success") {
                            val intent = Intent(this, MainActivity::class.java)
                            intent.putExtra("location", fullAddress)
                            startActivity(intent)
                            finish()
                        } else {
                            startActivity(Intent(this, LoginActivity::class.java))
                            finish()
                        }
                    }, 3000)
                }
            }
        }
    }







}











