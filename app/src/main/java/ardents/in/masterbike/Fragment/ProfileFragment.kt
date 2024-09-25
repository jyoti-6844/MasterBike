package ardents.`in`.masterbike.Fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import ardents.`in`.masterbike.Activity.LoginActivity
import ardents.`in`.masterbike.R
import ardents.`in`.masterbike.databinding.FragmentProfileBinding
import ardents.`in`.masterbike.utils.SharedPrefManager


class ProfileFragment : Fragment() {
    lateinit var binding:FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentProfileBinding.inflate(inflater,container, false,)
        val userData=SharedPrefManager.getInstance(requireContext()).getLoginResponse()
        binding.txtUserName.text=userData?.Login_Name
        binding.txtUserMail.text=userData?.Login_Email
        binding.txtUserPhone.text=userData?.Login_Mobile
        binding.layPhone1.setOnClickListener {
            val callIntent=Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+binding.txtPhone1.text.toString()))
            startActivity(callIntent)
        }
        binding.layContactMail.setOnClickListener {
            val mailIntent=Intent(Intent.ACTION_SENDTO)
            mailIntent.setData(Uri.parse(binding.txtContactMail.text.toString()))
            startActivity(Intent.createChooser(mailIntent,"Choose an email client:"))
        }
        binding.toobar.setOnClickListener {
            fragmentManager?.popBackStack()
        }
        binding.layContactWhatsapp.setOnClickListener {
            val whatsappIntent=Intent(Intent.ACTION_VIEW)
            whatsappIntent.setData(Uri.parse("http://api.whatsapp.com/send?phone="+"+91"+7275016110))
            startActivity(whatsappIntent)
        }
        binding.btnLogout.setOnClickListener{
            showDialog()
        }






        // Inflate the layout for this fragment
        return binding.root
    }

    fun showDialog() {
        val dialog = AlertDialog.Builder(requireContext())
        dialog.setTitle("Logout")
        dialog.setMessage("Are you sure want to logout ?")
        dialog.setPositiveButton("Yes") { dialog, _ ->
            SharedPrefManager.getInstance(requireContext()).clearLoginResponse()
            val intent=Intent(requireContext(),LoginActivity::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }
        dialog.setNegativeButton("No") { dialog, _ ->
            dialog.dismiss()
        }
        dialog.setCancelable(false)
        dialog.create()
        dialog.show()

    }


}