package ardents.`in`.masterbike.Activity

import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ardents.`in`.masterbike.MainActivity
import ardents.`in`.masterbike.R
import ardents.`in`.masterbike.ViewModel.SignupViewModel
import ardents.`in`.masterbike.databinding.ActivityLoginBinding
import ardents.`in`.masterbike.utils.Helper
import ardents.`in`.masterbike.utils.NetworkResult
import ardents.`in`.masterbike.utils.SharedPrefManager

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var viewModel: SignupViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // enableEdgeToEdge()
        binding=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        viewModel=ViewModelProvider(this).get(SignupViewModel::class.java)

        binding.txtCreateAcc.setOnClickListener {
            startActivity(Intent(this,SignupActivity::class.java))
            finish()
        }

        binding.txtUsernamelogin.setOnClickListener {
            binding.txtUsernamelogin.visibility = View.GONE
            binding.txtPhonelogin.visibility = View.VISIBLE
            binding.linearPhone.visibility = View.GONE
            binding.linearUser.visibility = View.VISIBLE
        }
        binding.txtPhonelogin.setOnClickListener {
            binding.linearPhone.visibility = View.VISIBLE
            binding.linearUser.visibility = View.GONE
            binding.txtPhonelogin.visibility = View.GONE
            binding.txtUsernamelogin.visibility = View.VISIBLE
        }

        binding.hideLoginPasswd.setOnClickListener {
            var cursorPosition:Int=binding.edtPass.selectionStart
            binding.edtPass.transformationMethod= HideReturnsTransformationMethod.getInstance()
            binding.hideLoginPasswd.visibility= View.GONE
            binding.showLoginPasswd.visibility= View.VISIBLE
            binding.edtPass.setSelection(cursorPosition)
        }

        binding.showLoginPasswd.setOnClickListener {
            var cusorPosition:Int=binding.edtPass.selectionStart
            binding.edtPass.transformationMethod= PasswordTransformationMethod.getInstance()
            binding.showLoginPasswd.visibility= View.GONE
            binding.hideLoginPasswd.visibility= View.VISIBLE
            binding.edtPass.setSelection(cusorPosition)
        }
        binding.btnLogin.setOnClickListener {
            val mail=binding.edtMail.text.toString().trim()
            val password=binding.edtPass.text.toString().trim()
            if (!Helper.isValidMail(binding.edtMail)||
                !Helper.validateEditText(binding.edtPass)
            ){
                return@setOnClickListener
            }else{
                viewModel.loginData(mail,password,"")
                loginData("username")
            }
            binding.edtPass.text=null
            binding.edtMail.text=null
        }
        binding.btnOtplogin.setOnClickListener {
            val phone=binding.edtPhone.text.toString()
            if (!Helper.validateEditText(binding.edtPhone)){
                return@setOnClickListener
            }else{
                viewModel.loginData("","",phone)
                loginData("phone")
            }

        }
        }

    fun loginData(loginWith:String){
        viewModel.loginData.observe(this, Observer {
            Helper.dismissProgressDialog()
            when(it){
                is NetworkResult.Success->{
                    if (it.data?.Message=="Success"){
                        SharedPrefManager.getInstance(this).setLoginResponse(it.data)
                        if (loginWith.equals("username")){
                            Toast.makeText(this, "Login Successfull", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this, MainActivity::class.java))
                            finish()
                        }else{
                            startActivity(Intent(this, OTPActivity::class.java))
                            finish()
                        }
                    }else{
                        Toast.makeText(this,it.data?.Message.toString(),Toast.LENGTH_SHORT).show()
                    }

                }
                is NetworkResult.Error->{
                    Toast.makeText(this,it.message, Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Loading->{
                    Helper.showProgressDialog(this)
                }
            }
        })


    }
}