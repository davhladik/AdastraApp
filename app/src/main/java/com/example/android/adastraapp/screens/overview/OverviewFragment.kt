package com.example.android.adastraapp.screens.overview

import android.graphics.drawable.ClipDrawable.HORIZONTAL
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.android.adastraapp.R
import com.example.android.adastraapp.database.RocketDatabase
import com.example.android.adastraapp.databinding.OverviewFragmentBinding

class OverviewFragment : Fragment() {

    private lateinit var viewModelFactory: OverviewViewModelFactory

    private lateinit var viewModel: OverviewViewModel

    private lateinit var binding: OverviewFragmentBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val application = requireNotNull(this.activity).application
        val dataSource = RocketDatabase.getInstance(application).rocketDatabaseDao
        viewModelFactory = OverviewViewModelFactory(dataSource, application)

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.overview_fragment,
            container,
            false
        )
        viewModel =
            ViewModelProviders.of(
                this,viewModelFactory).get(OverviewViewModel::class.java)

        binding.viewModel=viewModel
        binding.setLifecycleOwner(this)


        /**
         * Navigation
         */

        viewModel.navigateToItemDetail.observe(this, Observer { item ->
            item?.let {
                val action = OverviewFragmentDirections.actionOverviewFragmentToDetailFragment(item)
                NavHostFragment.findNavController(this).navigate(action)
                viewModel.doneNavigatingToItemDetail()
            }
        })


        /**
         * OBserver
         */

        viewModel.flag.observe(this, Observer { show ->
            if (show){
                viewModel.initializeThis()
            }
        })

        /**
         * RecyclerView Adapter
         */

        val adapter = SpaceAdapter(SpaceListener {
//            boosterId -> Toast.makeText(application,"ID BOOSTERU: $boosterId", Toast.LENGTH_SHORT).show()
                boosterId -> viewModel.onItemClicked(boosterId)
        })
        val itemDecor = DividerItemDecoration(application,HORIZONTAL)


        binding.recyclerView.adapter=adapter
        binding.recyclerView.addItemDecoration(itemDecor)

        return binding.root
    }





}