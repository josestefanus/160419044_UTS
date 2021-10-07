package id.ac.ubaya.informatika.a160419044_advnmp_uts.model

import com.google.gson.annotations.SerializedName

data class User(
    val id:String?,
    @SerializedName("user_name")
    val name:String?,
    @SerializedName("birth_of_date")
    val bod:String?,
    val phone:String?,
    @SerializedName("photo_url")
    val photoUrl:String?,
    val status:String?
)