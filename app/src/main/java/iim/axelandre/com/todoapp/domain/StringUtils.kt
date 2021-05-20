package iim.axelandre.com.myapplication.domain

import androidx.core.util.PatternsCompat


object StringUtils {
    fun isValidEmail(email: String): Boolean
            = email.isNotEmpty() && PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()

    fun isValidPassword(password: String): Boolean
            = password.isNotEmpty() && password.length >= 4
}