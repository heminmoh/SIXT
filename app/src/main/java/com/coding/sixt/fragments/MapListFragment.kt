package com.coding.sixt.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.coding.sixt.R
import com.coding.sixt.databinding.FragmentMapListBinding
import com.coding.sixt.model.CarPreview
import com.coding.sixt.viewmodel.CarViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MapListFragment : Fragment() {

    private var _viewBinding: FragmentMapListBinding? = null
    private lateinit var mMap : GoogleMap
    private var mapReady = false
    private var transmission = mapOf("M" to "Manual", "A" to "Automatic")
    private var fuelType = mapOf("P" to "Gasoline", "E" to "Ethanol", "D" to "Diesel")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _viewBinding =  FragmentMapListBinding.inflate(inflater, container, false)
        return _viewBinding!!.root
    }

    @SuppressLint("PotentialBehaviorOverride")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = _viewBinding?.mapFragment?.let { childFragmentManager.findFragmentById(it.id) } as SupportMapFragment
        val viewModel =  ViewModelProvider(this)[CarViewModel::class.java]
        this.context?.let { it ->
            viewModel.getListObservable(it).observe(viewLifecycleOwner) {
                if (it != null) {
                    val latLngBase = LatLng(it[0].latitude,it[0].longitude)
                    mapFragment.getMapAsync {
                            googleMap -> mMap = googleMap
                        for (i in it.indices) {
                            var latLng = LatLng(it[i].latitude,it[i].longitude)
                            // below line is use to add marker to each location of our array list.
                            mMap.addMarker(
                                MarkerOptions().position(latLng).title(it[i].licensePlate).icon(
                                    BitmapDescriptorFactory.fromResource(R.drawable.caronmap)) )
                            mMap.animateCamera(CameraUpdateFactory.zoomTo(18.0f))

                            // below line is use to move our camera to the specific location.
                            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng))
                        }

                        mapReady = true
                        mMap.animateCamera(CameraUpdateFactory.zoomTo(18.0f))
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLngBase,12f))

                        mMap.setOnMarkerClickListener { marker ->
                            val carInfo: CarPreview? = it.last { it.licensePlate == marker.title }
                            _viewBinding!!.Attribute.text = "Attributes"
                            _viewBinding!!.companyTextView.text = getString(R.string.make)
                            _viewBinding!!.ModelTextView.text = getString(R.string.name)
                            _viewBinding!!.licensePlate.text = getString(R.string.licensePlate)
                            _viewBinding!!.colorTextView.text = getString(R.string.Color)
                            _viewBinding!!.fuelTypeTextView.text = getString(R.string.fuelType)
                            _viewBinding!!.innerCleanlinessTextView.text = getString(R.string.InnerCleanliness)
                            _viewBinding!!.transmissionTextView.text = getString(R.string.transmission)
                            _viewBinding!!.fuelLeveltextView.text = getString(R.string.fuelLevel)
                            _viewBinding!!.model.text = carInfo?.name
                            _viewBinding!!.name.text = carInfo?.make
                            _viewBinding!!.licensePlateView.text = carInfo?.licensePlate
                            _viewBinding!!.colorView.text = carInfo?.color
                            _viewBinding!!.fueltypeView.text = fuelType[carInfo?.fuelType].toString()
                            _viewBinding!!.innerCleanlinessView.text = carInfo?.innerCleanliness
                            _viewBinding!!.transmissionView.text = transmission[carInfo?.transmission].toString()
                            _viewBinding!!.fuelLevelView.text = carInfo?.fuelLevel
                            false
                        }
                    }
                } else {
                    Toast.makeText(this.context,"NoDataFetched", Toast.LENGTH_SHORT).show()
                }
            }
        }


//            val marker = hitObject?.let { LatLng(hitObject!!.latitude, hitObject!!.longitude) }
//            marker?.let {
//                MarkerOptions().position(it).title(hitObject?.licensePlate).icon(
//                    BitmapDescriptorFactory.fromResource(R.drawable.car))
//            }?.let { mMap.addMarker(it) }
//            marker?.let { CameraUpdateFactory.newLatLngZoom(it,14f) }
//                ?.let { mMap.moveCamera(it) }

    }


}