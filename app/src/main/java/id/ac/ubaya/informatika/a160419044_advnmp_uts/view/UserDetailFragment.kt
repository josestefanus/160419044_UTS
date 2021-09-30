package id.ac.ubaya.informatika.a160419044_advnmp_uts.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.ac.ubaya.informatika.a160419044_advnmp_uts.R
import id.ac.ubaya.informatika.a160419044_advnmp_uts.viewmodel.DetailViewModel
import id.ac.ubaya.informatika.a160419044_advnmp_uts.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_user_detail.*
import kotlinx.android.synthetic.main.fragment_user_list.*


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

        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.refresh()
        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.userLD.observe(viewLifecycleOwner, Observer {
            userDetailAdapter.updateUserList(it)
        })
    }
}