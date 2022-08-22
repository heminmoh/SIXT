package com.coding.sixt.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.coding.sixt.R
import com.coding.sixt.databinding.FragmentMapListBinding
import com.coding.sixt.model.CarPreview
import com.coding.sixt.utilitiy.Mapping
import com.coding.sixt.utilitiy.SIXTProgressDialog
import com.coding.sixt.viewmodel.CarViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MapListFragment : Fragment() {

    private lateinit var progressDialog : SIXTProgressDialog
    private var binding: FragmentMapListBinding? = null
    private lateinit var mMap : GoogleMap
    private var mapReady = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =  FragmentMapListBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    @SuppressLint("PotentialBehaviorOverride", "UseCompatLoadingForDrawables", "SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressDialog = SIXTProgressDialog()
        this.context?.let { progressDialog.show(it) }
        val mapFragment = binding?.mapFragment?.let {
            childFragmentManager.findFragmentById(it.id) } as SupportMapFragment
        val viewModel =  ViewModelProvider(this)[CarViewModel::class.java]
        this.context?.let { it ->
            viewModel.getListObservable(it).observe(viewLifecycleOwner) {
                if (it != null) {
                    val latLngBase = LatLng(it[0].latitude,it[0].longitude)
                    mapFragment.getMapAsync {
                            googleMap -> mMap = googleMap
                        for (i in it.indices) {
                            val latLng = LatLng(it[i].latitude,it[i].longitude)
                            mMap.addMarker(
                                MarkerOptions().position(latLng).title(it[i].licensePlate).icon(
                                    BitmapDescriptorFactory.fromResource(R.drawable.caronmap)) )
                        }
                        mapReady = true
                        mMap.animateCamera(CameraUpdateFactory.zoomTo(18.0f))
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLngBase,12f))

                        mMap.setOnMarkerClickListener { marker ->
                            val carInfo: CarPreview = it.last { it ->
                                it.licensePlate == marker.title }
                            binding!!.FirstConstraintData.visibility   = View.VISIBLE
                            binding!!.secondConstraint.visibility      = View.VISIBLE
                            binding!!.thirdConstraint.visibility       = View.VISIBLE
                            binding!!.FirstViewLine.visibility         = View.VISIBLE
                            binding!!.SecondViewLine.visibility        = View.VISIBLE
                            binding!!.forthConstraint.visibility       = View.VISIBLE

                            binding!!.Attribute.text                = getString(R.string.Attributes)
                            binding!!.companyTextView.text          = getString(R.string.make)
                            binding!!.ModelTextView.text            = getString(R.string.name)
                            binding!!.licensePlate.text             = getString(R.string.licensePlate)
                            binding!!.colorTextView.text            = getString(R.string.Color)
                            binding!!.fuelTypeTextView.text         = getString(R.string.fuelType)
                            binding!!.innerCleanlinessTextView.text = getString(R.string.InnerCleanliness)
                            binding!!.transmissionTextView.text     = getString(R.string.transmission)
                            binding!!.fuelLevelTextView.text        = getString(R.string.fuelLevel)

                            Glide.with(binding!!.root).load(carInfo.carImageUrl).
                            error(context?.getDrawable(R.drawable.caronmap))
                                .into(binding!!.CarItemImageView)

                            binding!!.model.text                    = carInfo.name
                            binding!!.name.text                     = carInfo.make
                            binding!!.licensePlateView.text         = carInfo.licensePlate
                            binding!!.colorView.text                = carInfo.color
                            binding!!.fuelTypeView.text             = Mapping().fuelTypeMapping(carInfo.fuelType)
                            binding!!.innerCleanlinessView.text     = carInfo.innerCleanliness
                            binding!!.transmissionView.text         = Mapping().transmissionMapping(carInfo.transmission)
                            binding!!.fuelLevelView.text            = carInfo.fuelLevel
                            false
                        }
                        progressDialog.dialog.dismiss()
                    }
                } else {
                    Toast.makeText(this.context,"NoDataFetched", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}