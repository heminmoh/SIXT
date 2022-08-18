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
import com.coding.sixt.R
import com.coding.sixt.adpater.CarsContentAdapter
import com.coding.sixt.databinding.FragmentCarsBinding
import com.coding.sixt.model.CarPreview
import com.coding.sixt.viewmodel.CarViewModel


class CarsFragment : Fragment() {
    private var _viewBinding: FragmentCarsBinding? = null
    private lateinit var carsRecyclerView: RecyclerView
    private lateinit var viewmodel: CarViewModel
    private var adapter: CarsContentAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

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
        val viewModel =  ViewModelProvider(this)[CarViewModel::class.java]
        this.context?.let { it ->
            viewModel.getListObservable(it).observe(viewLifecycleOwner) {
                if (it != null) {
                    makeViewDesign(it)
                } else {
                    Toast.makeText(this.context," R.string.NoDataFetched", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun makeViewDesign(hitsList : List<CarPreview>)
    {
        val adapter = CarsContentAdapter(hitsList)
        carsRecyclerView.adapter = adapter

    }

}