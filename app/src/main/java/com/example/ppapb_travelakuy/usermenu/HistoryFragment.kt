package com.example.ppapb_travelakuy.usermenu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ppapb_travelakuy.databinding.UsermenuHistoryFragmentBinding
import com.example.ppapb_travelakuy.db.model.TravelForHistory
import com.example.ppapb_travelakuy.listener.HistoryCrudListener
import com.example.ppapb_travelakuy.usersignin.MainActivity

class HistoryFragment : Fragment() {
    private lateinit var binding: UsermenuHistoryFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = UsermenuHistoryFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val crud = MainActivity.get() as HistoryCrudListener

        with(binding){
            crud.getAllHistory(MainActivity.get().getID()).observe(viewLifecycleOwner) {
                if(it.isNotEmpty()) {
                    rvListTravel.apply {
                        adapter = ItemTravelForHistoryAdapter(it)
                        layoutManager = LinearLayoutManager(context)
                    }
                }
            }
        }
    }
}