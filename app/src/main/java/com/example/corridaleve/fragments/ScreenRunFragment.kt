package com.example.corridaleve.fragments

import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.corridaleve.R
import com.example.corridaleve.databinding.ScreenRunFragmentBinding
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.maps.model.LatLng
import java.text.DecimalFormat
import java.util.*
import java.util.zip.Inflater

class ScreenRunFragment:Fragment() {

    private var _binding: ScreenRunFragmentBinding? = null
    private val binding:ScreenRunFragmentBinding get() = _binding!!


    val time: Long = 1000000000L
    var timer = Timer(time)


    //private val locationListener:LocationListener()

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

        var locationManager:LocationManager = requireContext().getSystemService(Context.LOCATION_SERVICE) as LocationManager

        binding.imageViewStop.setOnClickListener {
            timer.cancel()
            val timing: Long = 1000000000 - timer.millisUntilFinished
            Toast.makeText(requireContext(), formatTime(timing), Toast.LENGTH_LONG).show()
            //requireActivity().finish()
        }

        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                (requireContext()),
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ){
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),101)
        }
        locationManager.requestLocationUpdates(
            LocationManager.GPS_PROVIDER,
            5000,
            0F,
            object:LocationListener {
                override fun onLocationChanged(p0: Location) {
                    Toast.makeText(requireContext(), p0.longitude.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        )
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

}

