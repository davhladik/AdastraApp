package com.example.android.adastraapp.screens.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import com.example.android.adastraapp.R
import com.example.android.adastraapp.database.RocketDatabase
import com.example.android.adastraapp.databinding.DetailFragmentBinding

class DetailFragment : Fragment() {

    private lateinit var viewModelFactory: DetailViewModelFactory

    private lateinit var viewModel: DetailViewModel

    private lateinit var binding: DetailFragmentBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val application = requireNotNull(this.activity).application
        val dataSource = RocketDatabase.getInstance(application).rocketDatabaseDao
        val selectedBooster = DetailFragmentArgs.fromBundle(requireArguments()).boosterID
        viewModelFactory = DetailViewModelFactory(dataSource,selectedBooster, application)

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.detail_fragment,
            container,
            false
        )
        viewModel =
            ViewModelProviders.of(
                this,viewModelFactory).get(DetailViewModel::class.java)

        binding.viewModel=viewModel
        binding.setLifecycleOwner(this)


        /**
         * Navigation
         */

        viewModel.navigateToOverview.observe(this, Observer { item ->
            if (item) {
                val action = DetailFragmentDirections.actionDetailFragmentToOverviewFragment()
                NavHostFragment.findNavController(this).navigate(action)
                viewModel.doneArrowBackClicked()
            }
        })


        /**
         * OBserver
         */

//        viewModel.flag.observe(this, Observer { show ->
//            if (show){
//                viewModel.initializeThis()
//            }
//        })

        return binding.root
    }

}