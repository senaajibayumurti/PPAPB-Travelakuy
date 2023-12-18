package com.example.ppapb_travelakuy.usermenu

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ppapb_travelakuy.R
import com.example.ppapb_travelakuy.databinding.UsermenuHomeFragmentBinding
import com.example.ppapb_travelakuy.listener.FragmentListener
import com.example.ppapb_travelakuy.listener.MenuListener
import com.example.ppapb_travelakuy.listener.StationCrudListener
import com.example.ppapb_travelakuy.usersignin.MainActivity

class HomeFragment : Fragment(){
    private lateinit var binding: UsermenuHomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = UsermenuHomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val crud = MainActivity.get() as StationCrudListener
        crud.getTravel()

        with(binding){

            crud.getAllTravel().observe(viewLifecycleOwner) {
                rvListTravel.apply {
                    adapter = ItemTravelForHomeAdapter(it, addHistory = {
                        val bundle = Bundle()
                        bundle.putString("stationOne", it.station_one)
                        bundle.putString("stationTwo", it.station_two)
                        bundle.putInt("price", it.price)

                        findNavController().navigate(R.id.action_homeFragment_to_orderDetailFragment, bundle)

                    })
                    layoutManager = LinearLayoutManager(requireContext())
                }
            }
        }
    }


}