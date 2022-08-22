package com.coding.sixt.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.coding.sixt.R
import com.coding.sixt.databinding.FragmentMapBinding
import com.coding.sixt.model.CarPreview
import com.coding.sixt.utilitiy.HelperSIXT
import com.coding.sixt.utilitiy.LiveDataInternetConnections
import com.coding.sixt.utilitiy.Mapping
import com.coding.sixt.utilitiy.SIXTProgressDialog
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import javax.inject.Inject


class MapFragment : Fragment() {
    private var binding: FragmentMapBinding? = null
    private lateinit var progressDialog : SIXTProgressDialog
    private lateinit var mMap : GoogleMap
    private lateinit var navController : NavController
    private var mapReady = false
    private var hitObject : CarPreview? = null
    @Inject
    lateinit var liveDataConnection : LiveDataInternetConnections
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =  FragmentMapBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        liveDataConnection = activity?.let { LiveDataInternetConnections(it.application) }!!
        progressDialog = SIXTProgressDialog()
        this.context?.let { progressDialog.show(it) }
        navController = Navigation.findNavController(view)
        hitObject = this.arguments?.getParcelable(HelperSIXT.CAR_OBJECT_BUNDLE)

        binding?.connected?.visibility   = View.GONE
        binding?.notConnected?.visibility = View.VISIBLE

        liveDataConnection.observe(viewLifecycleOwner) { isConnected ->
            if (isConnected) {
                binding?.connected?.visibility   = View.GONE
                binding?.notConnected?.visibility  = View.GONE
            }else {
                binding?.connected?.visibility   = View.GONE
                binding?.notConnected?.visibility  = View.VISIBLE
            }
        }

        val mapFragment = binding?.mapFragment?.let {
            childFragmentManager.findFragmentById(it.id) } as SupportMapFragment
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

                binding!!.forthConstraint.visibility     = View.VISIBLE
                binding!!.FirstConstraintData.visibility = View.VISIBLE
                binding!!.secondConstraint.visibility    = View.VISIBLE
                binding!!.thirdConstraint.visibility     = View.VISIBLE
                binding!!.FirstViewLine.visibility       = View.VISIBLE
                binding!!.SecondViewLine.visibility      = View.VISIBLE

                binding!!.companyTextView.text           = getString(R.string.make)
                binding!!.ModelTextView.text             = getString(R.string.name)
                binding!!.licensePlateTextView.text      = getString(R.string.licensePlate)
                binding!!.colorTextView.text             = getString(R.string.Color)
                binding!!.fuelTypeTextView.text          = getString(R.string.fuelType)
                binding!!.innerCleanlinessTextView.text  = getString(R.string.InnerCleanliness)
                binding!!.transmissionTextView.text      = getString(R.string.transmission)
                binding!!.fuelLevelTextView.text         = getString(R.string.fuelLevel)

                Glide.with(binding!!.root).load(hitObject?.carImageUrl).
                error(context?.getDrawable(R.drawable.caronmap))
                    .into(binding!!.CarItemImageView)

                binding!!.modelView.text            = hitObject?.name
                binding!!.nameView.text             = hitObject?.make
                binding!!.licensePlateView.text     = hitObject?.licensePlate
                binding!!.colorView.text            = hitObject?.color
                binding!!.fuelTypeView.text         = Mapping().fuelTypeMapping(hitObject?.fuelType.toString())
                binding!!.innerCleanlinessView.text = hitObject?.innerCleanliness
                binding!!.transmissionView.text     = Mapping().transmissionMapping(hitObject?.transmission.toString())
                binding!!.fuelLevelView.text        = hitObject?.fuelLevel
            }
            progressDialog.dialog.dismiss()

        requireActivity().onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true)
            {
                override fun handleOnBackPressed() {
                    val bundle = Bundle()
                    navController.navigate(R.id.action_mapFragment_to_carsFragment,bundle)
                }
            })
        }
}


