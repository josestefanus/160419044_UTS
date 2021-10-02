package id.ac.ubaya.informatika.a160419044_advnmp_uts.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import id.ac.ubaya.informatika.a160419044_advnmp_uts.R
import kotlinx.android.synthetic.main.fragment_info.*


class InfoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnOption.setOnClickListener {
            val action = InfoFragmentDirections.actionOptionFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }
}