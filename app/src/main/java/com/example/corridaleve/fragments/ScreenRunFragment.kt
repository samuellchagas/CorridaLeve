package com.example.corridaleve.fragments

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.corridaleve.databinding.ScreenRunFragmentBinding
import com.example.corridaleve.model.Historic
import com.example.corridaleve.viewmodel.ScreenRunViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.log

class ScreenRunFragment : Fragment() {

    private var _binding: ScreenRunFragmentBinding? = null
    private val binding: ScreenRunFragmentBinding get() = _binding!!
    private val viewModel: ScreenRunViewModel by viewModel()
    private val listCoordinate: MutableList<Location> = mutableListOf()
    val time: Long = 1000000000L
    var timer = Timer(time)

        override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ScreenRunFragmentBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        timer.start()

        binding.imageViewStop.setOnClickListener {
            timer.cancel()
            viewModel.saveHistoric(
                Historic(
                    formatDate(Calendar.getInstance()),
                    formatDistance(distanceRun(listCoordinate)),
                    formatTime((1000000000 - timer.millisUntilFinished)),
                    formatDistance( calcPace(distanceRun(listCoordinate),timer.millisUntilFinished))
                )
            )
            requireActivity().finish()
        }

        requestPermissionMapAndLocationLister()
    }


    fun formatDate(calendar: Calendar): String =
        SimpleDateFormat("dd/MM/yyyy").format(calendar.time)

    private fun formatDistance(distance: String): String = String.format("%.2f", distance.toDouble())

    private fun calcPace(distance: String, time: Long): String =
        (((((1000000000 - timer.millisUntilFinished)/ 60) / distance.toFloat()) )).toString()


    inner class Timer(miliis: Long) : CountDownTimer(miliis, 1) {
        var millisUntilFinished: Long = 0
        override fun onFinish() {

        }

        override fun onTick(millisUntilFinished: Long) {
            this.millisUntilFinished = millisUntilFinished
            binding.timeText.text = formatTime((time - millisUntilFinished))
        }
    }

    fun formatTime(passTime: Long): String {
        val f = DecimalFormat("00")
        val hour = passTime / 3600000 % 24
        val min = passTime / 60000 % 60
        val sec = passTime / 1000 % 60

        return f.format(hour) + ":" + f.format(min) + ":" + f.format(sec)
    }

    fun distanceRun(list: MutableList<Location>): String {
        var distanceCalculated = 0.0F;

        list.forEachIndexed { index, location ->
            if (index <= (list.size - 2)) {
                distanceCalculated += location.distanceTo(list[index + 1])
            }
        }

        return distanceCalculated.toString()
    }

    private fun requestPermissionMapAndLocationLister() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                (requireContext()),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                101
            )
        }
        val locationManager: LocationManager =
            requireContext().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationManager.requestLocationUpdates(
            LocationManager.GPS_PROVIDER,
            1000,
            0F,
            object : LocationListener {
                override fun onLocationChanged(p0: Location) {
                    listCoordinate.add(p0)
                }
            }
        )
    }
}