package com.coding.sixt.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.coding.sixt.R
import com.coding.sixt.databinding.FragmentMapBinding
import com.coding.sixt.model.CarPreview
import com.coding.sixt.utilitiy.Mapping
import com.coding.sixt.utilitiy.SIXTProgressDialog
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MapFragment : Fragment() {
    private var _viewBinding: FragmentMapBinding? = null
    private lateinit var progressDialog : SIXTProgressDialog
    private lateinit var mMap : GoogleMap
    private lateinit var navController : NavController
    private var mapReady = false
     private var hitObject : CarPreview? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        container?.removeAllViews()
        _viewBinding =  FragmentMapBinding.inflate(inflater, container, false)
        return _viewBinding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressDialog = SIXTProgressDialog()
        this.context?.let { progressDialog.show(it,"Please Wait...") }
        navController = Navigation.findNavController(view)
        hitObject = this.arguments?.getParcelable("object")

        val mapFragment = _viewBinding?.mapFragment?.let { childFragmentManager.findFragmentById(it.id) } as SupportMapFragment
            this.context?.let {
                mapFragment.getMapAsync {
                        googleMap -> mMap = googleMap
                    mapReady = true
                    val marker = hitObject?.let { LatLng(hitObject!!.latitude, hitObject!!.longitude) }
                    marker?.let {
                        MarkerOptions().position(it).title(hitObject?.licensePlate).icon(
                            BitmapDescriptorFactory.fromResource(R.drawable.caronmap))
                    }?.let { mMap.addMarker(it) }
                    marker?.let { CameraUpdateFactory.newLatLngZoom(it,14f) }
                        ?.let { mMap.moveCamera(it) }
                }
                _viewBinding!!.FirstConstraintData.visibility=View.VISIBLE
                _viewBinding!!.secondConstraint.visibility=View.VISIBLE
                _viewBinding!!.thirdConstraint.visibility=View.VISIBLE
                _viewBinding!!.FirstViewLine.visibility = View.VISIBLE
                _viewBinding!!.SecondViewLine.visibility = View.VISIBLE
                _viewBinding!!.companyTextView.text = getString(R.string.make)
                _viewBinding!!.ModelTextView.text = getString(R.string.name)
                _viewBinding!!.licensePlate.text = getString(R.string.licensePlate)
                _viewBinding!!.colorTextView.text = getString(R.string.Color)
                _viewBinding!!.fuelTypeTextView.text = getString(R.string.fuelType)
                _viewBinding!!.innerCleanlinessTextView.text = getString(R.string.InnerCleanliness)
                _viewBinding!!.transmissionTextView.text = getString(R.string.transmission)
                _viewBinding!!.fuelLevelTextView.text = getString(R.string.fuelLevel)
                _viewBinding!!.model.text = hitObject?.name
                _viewBinding!!.name.text = hitObject?.make
                _viewBinding!!.licensePlateView.text = hitObject?.licensePlate
                _viewBinding!!.colorView.text = hitObject?.color
                _viewBinding!!.fuelTypeView.text = Mapping().fuelTypeMapping(hitObject?.fuelType.toString())
                _viewBinding!!.innerCleanlinessView.text = hitObject?.innerCleanliness
                _viewBinding!!.transmissionView.text = Mapping().transmissionMapping(hitObject?.transmission.toString())
                _viewBinding!!.fuelLevelView.text = hitObject?.fuelLevel

            }
            progressDialog.dialog.dismiss()

        requireActivity().onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    val bundle = Bundle()
                    navController.navigate(R.id.action_mapFragment_to_carsFragment,bundle)
                }
            })
        }
}


//        https://maps.googleapis.com/maps/api/geocode/json?latlng=40.714224,-73.961452&key=YOUR_API_KEY
