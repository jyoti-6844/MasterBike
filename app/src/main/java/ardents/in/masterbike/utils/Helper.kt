package ardents.`in`.masterbike.utils

import android.app.DatePickerDialog
import android.app.ProgressDialog
import android.content.Context
import android.text.Editable
import android.util.Patterns
import android.view.View
import android.widget.EditText
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

object Helper {
     var progressDialog:ProgressDialog?=null
    private val calendar = Calendar.getInstance()
    fun showProgressDialog(context: Context){
        progressDialog = ProgressDialog(context)
        progressDialog?.setMessage("Loading....")
        progressDialog?.setCancelable(false)
        progressDialog?.show()
        //return progressDialog
    }


    fun dismissProgressDialog() {
        progressDialog?.let {
            if (it.isShowing){
                progressDialog?.dismiss()
            }
        }
        progressDialog=null
    }

    fun validateEditText(editText: EditText): Boolean {
        val validateText = editText.text.toString()
        editText.visibility = View.VISIBLE
        return if (validateText.isEmpty()) {
            editText.error = "This field is required"
            false
        } else {
            editText.error = null
            true
        }
    }




    fun isValidMail(editText: EditText):Boolean{
        val validateText=editText.text.toString().trim()
        editText.visibility = View.VISIBLE
        return if (validateText.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(validateText).matches()){
           editText.error="Not a valid mail"
            false
        }
        else{
            editText.error=null
            true
        }


    }

     fun showDatePicker(setDate: EditText,context:Context) {
        // Create a DatePickerDialog
        val datePickerDialog = DatePickerDialog(
            context, {DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                // Create a new Calendar instance to hold the selected date
                val selectedDate = Calendar.getInstance()
                // Set the selected date using the values received from the DatePicker dialog
                selectedDate.set(year, monthOfYear, dayOfMonth)
                // Create a SimpleDateFormat to format the date as "dd/MM/yyyy"
                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                // Format the selected date into a string
                val formattedDate = dateFormat.format(selectedDate.time)
                // Update the TextView to display the selected date with the "Selected Date: " prefix
                setDate.text = Editable.Factory.getInstance().newEditable(formattedDate)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        // Show the DatePicker dialog
        datePickerDialog.show()
    }

}