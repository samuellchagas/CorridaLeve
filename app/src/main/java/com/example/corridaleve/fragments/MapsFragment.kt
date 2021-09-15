package com.example.corridaleve.fragments

import android.content.pm.PackageManager
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.corridaleve.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsFragment : Fragment() {

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    lateinit var latitude:String
    lateinit var longitude: String

    private val callback = OnMapReadyCallback { googleMap ->

        val localUser = LatLng(latitude.toDouble(),longitude.toDouble())
        googleMap.addMarker(MarkerOptions().position(localUser).title("Você está aqui!"))
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(localUser,12f))

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?

        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(requireContext())

         locationUser(mapFragment)
    }

    private fun locationUser(mapFragment: SupportMapFragment?) {

        var task = fusedLocationProviderClient.lastLocation
        task.addOnSuccessListener {
            if(it!=null){
                Toast.makeText(requireContext(),"${it.latitude} ${it.longitude}",Toast.LENGTH_LONG).show()
                latitude = it.latitude.toString()
                longitude = it.longitude.toString()

                mapFragment?.getMapAsync(callback)
            }
        }
    }
}