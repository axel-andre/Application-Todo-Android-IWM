package iim.axelandre.com.todoapp.presentation.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import iim.axelandre.com.todoapp.domain.SharedPreferencesUtils
import iim.axelandre.com.myapplication.domain.StringUtils
import iim.axelandre.com.todoapp.R
import iim.axelandre.com.todoapp.presentation.activity.MainActivity
import kotlinx.android.synthetic.main.fragment_register.*

/**
 * A simple [Fragment] subclass.
 * Use the [RegisterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegisterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerButton.setOnClickListener {
            val email: String = registerEmailInput.text.toString()
            val password: String = registerPasswordInput.text.toString()
            val passwordConfirmation: String = registerConfirmationPasswordInput.text.toString()
            val lastname: String = registerLastnameInput.text.toString()
            val firstname: String = registerFirstnameInput.text.toString()

            if(validateForm(email, password, lastname, firstname, passwordConfirmation)) {
                register(lastname, firstname)
            } else {
                Log.d("RegisterFragment","Username ou mot de passe invalide")
            }
        }
    }

    private fun validateForm (
        email: String,
        password: String,
        lastname: String,
        firstname: String,
        passwordConfirmation: String
    ) : Boolean {

        var isValid = true

        registerEmailInput.error =
            if (!StringUtils.isValidEmail(email)) {
                isValid = false
                getString(R.string.valid_email)
            } else null

        registerPasswordInput.error =
            if (password != passwordConfirmation) {
                isValid = false
                getString(R.string.password_different)
            } else null
        registerConfirmationPasswordInput.error = registerPasswordInput.error

        registerPasswordInput.error =
            if (!StringUtils.isValidPassword(password)) {
                isValid = false
                getString(R.string.password_length)
            } else null

        registerLastnameInput.error =
            if (lastname.isEmpty()) {
                isValid = false
                getString(R.string.required_field)
            } else null

        registerFirstnameInput.error =
            if (firstname.isEmpty()) {
                isValid = false
                getString(R.string.required_field)
            } else null



        return isValid
    }

    private fun register(lastname: String, firstname : String) {

        SharedPreferencesUtils.setLastame(lastname)
        SharedPreferencesUtils.setFirstname(firstname)
        (activity as MainActivity).navigateToTodoFragment()
    }

}