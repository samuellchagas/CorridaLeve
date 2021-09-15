package com.example.corridaleve.fragments

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
import com.example.corridaleve.databinding.ScreenRunFragmentBinding
import com.example.corridaleve.model.Historic
import com.example.corridaleve.viewmodel.ScreenRunViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.DecimalFormat

class ScreenRunFragment : Fragment() {

    private var _binding: ScreenRunFragmentBinding? = null
    private val binding: ScreenRunFragmentBinding get() = _binding!!
    private val viewModel: ScreenRunViewModel by viewModel()

    private val listCoordinate: MutableList<Location> = mutableListOf()


    val time: Long = 1000000000L
    var timer = Timer(time)


    //private val locationListener:LocationListener()

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

        var locationManager: LocationManager =
            requireContext().getSystemService(Context.LOCATION_SERVICE) as LocationManager

        binding.imageViewStop.setOnClickListener {
            timer.cancel()
            val timing: Long = 1000000000 - timer.millisUntilFinished

            viewModel.saveHistoric(
                Historic(
                    formatDistance(distanceRun(listCoordinate)),
                    formatTime(timing),
                    formatTime(timing)
                )
            )

            //calcPace(distanceRun(listCoordinate),timing)))

            requireActivity().finish()

        }

        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                (requireContext()),
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                101
            )
        }
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

    private fun formatDistance(distance: String): String = String.format("%.2f", distance.toDouble())

    private fun calcPace(distance: String, time: Long): String {
        return ((distance.toFloat() / time) / 60).toString()
    }

    inner class Timer(miliis: Long) : CountDownTimer(miliis, 1) {
        var millisUntilFinished: Long = 0
        override fun onFinish() {

        }

        override fun onTick(millisUntilFinished: Long) {
            this.millisUntilFinished = millisUntilFinished
            val passTime = time - millisUntilFinished

            binding.timeText.text = formatTime(passTime)
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

        var latitude = 0.0
        var longitude = 0.0
        var distanceCalculated = 0.0F;

        list.forEachIndexed { index, location ->
            if (index <= (list.size - 2)) {
                distanceCalculated += location.distanceTo(list[index + 1])
            }
        }

        var distance = latitude.toString() + ", " + longitude.toString()
        return distanceCalculated.toString()


    }
}
