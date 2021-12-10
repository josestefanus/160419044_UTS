package id.ac.ubaya.informatika.a160419044_advnmp_uts.view

import android.content.Context
import android.hardware.SensorManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.ac.ubaya.informatika.a160419044_advnmp_uts.R


class CertificateFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_certificate, container, false)
    }
}