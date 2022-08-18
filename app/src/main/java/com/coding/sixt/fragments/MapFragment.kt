package com.coding.sixt.fragments

import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.coding.sixt.R
import com.coding.sixt.model.CarPreview
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions


class MapFragment : Fragment() , OnMapReadyCallback{
    private val SYDNEY = LatLng(48.042802, 11.510077)


    private var mMap: GoogleMap? = null
     var hitObject : CarPreview? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         hitObject = this.arguments?.getParcelable("object")

        val mapFragment = SupportMapFragment.newInstance()
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap) {
        map.addMarker(
            MarkerOptions()
                .position(LatLng(48.042802, 11.510077))
                .title("San Francisco")
        )
        map.addMarker(
            MarkerOptions()
                .position(LatLng(48.042802, 11.510077))
                .title("Marker")
        )
        map.moveCamera(CameraUpdateFactory.newLatLng(SYDNEY));

    }








}