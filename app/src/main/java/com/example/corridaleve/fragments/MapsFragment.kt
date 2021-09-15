package com.example.corridaleve.fragments

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
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
    lateinit var latitude: String
    lateinit var longitude: String

    private val callback = OnMapReadyCallback { googleMap ->
        googleMap.addMarker(
            MarkerOptions()
                .position(LatLng(latitude.toDouble(), longitude.toDouble()))
                .title(getString(R.string.title_map))
        )
        googleMap
            .animateCamera(
                CameraUpdateFactory
                    .newLatLngZoom(LatLng(latitude.toDouble(), longitude.toDouble()), 15f)
            )
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

        requestPermissionMapsAndLocationUser(mapFragment)
    }

    private fun requestPermissionMapsAndLocationUser(mapFragment: SupportMapFragment?) {
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
        fusedLocationProviderClient.lastLocation.addOnSuccessListener {
            it?.let {
                latitude = it.latitude.toString()
                longitude = it.longitude.toString()
                mapFragment?.getMapAsync(callback)
            }
        }
    }
}
