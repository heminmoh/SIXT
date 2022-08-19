package com.coding.sixt.fragments

import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.coding.sixt.R
import com.coding.sixt.databinding.FragmentCarsBinding
import com.coding.sixt.databinding.FragmentMapBinding
import com.coding.sixt.model.CarPreview
import com.coding.sixt.utilitiy.SIXTProgressDialog
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions


class MapFragment : Fragment() , OnMapReadyCallback{
    private val SYDNEY = LatLng(48.042802, 11.510077)
    private var _viewBinding: FragmentMapBinding? = null
    private lateinit var progressDialog : SIXTProgressDialog
    private lateinit var mMap : GoogleMap
    private var mapReady = false
     var hitObject : CarPreview? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _viewBinding =  FragmentMapBinding.inflate(inflater, container, false)
        return _viewBinding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressDialog = SIXTProgressDialog()
        this.context?.let { progressDialog.show(it,"Please Wait...") }
        hitObject = this.arguments?.getParcelable("object")
        val mapFragment = childFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync {
                googleMap -> mMap = googleMap
            mapReady = true
            val CarPoint = hitObject?.let { LatLng(it.latitude, hitObject!!.longitude) }
            val marker = hitObject?.let { LatLng(it.latitude, hitObject!!.longitude) }
            marker?.let { MarkerOptions().position(it).title(hitObject?.licensePlate).icon(
                BitmapDescriptorFactory.fromResource(R.drawable.car)) }
                ?.let { mMap.addMarker(it) }
//            CarPoint?.let { CameraUpdateFactory.newLatLng(it) }?.let { mMap.moveCamera(it) }
            CarPoint?.let { CameraUpdateFactory.newLatLngZoom(it, 14f) }
                ?.let { mMap.moveCamera(it) }
            progressDialog.dialog.dismiss()
        }
        _viewBinding!!.CarModel.text = hitObject?.modelName

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
        map.moveCamera(CameraUpdateFactory.newLatLng(SYDNEY))

    }








}