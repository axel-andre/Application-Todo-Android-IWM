package iim.axelandre.com.todoapp.presentation.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import iim.axelandre.com.myapplication.data.network.ApiClient
import iim.axelandre.com.todoapp.domain.SharedPreferencesUtils
import iim.axelandre.com.myapplication.domain.StringUtils
import iim.axelandre.com.todoapp.R
import iim.axelandre.com.todoapp.data.model.User
import iim.axelandre.com.todoapp.presentation.activity.MainActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginButton.setOnClickListener {
            val email: String = loginEmailInput.text.toString()
            val password: String = loginPasswordInput.text.toString()

            loginEmailInput.error = if (!StringUtils.isValidEmail(email)) getString(R.string.valid_email) else null
            loginPasswordInput.error = if (password.isEmpty()) getString(R.string.required_field) else null

            if(email.isNotBlank() && password.isNotBlank()) {
                login(email, password)
            } else {
                Log.d("LoginFragment","Username ou mot de passe invalide")
            }
        }

        registerButton.setOnClickListener {
            (activity as MainActivity).navigateTo(R.id.action_loginFragment_to_registerFragment)
        }
    }

    private fun login(email: String, password : String) {
        val call = ApiClient.apiService.login(email, password)

        call.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {

                response.body()?.let {
                    SharedPreferencesUtils.setLastame(it.lastname)
                    SharedPreferencesUtils.setFirstname(it.firstname)
                    (activity as MainActivity).navigateToTodoFragment()
                } ?: run {
                    Toast.makeText(this@LoginFragment.requireActivity(), getString(R.string.retrieve_user_data_error), Toast.LENGTH_SHORT).show()
                }


            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.d("LoginFragment","Error when login user")
                Toast.makeText(this@LoginFragment.requireActivity(), getString(R.string.login_user_error), Toast.LENGTH_SHORT).show()
            }
        })

    }

}