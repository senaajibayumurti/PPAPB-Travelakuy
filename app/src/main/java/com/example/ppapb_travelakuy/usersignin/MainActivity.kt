package com.example.ppapb_travelakuy.usersignin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.viewpager2.widget.ViewPager2
import com.example.ppapb_travelakuy.listener.AuthListener
import com.example.ppapb_travelakuy.databinding.UsersigninActivityMainBinding
import com.example.ppapb_travelakuy.db.HistoryDB
import com.example.ppapb_travelakuy.db.HistoryDao
import com.example.ppapb_travelakuy.db.model.Akun
import com.example.ppapb_travelakuy.db.model.Travel
import com.example.ppapb_travelakuy.db.model.TravelForHistory
import com.example.ppapb_travelakuy.db.model.TravelForHome
import com.example.ppapb_travelakuy.listener.FragmentListener
import com.example.ppapb_travelakuy.listener.HistoryCrudListener
import com.example.ppapb_travelakuy.listener.StationCrudListener
import com.example.ppapb_travelakuy.sharedpreferences.Preferences
import com.example.ppapb_travelakuy.usermenu.MainActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity(), AuthListener, FragmentListener,
StationCrudListener, HistoryCrudListener {

    private lateinit var binding: UsersigninActivityMainBinding
    private lateinit var firebase: FirebaseFirestore
    private lateinit var accountCollection: CollectionReference
    private lateinit var tetsudouCollection: CollectionReference
    private lateinit var sharedPreferences: Preferences
    private val stations: MutableLiveData<List<TravelForHome>> by lazy {
        MutableLiveData<List<TravelForHome>>()
    }

    private lateinit var executorService: ExecutorService
    private lateinit var historyDB: HistoryDB
    private lateinit var historyDao: HistoryDao
    lateinit var viewPager2:ViewPager2

    companion object {
        private var instance: com.example.ppapb_travelakuy.usersignin.MainActivity? = null
        fun get(): com.example.ppapb_travelakuy.usersignin.MainActivity {
            return instance!!
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = UsersigninActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        firebase = FirebaseFirestore.getInstance()
        accountCollection = firebase.collection("account")
        tetsudouCollection = firebase.collection("station")
        sharedPreferences = Preferences.getInstance(this)

        historyDB = HistoryDB.getDatabase(this)!!
        historyDao = historyDB.historyDao()
        executorService = Executors.newSingleThreadExecutor()


        title = "Travelakuy"


        with(binding){
            viewPager.adapter = TabAdapter(this@MainActivity)
            viewPager2 = viewPager

            TabLayoutMediator(tabLayout, viewPager){
                    tab, position ->
                tab.text = when(position){
                    0 -> "Register"
                    1 -> "Log In"
                    else -> ""
                }
            }.attach()
        }

        instance = this


        if(sharedPreferences.isLoggedIn()){
            if(sharedPreferences.isAdmin()){
                startActivity(Intent(this, com.example.ppapb_travelakuy.adminmenu.MainActivity::class.java))
            } else {
                startActivity(Intent(this, MainActivity::class.java))
            }

        }

    }
    override fun toPage(n: Int){
        viewPager2.setCurrentItem(n, true)
    }

    override fun setAccount(username: String, password: String) {
        accountCollection.whereEqualTo("username", username)
            .limit(1).get()
            .addOnSuccessListener {
                if (it.isEmpty) {
                    Toast.makeText(this, "Account Not Found", Toast.LENGTH_SHORT).show()
                } else {
                    val data = it.documents[0].data

                    sharedPreferences.setId(data?.get("id").toString())
                    sharedPreferences.saveUsername(data?.get("username").toString())
                    sharedPreferences.saveEmail(data?.get("email").toString())
                    sharedPreferences.savePassword(data?.get("password").toString())

                    if(data?.get("admin") == true){
                        sharedPreferences.setAdmin()
                    } else {
                        sharedPreferences.setNotAdmin()
                    }
                    LoginAccount(username, password)
                }
            }
            .addOnFailureListener {
                Toast.makeText(this, "Account Not Found", Toast.LENGTH_SHORT).show()
            }
    }
    override fun RegisterAccount(username: String, email: String, password: String) {
        val account = Akun(
            username = username,
            email = email,
            password = password
        )
        accountCollection.add(account)
            .addOnSuccessListener {
                account.id = it.id
                it.set(account)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Register Success", Toast.LENGTH_SHORT).show()
                        viewPager2.setCurrentItem(1, true)
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Register Failed $it", Toast.LENGTH_SHORT).show()
                    }
            }
            .addOnFailureListener {
                Toast.makeText(this, "Register Failed $it", Toast.LENGTH_SHORT).show()
            }
    }

    override fun LoginAccount(username: String, password: String) {
        if(username == sharedPreferences.getUsername() && password == sharedPreferences.getPassword()){
            if(sharedPreferences.isAdmin()){
                sharedPreferences.setLoggedIn(true, isAdmin = true)
                val intent = Intent(this, com.example.ppapb_travelakuy.adminmenu.MainActivity::class.java)
                startActivity(intent)
            } else {
                sharedPreferences.setLoggedIn(true, isAdmin = false)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        } else {
            Toast.makeText(this, "Wrong Username or Password", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getID(): String {
        return sharedPreferences.getId()
    }

    override fun logout() {
        sharedPreferences.clear()
    }
    override fun addTravel(stationOne: String, stationTwo: String, price: String) {
        val travel = TravelForHome(
            station_one = stationOne,
            station_two = stationTwo,
            price = price.toInt()
        )
        tetsudouCollection.add(travel)
            .addOnSuccessListener {
                travel.id = it.id
                it.set(travel)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Add Success", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Add Failed", Toast.LENGTH_SHORT).show()
                    }
            }
            .addOnFailureListener {
                Toast.makeText(this, "Add Failed", Toast.LENGTH_SHORT).show()
            }
    }

    override fun updateTravel(
        id: String,
        stationOne: String,
        stationTwo: String,
        price: String
    ) {
        val travel = TravelForHome(
            station_one = stationOne,
            station_two = stationTwo,
            price = price.toInt()
        )
        tetsudouCollection.document(id).set(travel)
            .addOnSuccessListener {
                Toast.makeText(this, "Update Success", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Update Failed", Toast.LENGTH_SHORT).show()
            }
    }

    override fun deleteTravel(id: String) {
        tetsudouCollection.document(id).delete()
            .addOnSuccessListener {
                Toast.makeText(this, "Delete Success", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Delete Failed", Toast.LENGTH_SHORT).show()
            }
    }

    override fun getTravel(){
        tetsudouCollection.addSnapshotListener { value, error ->
            if(error != null) {
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }
            val travel = value?.toObjects(TravelForHome::class.java)
            if(travel != null) {
                stations.postValue(travel)
            }
        }
    }
    override fun getAllTravel(): LiveData<List<TravelForHome>> {
        return stations
    }

    override fun deleteHistory(travelForHistory: TravelForHistory) {
        executorService.execute {
            historyDao.deleteHistory(travelForHistory)
        }
    }

    override fun insertHistory(travelForHistory: TravelForHistory) {
        executorService.execute {
            historyDao.insertHistory(travelForHistory)
        }
    }

    override fun getAllHistory(id: String): LiveData<List<TravelForHistory>> {
        return historyDao.getAllHistory(id)
    }


}