package ardents.`in`.masterbike.Activity

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.provider.OpenableColumns
import android.util.Base64
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ardents.`in`.masterbike.Model.CompanyModel
import ardents.`in`.masterbike.R
import ardents.`in`.masterbike.ViewModel.InsuranceViewModel
import ardents.`in`.masterbike.databinding.ActivityInsuranceClaimBinding
import ardents.`in`.masterbike.utils.Helper
import java.io.ByteArrayOutputStream
import java.io.InputStream

class InsuranceClaimActivity : AppCompatActivity() {
    lateinit var binding:ActivityInsuranceClaimBinding
    var image:Int=0

    val PERMISSION_CODE=1001
    private var filename: String? = null
    private var imageBase64: String? = null
    private var firstImageBase64: String? = null
    private var secondImageBase64: String? = null
    private var thirdImageBase64: String? = null
    private var fourthImageBase64: String? = null
    lateinit var  firstFileName:String
    lateinit var  secondFileName:String
    lateinit var  thirdFileName:String
    lateinit var  fourthFileName:String
    lateinit var viewModel:InsuranceViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // enableEdgeToEdge()
        binding=ActivityInsuranceClaimBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        viewModel=ViewModelProvider(this).get(InsuranceViewModel::class.java)
        viewModel.companyData()
        companyData()

        binding.toolbar.setOnClickListener {
            finish()
        }
        binding.edtValidity.setOnClickListener {
            Helper.showDatePicker(binding.edtValidity,this@InsuranceClaimActivity)
        }
        binding.imgFirstSide.setOnClickListener {
            image=1
            chooseImageGallery()
        }
        binding.imgSecondSide.setOnClickListener {
            image=2
            chooseImageGallery()
        }
        binding.imgThirdSide.setOnClickListener {
            image=3
            chooseImageGallery()
        }
        binding.imgFourthSide.setOnClickListener {
            image=4
            chooseImageGallery()
        }
    }

    private fun companyData(){
        viewModel.companyData.observe(this, Observer {
            if (it.data != null) {
                val companyNames = it.data.map { it.Company_Name }
                val companyAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, companyNames)
                binding.autoCompleteTxtView.setAdapter(companyAdapter)
            } else {
                Toast.makeText(this, "Failed to load company data", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private val filePickerLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.data?.let { uri ->
                handleFile(uri)
            }
        }
    }


    private fun chooseImageGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        //   startActivityForResult(intent, IMAGE_CHOOSE)
        filePickerLauncher.launch(intent)
    }
    private fun handleFile(uri: Uri) {
        filename = getFileName(uri)
        imageBase64 = convertFileToBase64(uri)
        if (image==1){
            firstFileName= filename.toString()
            firstImageBase64=imageBase64
            firstImageBase64=imageBase64
            binding.imgFirstSide.setImageURI(uri)
        }else if (image == 2){
            secondFileName=filename.toString()
            secondImageBase64=imageBase64
            binding.imgSecondSide.setImageURI(uri)
        }else if (image == 3){
            thirdFileName=filename.toString()
            thirdImageBase64=imageBase64
            binding.imgThirdSide.setImageURI(uri)
        }else{
            fourthFileName=filename.toString()
            fourthImageBase64=imageBase64
            binding.imgFourthSide.setImageURI(uri)
        }

    }

    private fun getFileName(uri: Uri): String {
        var name = ""
        contentResolver.query(uri, null, null, null, null)?.use { cursor ->
            val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
            cursor.moveToFirst()
            name = cursor.getString(nameIndex)
        }
        return name
    }
    private fun convertFileToBase64(uri: Uri): String? {
        return try {
            val inputStream: InputStream? = contentResolver.openInputStream(uri)
            val byteArrayOutputStream = ByteArrayOutputStream()
            val buffer = ByteArray(1024)
            var bytesRead: Int
            while (inputStream?.read(buffer).also { bytesRead = it ?: -1 } != -1) {
                byteArrayOutputStream.write(buffer, 0, bytesRead)
            }
            Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

}