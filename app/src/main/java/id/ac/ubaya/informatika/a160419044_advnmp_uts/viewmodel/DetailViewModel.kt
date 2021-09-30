package id.ac.ubaya.informatika.a160419044_advnmp_uts.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.ac.ubaya.informatika.a160419044_advnmp_uts.model.User

class DetailViewModel:ViewModel() {
    val userLD = MutableLiveData<User>()

    fun refresh() {
        val user1 = User("16055","Nonie","1998/03/28","5718444778",
            "http://dummyimage.com/75x100.jpg/cc0000/ffffff")

        userLD.value = user1
    }

}