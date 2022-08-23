package com.coding.sixt.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.coding.sixt.adpater.CarsContentAdapter
import com.coding.sixt.databinding.FragmentCarsBinding
import com.coding.sixt.model.CarPreview
import com.coding.sixt.utilitiy.LiveDataInternetConnections
import com.coding.sixt.utilitiy.SIXTProgressDialog
import com.coding.sixt.viewmodel.CarViewModel
import javax.inject.Inject


class CarsFragment : Fragment() {

    private var binding: FragmentCarsBinding? = null
    private lateinit var carsRecyclerView: RecyclerView
    private lateinit var progressDialog : SIXTProgressDialog
    @Inject
    lateinit var liveDataConnection : LiveDataInternetConnections
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCarsBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        liveDataConnection = activity?.let { LiveDataInternetConnections(it.application) }!!
        carsRecyclerView = binding?.CarsRecycler!!
        carsRecyclerView.layoutManager = LinearLayoutManager(this.context)
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
        initViewModel()


        binding!!.swipeToRefresh.setOnRefreshListener {
            binding!!.swipeToRefresh.isRefreshing = false
            initViewModel()

        }

    }

    private fun initViewModel()
    {
        progressDialog = SIXTProgressDialog()
        this.context?.let { progressDialog.show(it) }
        val viewModel =  ViewModelProvider(this)[CarViewModel::class.java]
        this.context?.let { it ->
            viewModel.getListObservable(it).observe(viewLifecycleOwner) {
                if (it != null) {
                    makeViewDesign(it)
                    progressDialog.dialog.dismiss()
                } else { Toast.makeText(this.context,"NoDataFetched", Toast.LENGTH_SHORT).show()    }
                progressDialog.dialog.dismiss()
            }
        }
    }

     fun makeViewDesign(hitsList : List<CarPreview>)
    {
        val adapter = CarsContentAdapter(hitsList)
        carsRecyclerView.adapter = adapter
    }

}