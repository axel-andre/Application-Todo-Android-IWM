package iim.axelandre.com.todoapp.data.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("prenom") val firstname : String,
    @SerializedName("nom") val lastname : String,
    @SerializedName("email") val email: String,
    val password: String?
)
