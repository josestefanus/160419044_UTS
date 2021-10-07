package id.ac.ubaya.informatika.a160419044_advnmp_uts.view

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import id.ac.ubaya.informatika.a160419044_advnmp_uts.R
import id.ac.ubaya.informatika.a160419044_advnmp_uts.util.loadImage
import id.ac.ubaya.informatika.a160419044_advnmp_uts.viewmodel.DetailViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_user_detail.*
import kotlinx.android.synthetic.main.user_list_item.view.*
import java.util.concurrent.TimeUnit


class UserDetailFragment : Fragment() {

    private lateinit var viewModel:DetailViewModel
    private val userDetailAdapter = UserListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        //viewModel.refresh()
        //observeViewModel()

        btnBack.setOnClickListener {
            val action = UserDetailFragmentDirections.actionUserList()
            Navigation.findNavController(it).navigate(action)
        }

        arguments?.let {
            var userId = UserDetailFragmentArgs.fromBundle(requireArguments()).userId
            viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
            viewModel.refresh(userId)
            observeViewModel()
        }

    }

    fun observeViewModel() {
        viewModel.userLD.observe(viewLifecycleOwner, Observer {
            txtId.setText(viewModel.userLD.value?.id)
            txtName.setText(viewModel.userLD.value?.name)
            txtBod.setText(viewModel.userLD.value?.bod)
            txtPhone.setText(viewModel.userLD.value?.phone)

            if(viewModel.userLD.value?.status.equals("VACCINATED")) {
                txtStatusV.setText("VACCINATED")
                txtStatusV.setTextColor(Color.GREEN)
            } else {
                txtStatusV.setText("NOT VACCINATED")
                txtStatusV.setTextColor(Color.RED)
            }

            //txtStatusV.setText(viewModel.userLD.value?.status)
            imageView2.loadImage(viewModel.userLD.value?.photoUrl.toString(), progressBarDetail)

            var user = it
            btnNotif.setOnClickListener {
                Observable.timer(1, TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        Log.d("Messages", "five seconds")
                        MainActivity.showNotification(user.name.toString(),
                            "A new notification created",
                            R.drawable.ic_baseline_person_24)
                    }
            }

        })
    }
}