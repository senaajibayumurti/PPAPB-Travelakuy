package com.example.ppapb_travelakuy.adminmenu

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.ppapb_travelakuy.R
import com.example.ppapb_travelakuy.databinding.AdminmenuActivityMainBinding
import com.example.ppapb_travelakuy.db.TravelDao
import com.example.ppapb_travelakuy.db.TravelDb
import com.example.ppapb_travelakuy.db.model.TravelRoom
import com.example.ppapb_travelakuy.listener.NetworkReceiver
import com.example.ppapb_travelakuy.listener.StationCrudListener
import com.example.ppapb_travelakuy.usermenu.ItemTravelForHomeAdapter
import com.example.ppapb_travelakuy.usermenu.ItemTravelForHomeRoomAdapter
import com.example.ppapb_travelakuy.usersignin.MainActivity
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {

    private lateinit var binding: AdminmenuActivityMainBinding
    private val networkReceiver = NetworkReceiver()

    private val receiverIntentFilter = IntentFilter("networkStatus")
    private var hasDisconnected = false;
    private val db: TravelDb by lazy {
        TravelDb.getDatabase(this)!!
    }

    private val dao: TravelDao by lazy {
        db.travelDao()
    }

    private val executorService : ExecutorService = Executors.newSingleThreadExecutor()

    private val networkStatusReceiver = object : BroadcastReceiver() {
        @SuppressLint("ResourceAsColor")
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent != null) {
                val isConnected = intent.getBooleanExtra("isConnected", false)
                val crud = MainActivity.get() as StationCrudListener

                crud.getTravel()
                if (isConnected) {
                    binding.tvConnections.text = "Terhubung ke internet"
                    binding.tvConnections.setBackgroundResource(R.color.success)

                    with(binding) {

                        rvTravelItem.visibility = View.VISIBLE
                        rvRoom.visibility = View.GONE
                        btnAddRoom.visibility = View.GONE
                        btnAdd.visibility = View.VISIBLE
                        if(hasDisconnected) {
                            dao.getAllTravel().observe(this@MainActivity) {
                                val alreadyAdded = mutableListOf<TravelRoom>()
                                for(item in it) {
                                    val temp = item.copy()
                                    temp.id = 0
                                    if(!alreadyAdded.contains(temp)){
                                        crud.addTravel(
                                            item.stasiunOne,
                                            item.stasiunTwo,
                                            item.harga
                                        )
                                        alreadyAdded.add(temp)
                                    }
                                }
                            }
                            executorService.execute {
                                dao.deleteAllTravel()
                            }

                            hasDisconnected = false
                        }

                        crud.getAllTravel().observe(this@MainActivity) {
                            rvTravelItem.apply {
                                adapter = ItemTravelForHomeAdapter(it, isAdmin = true, onClick = {
                                    crud.deleteTravel(it.id)
                                    Toast.makeText(this@MainActivity, "Berhasil dihapus", Toast.LENGTH_SHORT).show()
                                })
                                layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this@MainActivity)
                            }
                        }
                        btnAdd.setOnClickListener {
                            executorService.execute {
                                crud.addTravel(
                                    etStationOne.text.toString(),
                                    etStationTwo.text.toString(),
                                    etPrice.text.toString()
                                )
                            }
                        }
                    }
                } else {
                    binding.tvConnections.text = "Tidak dapat terhubung ke internet"
                    binding.tvConnections.setBackgroundResource(R.color.danger)
                    with(binding) {
                        hasDisconnected = true
                        rvTravelItem.visibility = View.GONE
                        rvRoom.visibility = View.VISIBLE
                        btnAdd.visibility = View.GONE
                        btnAddRoom.visibility = View.VISIBLE
                        if(hasDisconnected) {
                            dao.getAllTravel().observe(this@MainActivity) {
                                rvRoom.apply {
                                    adapter = ItemTravelForHomeRoomAdapter(it, onClick = {
                                        dao.deleteTravel(it)
                                        Toast.makeText(this@MainActivity, "Berhasil dihapus", Toast.LENGTH_SHORT).show()
                                    })
                                    layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this@MainActivity)
                                }
                            }
                        }

                        btnAddRoom.setOnClickListener {
                            executorService.execute {
                                dao.insertTravel(
                                    TravelRoom(
                                        stasiunOne = etStationOne.text.toString(),
                                        stasiunTwo = etStationTwo.text.toString(),
                                        harga = etPrice.text.toString(),
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AdminmenuActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.apply {
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            statusBarColor = Color.TRANSPARENT
        }

        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(networkReceiver, filter)

        LocalBroadcastManager.getInstance(this)
            .registerReceiver(networkStatusReceiver, receiverIntentFilter)

        val crud = MainActivity.get() as StationCrudListener
        crud.getTravel()


        with(binding) {

            button2.setOnClickListener {
                MainActivity.get().logout()
                finish()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        unregisterReceiver(networkReceiver)
        LocalBroadcastManager.getInstance(this).unregisterReceiver(networkStatusReceiver)
    }
}