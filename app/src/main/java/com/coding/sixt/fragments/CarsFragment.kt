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
import com.coding.sixt.adpater.CarsContentAdapter
import com.coding.sixt.databinding.FragmentCarsBinding
import com.coding.sixt.model.CarPreview
import com.coding.sixt.utilitiy.SIXTProgressDialog
import com.coding.sixt.viewmodel.CarViewModel


class CarsFragment : Fragment() {
    private var _viewBinding: FragmentCarsBinding? = null
    private lateinit var carsRecyclerView: RecyclerView
    private lateinit var progressDialog : SIXTProgressDialog


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _viewBinding = FragmentCarsBinding.inflate(inflater, container, false)
        return _viewBinding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        carsRecyclerView = _viewBinding?.CarsRecycler!!
        carsRecyclerView.layoutManager = LinearLayoutManager(this.context)
        initViewModel()
    }

    private fun initViewModel()
    {
        progressDialog = SIXTProgressDialog()
        this.context?.let { progressDialog.show(it,"Please Wait...") }
        val viewModel =  ViewModelProvider(this)[CarViewModel::class.java]
        this.context?.let { it ->
            viewModel.getListObservable(it).observe(viewLifecycleOwner) {
                if (it != null) {
                    makeViewDesign(it)
                    progressDialog.dialog.dismiss()
                } else {
                    Toast.makeText(this.context,"NoDataFetched", Toast.LENGTH_SHORT).show()
                }
                progressDialog.dialog.dismiss()
            }
        }
    }

    private fun makeViewDesign(hitsList : List<CarPreview>)
    {
        val adapter = CarsContentAdapter(hitsList)
        carsRecyclerView.adapter = adapter

    }

}