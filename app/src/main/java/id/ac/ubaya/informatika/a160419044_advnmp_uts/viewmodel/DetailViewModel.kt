package id.ac.ubaya.informatika.a160419044_advnmp_uts.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import id.ac.ubaya.informatika.a160419044_advnmp_uts.model.User

class DetailViewModel(application: Application):AndroidViewModel(application) {
    val userLD = MutableLiveData<User>()

    private val TAG = "volleyTag"
    private var queue: RequestQueue?= null

    fun refresh(userId: String)
    {
        queue = Volley.newRequestQueue(getApplication())
        val url = "https://raw.githubusercontent.com/josestefanus/user_data/main/user$userId.php"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                val sType = object : TypeToken<List<User>>() {}.type
                val result = Gson().fromJson<User>(response, User::class.java)
                userLD.value = result
                Log.d("showvolley", response.toString())
            },
            {
                Log.d("showvolley", it.toString())
            })
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}