package com.coding.sixt.fragments

import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.coding.sixt.R
import com.coding.sixt.databinding.FragmentMapBinding
import com.coding.sixt.model.CarPreview
import com.coding.sixt.utilitiy.SIXTProgressDialog
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MapFragment : Fragment() {
    private val SYDNEY = LatLng(48.042802, 11.510077)
    private var _viewBinding: FragmentMapBinding? = null
    private lateinit var progressDialog : SIXTProgressDialog
    private lateinit var mMap : GoogleMap
    private var mapReady = false
     var hitObject : CarPreview? = null
    var transmission = mapOf("M" to "Manual", "A" to "Automatic")
    var fueltype = mapOf("P" to "Gasoline", "E" to "Ethanol", "D" to "Diesel")
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
        _viewBinding!!.companyTextView.text = "make"
        _viewBinding!!.ModelTextView.text = "name"
        _viewBinding!!.licensePlate.text = "license Plate"
        _viewBinding!!.colorTextView.text = "Color"
        _viewBinding!!.fuelTypeTextView.text = "Fuel Type"
        _viewBinding!!.innerCleanlinessTextView.text = "InnerCleanliness"
        _viewBinding!!.transmissionTextView.text = "transmission"
        _viewBinding!!.fuelLeveltextView.text = "fuel Level"
        _viewBinding!!.model.text = hitObject?.name
        _viewBinding!!.name.text = hitObject?.make
        _viewBinding!!.licensePlateView.text = hitObject?.licensePlate
        _viewBinding!!.colorView.text = hitObject?.color
        _viewBinding!!.fueltypeView.text = fueltype[hitObject?.fuelType].toString()
        _viewBinding!!.innerCleanlinessView.text = hitObject?.innerCleanliness
        _viewBinding!!.transmissionView.text = transmission[hitObject?.transmission].toString()
        _viewBinding!!.fuelLevelView.text = hitObject?.fuelLevel

//        https://maps.googleapis.com/maps/api/geocode/json?latlng=40.714224,-73.961452&key=YOUR_API_KEY
    }








}