package ardents.`in`.masterbike

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ardents.`in`.masterbike.Fragment.BookingFragment
import ardents.`in`.masterbike.Fragment.HomeFragment
import ardents.`in`.masterbike.Fragment.NotificationFragment
import ardents.`in`.masterbike.Fragment.ProfileFragment
import ardents.`in`.masterbike.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
     //   enableEdgeToEdge()
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val address=intent.getStringExtra("location").toString()

        val fragment=HomeFragment()
        val bundle=Bundle()
        bundle.putString("fulladdress",address)
        fragment.arguments=bundle
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView,fragment).commit()


        binding.bottomNavigation.setOnNavigationItemSelectedListener {item ->
            when(item.itemId){
                R.id.bottom_home ->{
                    supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView,HomeFragment()).commit()
                    true
                }
                R.id.bottom_booking ->{
                    supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView,BookingFragment()).commit()
                    true
                }
                R.id.bottom_notification ->{
                    supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView,NotificationFragment()).commit()
                    true
                }
                R.id.bottom_profile ->{
                    supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView,ProfileFragment()).commit()
                    true
                }
                else -> false

            }
        }



    }
}