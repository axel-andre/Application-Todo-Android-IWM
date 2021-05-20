package iim.axelandre.com.todoapp.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import iim.axelandre.com.todoapp.R
import iim.axelandre.com.todoapp.domain.SharedPreferencesUtils

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        SharedPreferencesUtils.initSharedPreferences(this)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.mainActivityFragmentHost) as NavHostFragment

        navController = navHostFragment.navController

        navigateToTodoFragment()

    }

    fun navigateTo(actionId : Int) {
        navController.navigate(actionId)
    }

    fun navigateToTodoFragment() {
        if (!(SharedPreferencesUtils.getFirstname().isNullOrBlank() && SharedPreferencesUtils.getLastame().isNullOrBlank())) {
            navController.navigate(R.id.action_loginFragment_to_todoFragment)
        }
    }
}