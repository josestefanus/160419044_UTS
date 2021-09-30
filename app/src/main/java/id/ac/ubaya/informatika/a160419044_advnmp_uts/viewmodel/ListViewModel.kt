package id.ac.ubaya.informatika.a160419044_advnmp_uts.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.ac.ubaya.informatika.a160419044_advnmp_uts.model.User

class ListViewModel:ViewModel() {
    val usersLD = MutableLiveData<List<User>>()
    val loadingErrorLD = MutableLiveData<Boolean>()
    val loadingDoneLD = MutableLiveData<Boolean>()

    fun refresh() {
        val user1 =
            User("16055","Nonie","1998/03/28","5718444778","http://dummyimage.com/75x100.jpg/cc0000/ffffff")

        val user2 =
            User("13312","Rich","1994/12/14","3925444073","http://dummyimage.com/75x100.jpg/5fa2dd/ffffff")

        val user3 =
            User("11204","Dinny","1994/10/07","6827808747","http://dummyimage.com/75x100.jpg/5fa2dd/ffffff1")
        val userList:ArrayList<User> = arrayListOf<User>(user1, user2, user3)
        usersLD.value = userList
        loadingErrorLD.value = false
        loadingDoneLD.value = false
    }


}