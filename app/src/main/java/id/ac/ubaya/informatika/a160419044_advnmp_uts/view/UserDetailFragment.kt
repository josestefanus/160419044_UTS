package id.ac.ubaya.informatika.a160419044_advnmp_uts.view

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import id.ac.ubaya.informatika.a160419044_advnmp_uts.R
import id.ac.ubaya.informatika.a160419044_advnmp_uts.databinding.FragmentUserDetailBinding
import id.ac.ubaya.informatika.a160419044_advnmp_uts.model.User
import id.ac.ubaya.informatika.a160419044_advnmp_uts.util.loadImage
import id.ac.ubaya.informatika.a160419044_advnmp_uts.viewmodel.DetailViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_user_detail.*
import kotlinx.android.synthetic.main.user_list_item.view.*
import java.util.concurrent.TimeUnit


class UserDetailFragment : Fragment(), ButtonNotificationClickListener, ButtonBackClickListener {

    private lateinit var viewModel:DetailViewModel
    private lateinit var dataBinding:FragmentUserDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate<FragmentUserDetailBinding>(inflater, R.layout.fragment_user_detail, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            var userId = UserDetailFragmentArgs.fromBundle(requireArguments()).userId
            viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
            viewModel.refresh(userId)

            dataBinding.notifListener = this
            dataBinding.backListener = this

            observeViewModel()
        }
    }

    fun observeViewModel() {
        viewModel.userLD.observe(viewLifecycleOwner, Observer {
            dataBinding.user = it
        })
    }

    override fun onButtonBackClick(v: View) {
        val action = UserDetailFragmentDirections.actionUserList()
        Navigation.findNavController(v).navigate(action)
    }

    override fun onButtonNotificationClick(v: View) {
        Observable.timer(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    MainActivity.showNotification(dataBinding.user?.name.toString(),
                            "A new notification created",
                            R.drawable.ic_baseline_person_24)
                }
    }
}