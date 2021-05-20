package iim.axelandre.com.todoapp.domain

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

object SharedPreferencesUtils {

    private const val LASTNAME = "lastname"
    private const val FIRSTNAME = "firstname"
    private const val PREF_ID = "app.sharedpreferences"

    private var sharedPreferences: SharedPreferences? = null

    fun initSharedPreferences(context: Context) {
        sharedPreferences = context.getSharedPreferences(PREF_ID, MODE_PRIVATE)
    }

    fun resetLastame() {
        sharedPreferences?.edit()?.remove(LASTNAME)?.apply()
    }

    fun setLastame(lastname: String) {
        sharedPreferences?.edit()?.putString(LASTNAME, lastname)?.apply()
    }

    fun getLastame(): String? {
        return sharedPreferences?.getString(LASTNAME, null)
    }

    fun resetFirstname() {
        sharedPreferences?.edit()?.remove(FIRSTNAME)?.apply()
    }

    fun setFirstname(firstname: String) {
        sharedPreferences?.edit()?.putString(FIRSTNAME, firstname)?.apply()
    }

    fun getFirstname(): String? {
        return sharedPreferences?.getString(FIRSTNAME, null)
    }

}