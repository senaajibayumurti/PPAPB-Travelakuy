package com.example.ppapb_travelakuy.usermenu

import android.app.DatePickerDialog
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Toolbar
import androidx.core.app.NotificationCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.ppapb_travelakuy.R
import com.example.ppapb_travelakuy.databinding.UsermenuOrderDetailFragmentBinding
import com.example.ppapb_travelakuy.db.model.TravelForHistory
import com.example.ppapb_travelakuy.listener.HistoryCrudListener
import com.example.ppapb_travelakuy.notification.NotificationReceiver
import com.example.ppapb_travelakuy.usersignin.MainActivity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.text.NumberFormat
import java.util.*

class OrderDetailFragment : Fragment(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    private lateinit var binding: UsermenuOrderDetailFragmentBinding
    private var strD: String = ""
    private var strT: String = ""
    private var random: Int = 0

    private fun formatToRupiah(number: Int): String {
        val localeID = Locale("in", "ID")
        val numberFormat = NumberFormat.getCurrencyInstance(localeID)
        return numberFormat.format(number.toLong()).toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = UsermenuOrderDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val crud = MainActivity.get() as HistoryCrudListener

        with(binding) {
            toolbar.setNavigationIcon(R.drawable.ic_back)
            toolbar.setNavigationOnClickListener {
                findNavController().navigateUp()
            }
            (binding.toolbar as? Toolbar)?.apply {
                setNavigationIcon(R.drawable.ic_back)
                setNavigationOnClickListener {
                    findNavController().navigateUp()
                }
            }

            val inf = arguments

            tvOrigin.text = inf?.getString("stationOne")
            tvDestination.text = inf?.getString("stationTwo")

            val spinnerItems = arrayOf("Pilih Kelas", "Ekonomi", "Bisnis", "Eksekutif")
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinnerItems)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            etClass.adapter = adapter

            etClass.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                    val item = parent.getItemAtPosition(position).toString()
                    when (item) {
                        "Ekonomi" -> {
                            random = (50000..100000).random() + (inf?.getInt("price") ?: 0)
                            tvTotalPrice.text = formatToRupiah(random)
                        }
                        "Bisnis" -> {
                            random = (100000..200000).random()  + (inf?.getInt("price") ?: 0)
                            tvTotalPrice.text = formatToRupiah(random)
                        }
                        "Eksekutif" -> {
                            random = (200000..300000).random()  + (inf?.getInt("price") ?: 0)
                            tvTotalPrice.text = formatToRupiah(random)
                        } else -> {
                        random = (inf?.getInt("price") ?: 0)
                        tvTotalPrice.text = formatToRupiah(random)
                    }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // Another interface callback
                }
            }

            etDatetime.setOnClickListener {
                showDatePicker()
            }

            btnOrder.setOnClickListener {
                crud.insertHistory(TravelForHistory(
                    userID = MainActivity.get().getID(),
                    station_one = inf?.getString("stationOne") ?: "",
                    station_two = inf?.getString("stationTwo") ?: "",
                    schedule_date = strD,
                    schedule_time = strT,
                    price = random
                ))
                notifyTicket()
                findNavController().navigate(R.id.action_orderDetailFragment_to_historyFragment)
            }
        }
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(requireContext(), this, year, month, day)
        datePickerDialog.datePicker.minDate = System.currentTimeMillis() - 1000
        datePickerDialog.show()
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val selectedDate = Calendar.getInstance()
        selectedDate.set(Calendar.YEAR, year)
        selectedDate.set(Calendar.MONTH, month)
        selectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)

        updateSelectedDate(selectedDate.time)
    }

    private fun updateSelectedDate(date: Date) {
        val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
        val formattedDate = dateFormat.format(date)
        strD = formattedDate
        showTimePicker()
    }

    private fun showTimePicker() {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(
            requireContext(),
            this,
            hour,
            minute,
            true
        )
        timePickerDialog.show()
    }

    override fun onTimeSet(view: android.widget.TimePicker?, hourOfDay: Int, minute: Int) {
        val selectedTime = Calendar.getInstance()
        selectedTime.set(Calendar.HOUR_OF_DAY, hourOfDay)
        selectedTime.set(Calendar.MINUTE, minute)
        updateSelectedTime(selectedTime.time)
    }

    private fun updateSelectedTime(time: Date) {
        val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        val formattedTime = timeFormat.format(time)
        strT = formattedTime
        binding.etDatetime.setText("$strD $strT")
    }

    private fun notifyTicket() {
        val NOTIFICATION_ID = 1

        val flag = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        } else {
            0
        }

        val intent = Intent(requireContext(), NotificationReceiver::class.java).putExtra("message", "Ticket Added")
        val pendingIntent = PendingIntent.getBroadcast(requireContext(), 0, intent, flag)
        val builder = NotificationCompat.Builder(requireContext(), "1")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Ticket Added")
            .setContentText("Anda telah menambahkan tiket, silahkan cek di halaman tiket")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel("1", "1", NotificationManager.IMPORTANCE_DEFAULT)
            channel.description = "1"
            val notificationManager =
                requireContext().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

        val notificationManager =
            requireContext().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(NOTIFICATION_ID, builder.build())
    }
}