package com.example.homework3_lecture5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.homework3_lecture5.databinding.ActivityMainBinding
import android.util.Patterns

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val emailEdit = binding.emailEdit
        val phoneEdit = binding.phoneEdit
        val usernameEdit = binding.usernameEdit
        val firstNameEdit = binding.firstNameEdit
        val lastNameEdit = binding.lastNameEdit
        val ageEdit = binding.ageEdit
        val saveButton = binding.saveButton
        val clearButton = binding.clearButton
        val inputArray =
            arrayOf(emailEdit, phoneEdit, usernameEdit, firstNameEdit, lastNameEdit, ageEdit)

        //function to iterate through inputs to check whether a field is empty
        fun filledInputs(): Boolean {
            for (input in inputArray) {
                val inputtedText = input.text.toString()
                if (inputtedText.isEmpty()) {
                    Toast.makeText(this, "პროფილის რედაქტირებისთვის გთხოვთ შეავსოთ ყველა ველი", Toast.LENGTH_SHORT).show().toString()
                    return false
                }
            }
            return true
        }
        //used predefined email validator
        fun emailValidation(inputtedEmail:String): Boolean {
            val emailMatcher = Patterns.EMAIL_ADDRESS.matcher(inputtedEmail)
            if (emailMatcher.matches()) {
                emailMatcher.toString()
                return true
            }
            Toast.makeText(this, "გთხოვთ შეიყვანოთ მეილი სწორი ფორმატით", Toast.LENGTH_SHORT).show().toString()
            return false
        }
        //added phone input, just thought would look more complete
        //in xml i wrote for it to only accept number input but I still wrote code to trim the string
        //if statement checks whether the string starts with 5 (standard Georgian Number) and contains 9 digits
        fun phoneValidation(inputtedPhone:String): Boolean {
            val numberToString = inputtedPhone.trim()
            return if (numberToString.startsWith('5') && numberToString.length == 9){//es chasasworebelia
//                numberToString
                true
            }else{
                Toast.makeText(this, "გთხოვთ შეიყვანოთ ნომერი სწორი ფორმატით [5** *** ***]", Toast.LENGTH_SHORT).show().toString()
                false
            }
        }
        //simple validation for username, it just checks if the symbols are more than 10 in total
        //and doesn't contain " " so that an empty string is not registered as input
        fun usernameValidation(inputtedUsername:String): Boolean {
            return if(inputtedUsername.length > 10 && !inputtedUsername.contains(" ")) {
//                inputtedUsername
                true
            }else{
                Toast.makeText(this, "გთხოვთ შეიყვანოთ 10 სიმბოლოზე მეტი", Toast.LENGTH_SHORT).show().toString()
                false
            }
        }
        //same goes here, but the number of characters doesn't matter
        fun nameBinder(inputtedFirstName:String, inputtedLastName:String): Boolean {
            return if(!inputtedFirstName.contains(" ") && !inputtedLastName.contains(" ")) {
//                "$inputtedFirstName $inputtedLastName"
                true
            }else{
                Toast.makeText(this, "გთხოვთ შეიყვანოთ სახელი ან/და გვარი სწორი ფორმატით", Toast.LENGTH_SHORT).show().toString()
                false
            }
        }
        //I restricted the usage of letters or symbols from xml so they are unlikely to enter
        //but i still wrote the exception handling
        //without the outer if else try catch still allowed "negative" numbers (with "-") sign
        //so I had to improvise
        fun ageValidation(inputtedAge:String): Boolean {
            if(!inputtedAge.contains(".") && !inputtedAge.contains("-")){
                return try {
                    val ageToInt = inputtedAge.toInt()
                    (ageToInt >= 1).toString()
                    true
                } catch (e: NumberFormatException) {
                    Toast.makeText(this, "გთხოვთ შეიყვანოთ თქვენი ასაკი სწორი ფორმატირებით", Toast.LENGTH_SHORT).show().toString()
                    false
                }
            }
            Toast.makeText(this, "გთხოვთ შეიყვანოთ თქვენი ასაკი სწორი ფორმატირებით", Toast.LENGTH_SHORT).show().toString()
            return false
        }
        //clearButton sets eveyrything to an empty string when clicked
        clearButton.setOnClickListener {
            emailEdit.setText("")
            phoneEdit.setText("")
            usernameEdit.setText("")
            firstNameEdit.setText("")
            lastNameEdit.setText("")
            ageEdit.setText("")
        }
        //save button serves as an execution trigger, it tiggers the code inside the setOnClickListener
        //in the if else statement and the conditions are checked with their respective inputs
        saveButton.setOnClickListener {
            if (filledInputs() &&
                emailValidation(emailEdit.text.toString()) &&
                phoneValidation(phoneEdit.text.toString()) &&
                usernameValidation(usernameEdit.text.toString()) &&
                nameBinder(firstNameEdit.text.toString(), lastNameEdit.text.toString()) &&
                ageValidation(ageEdit.text.toString())){
                Toast.makeText(this, "მონაცემები წარმატებით შეინახა", Toast.LENGTH_SHORT).show().toString()
            }
        }
    }
}