package com.example.corridaleve.fragments

import android.app.AlertDialog
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.corridaleve.R
import com.example.corridaleve.databinding.ScreenRunFragmentBinding
import java.text.DecimalFormat
import java.util.*
import java.util.zip.Inflater

class ScreenRunFragment:Fragment(),LocationListener {

    private var _binding: ScreenRunFragmentBinding? = null
    private val binding:ScreenRunFragmentBinding get() = _binding!!

    val time: Long = 1000000000L
    var timer = Timer(time)

    private var locationManager:LocationManager = requireContext().getSystemService(Context.LOCATION_SERVICE) as LocationManager
    private var locationListener = LocationListener{}
    private var listLocation:List<String> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ScreenRunFragmentBinding.inflate(inflater,container,false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        timer.start()

        binding.imageViewStop.setOnClickListener {
            timer.cancel()
            val timing:Long =1000000000-timer.millisUntilFinished
            Toast.makeText(requireContext(),formatTime(timing),Toast.LENGTH_LONG).show()
            //requireActivity().finish()

//            locationManager.requestLocationUpdates(
//                LocationManager.GPS_PROVIDER,
//                5000,
//                5,
//                locationListener
//
//            )

//            locationManager.requestLocationUpdates(
//                LocationManager.GPS_PROVIDER,
//                0,
//                0,
//                LocationListener {  }
//            )
        }
    }

    inner class Timer(miliis:Long) : CountDownTimer(miliis,1){
        var millisUntilFinished:Long = 0
        override fun onFinish() {

        }
        override fun onTick(millisUntilFinished: Long) {
            this.millisUntilFinished = millisUntilFinished
            val passTime = time - millisUntilFinished

            binding.timeText.text = formatTime(passTime)
        }
    }

    fun formatTime (passTime:Long):String{

        val f = DecimalFormat("00")
        val hour = passTime / 3600000 % 24
        val min = passTime / 60000 % 60
        val sec = passTime / 1000 % 60

        return f.format(hour) + ":" + f.format(min) + ":" + f.format(sec)
    }

    override fun onLocationChanged(p0: Location) {
        Toast.makeText(requireContext(),"TESTE LOCALIZAÇÂO",Toast.LENGTH_LONG).show()
    }
}
