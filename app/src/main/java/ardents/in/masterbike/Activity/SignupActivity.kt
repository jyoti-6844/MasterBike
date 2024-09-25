package ardents.`in`.masterbike.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ardents.`in`.masterbike.MainActivity
import ardents.`in`.masterbike.R
import ardents.`in`.masterbike.ViewModel.SignupViewModel
import ardents.`in`.masterbike.databinding.ActivitySignupBinding
import ardents.`in`.masterbike.utils.Helper
import ardents.`in`.masterbike.utils.NetworkResult

class SignupActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignupBinding
    lateinit var signupViewModel:SignupViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      //  enableEdgeToEdge()
        binding=ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        signupViewModel=ViewModelProvider(this).get(SignupViewModel::class.java)

        binding.btnSignup.setOnClickListener {
            val name=binding.edtUsername.text.toString().trim()
            val phone=binding.edtPhone.text.toString().trim()
            val mail=binding.edtMail.text.toString().trim()
            val password=binding.edtPasswd.text.toString().trim()
            if (!Helper.validateEditText(binding.edtUsername)||
                !Helper.validateEditText(binding.edtPhone)||
                !Helper.validateEditText(binding.edtPasswd)||
                !Helper.isValidMail(binding.edtMail))
            {
                return@setOnClickListener
            }else{
                signupViewModel.signupData(mail,password, phone,name)
                signupData()
            }
            binding.edtUsername.text=null
            binding.edtMail.text=null
            binding.edtPasswd.text=null
            binding.edtPhone.text=null
        }

        binding.txtLogin.setOnClickListener {
            startActivity(Intent(applicationContext,LoginActivity::class.java))
            finish()
        }
    }

    fun signupData(){
        signupViewModel.signupData.observe(this, Observer {
            Helper.dismissProgressDialog()
            when(it){
               is NetworkResult.Success->{
                   if (it.data?.Message == "Success"){
                       Toast.makeText(this,"Register Successfully",Toast.LENGTH_SHORT).show()
                       startActivity(Intent(applicationContext,MainActivity::class.java))
                       finish()
                   }else{
                       Toast.makeText(this,it.data?.Message.toString(),Toast.LENGTH_SHORT).show()
                   }
                }
                is NetworkResult.Error ->{
                    Toast.makeText(this,it.message, Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Loading ->{
                    Helper.showProgressDialog(this)
                }else ->{
                Toast.makeText(this,"Something went wrong", Toast.LENGTH_SHORT).show()
            }
            }


        })
    }
}