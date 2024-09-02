package com.rajan.my3rdyearproject

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.Ringtone
import android.media.RingtoneManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Switch
import android.widget.TimePicker
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import java.util.*

/**
 * Class for setting up the routine fragment for the 3rd year project.
 * @author Rajan Singh Bhamra (2034215)
 * @version 2
 */

class RoutineFragment : Fragment() {

    /**
     * I get the necessary conditions for Firebase Authentication.
     * I get the necessary conditions for Firebase Firestore too.
     * All EditTexts and Buttons and Switches and Charts are called in correctly.
     */

    private lateinit var barChart: BarChart
    private lateinit var pieChart: PieChart

    private lateinit var barChartEvening: BarChart
    private lateinit var pieChartEvening: PieChart

    private lateinit var btnSubmitMorningRoutine: Button
    private lateinit var btnSubmitEveningRoutine: Button

    private lateinit var switchWakeUp: Switch
    private lateinit var switchWashUp: Switch
    private lateinit var switchEatFood: Switch
    private lateinit var switchJournal: Switch
    private lateinit var switchPray: Switch

    private lateinit var switchReflectOnDay: Switch
    private lateinit var switchPrepareTomorrow: Switch
    private lateinit var switchEatHealthyDinner: Switch
    private lateinit var switchUnplug: Switch
    private lateinit var switchRelaxation: Switch

    private var alarmSet = false
    private lateinit var alarmManager: AlarmManager
    private lateinit var pendingIntent: PendingIntent
    private var buttonPressCounts = IntArray(10)


    /**
     * This method sets the layout as fragment_routine xml file
     */

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_routine, container, false)
    }

    /**
     * This method sets the IDs of everything.
     * It sets up the graphs and any buttons.
     * it also sets up an alarm by getting the date and time for it.
     */

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        barChart = view.findViewById(R.id.barChart)
        pieChart = view.findViewById(R.id.pieChart)

        barChartEvening = view.findViewById(R.id.barChartEvening)
        pieChartEvening = view.findViewById(R.id.pieChartEvening)

        btnSubmitMorningRoutine = view.findViewById(R.id.btnSubmitMorningRoutine)
        btnSubmitEveningRoutine = view.findViewById(R.id.btnSubmitEveningRoutine)

        switchWakeUp = view.findViewById(R.id.switchWakeUp)
        switchWashUp = view.findViewById(R.id.switchWashUp)
        switchEatFood = view.findViewById(R.id.switchEatFood)
        switchJournal = view.findViewById(R.id.switchJournal)
        switchPray = view.findViewById(R.id.switchPray)

        switchReflectOnDay = view.findViewById(R.id.switchReflectOnDay)
        switchPrepareTomorrow = view.findViewById(R.id.switchPrepareTomorrow)
        switchEatHealthyDinner = view.findViewById(R.id.switchEatHealthyDinner)
        switchUnplug = view.findViewById(R.id.switchUnplug)
        switchRelaxation = view.findViewById(R.id.switchRelaxation)

        alarmManager = requireActivity().getSystemService(Context.ALARM_SERVICE) as AlarmManager

        btnSubmitMorningRoutine.setOnClickListener {
            updateCharts()
        }

        btnSubmitEveningRoutine.setOnClickListener {
            updateEveningCharts()
        }


        view.findViewById<Button>(R.id.setAlarmButton).setOnClickListener {
            val calendar = Calendar.getInstance()
            calendar.set(Calendar.HOUR_OF_DAY, view.findViewById<TimePicker>(R.id.timePicker).hour)
            calendar.set(Calendar.MINUTE, view.findViewById<TimePicker>(R.id.timePicker).minute)

            setAlarm(calendar.timeInMillis)
        }

        view.findViewById<Button>(R.id.cancelAlarmButton).setOnClickListener {
            cancelAlarm()
        }
    }

    /**
     * This method schedules the alarm for a specific time frame.
     * It waits until the button to set the time is pressed.
     * It then sets the visibility to set an alarm to be gone as soon as it is set.
     * It also displays a message letting the user know the alarm has been set.
     */

    @SuppressLint("ScheduleExactAlarm")
    private fun setAlarm(timeInMillis: Long) {
        val intent = Intent(requireContext(), AlarmReceiver::class.java)
        pendingIntent = PendingIntent.getBroadcast(requireContext(), 0, intent,
            PendingIntent.FLAG_IMMUTABLE)

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, timeInMillis, pendingIntent)

        view?.findViewById<Button>(R.id.setAlarmButton)?.visibility = View.GONE
        view?.findViewById<Button>(R.id.cancelAlarmButton)?.visibility = View.VISIBLE
        alarmSet = true

        Toast.makeText(requireContext(), "Alarm set!", Toast.LENGTH_SHORT).show()
    }

    /**
     * This method cancels the alarm.
     * It waits until the alarm has been played / is playing.
     * It then turns it off when the button associated is pressed.
     * It then displays a message saying the alarm method is cancelled.
     */

    private fun cancelAlarm() {
        alarmManager.cancel(pendingIntent)
        view?.findViewById<Button>(R.id.cancelAlarmButton)?.visibility = View.GONE
        view?.findViewById<Button>(R.id.setAlarmButton)?.visibility = View.VISIBLE
        alarmSet = false
        Toast.makeText(requireContext(), "Alarm canceled!", Toast.LENGTH_SHORT).show()
    }

    /**
     * This class updates all the morning charts.
     * It gets all the required switches (the morning ones).
     * It also sets up the bar chart and pie chart with the correct axis.
     * This class also sets the colours for each of the charts.
     * It updates the charts as each button is submitted whilst the switch is true.
     */

    private fun updateCharts() {
        val switches = listOf(switchWakeUp, switchWashUp, switchEatFood, switchJournal, switchPray)
        val barEntries = mutableListOf<BarEntry>()

        switches.forEachIndexed { index, switch ->
            if (switch.isChecked) {
                buttonPressCounts[index]++
            }
            val currentValue = buttonPressCounts[index].toFloat()
            barEntries.add(BarEntry(index.toFloat(), currentValue))
        }

        val barDataSet = BarDataSet(barEntries, "Morning Routine")
        barDataSet.colors = listOf(Color.RED, Color.BLUE, Color.GREEN, Color.parseColor("#800080"), Color.parseColor("#FFA500"))

        val barData = BarData(barDataSet)

        barChart.data = barData
        barChart.axisLeft.axisMinimum = 0f
        barChart.axisLeft.axisMaximum = 10f
        barChart.invalidate()

        val pieEntries = mutableListOf<PieEntry>()
        for ((index, count) in buttonPressCounts.withIndex()) {
            val switchName = getSwitchName(index)
            pieEntries.add(PieEntry(count.toFloat(), switchName))
        }

        val pieDataSet = PieDataSet(pieEntries, "Morning Routine")
        pieDataSet.colors = listOf(Color.parseColor("#ADD8E6"), Color.parseColor("#FFC0CB"), Color.parseColor("#90EE90"),
            Color.parseColor("#BA55D3"), Color.parseColor("#FFD700"))

        val pieData = PieData(pieDataSet)

        pieChart.data = pieData
        pieChart.invalidate()
    }

    /**
     * This class updates all the evening charts.
     * It gets all the required switches (the evening ones).
     * It also sets up the bar chart and pie chart with the correct axis.
     * This class also sets the colours for each of the charts.
     * It updates the charts as each button is submitted whilst the switch is true.
     */

    private fun updateEveningCharts() {
        val switches = listOf(switchReflectOnDay, switchPrepareTomorrow, switchEatHealthyDinner, switchUnplug, switchRelaxation)
        val barEntriesEvening = mutableListOf<BarEntry>()

        switches.forEachIndexed { index, switch ->
            if (switch.isChecked) {
                buttonPressCounts[index + 5]++
            }
            val currentValue = buttonPressCounts[index + 5].toFloat()
            barEntriesEvening.add(BarEntry(index.toFloat(), currentValue))
        }

        val barDataSet = BarDataSet(barEntriesEvening, "Evening Routine")
        barDataSet.colors = listOf(Color.parseColor("#0000FF"), Color.parseColor("#FF0000"),
            Color.parseColor("#00FF00"), Color.parseColor("#800080"), Color.parseColor("#FFA500"))

        val barData = BarData(barDataSet)

        barChartEvening.data = barData
        barChartEvening.axisLeft.axisMinimum = 0f
        barChartEvening.axisLeft.axisMaximum = 10f
        barChartEvening.invalidate()

        val pieEntries = mutableListOf<PieEntry>()
        for ((index, count) in buttonPressCounts.slice(5 until buttonPressCounts.size).withIndex()) {
            val switchName = getSwitchName(index + 5)
            pieEntries.add(PieEntry(count.toFloat(), switchName))
        }

        val pieDataSet = PieDataSet(pieEntries, "Evening Routine")
        pieDataSet.colors = listOf(Color.parseColor("#0000FF"), Color.parseColor("#FF0000"),
            Color.parseColor("#00FF00"), Color.parseColor("#800080"), Color.parseColor("#FFA500"))

        val pieData = PieData(pieDataSet)

        pieChartEvening.data = pieData
        pieChartEvening.invalidate()
    }

    /**
     * This methods sets a numeric array to each switch.
     * This helps identify the correct switch in the graph.
     */

    private fun getSwitchName(index: Int): String {
        return when (index) {
            0 -> "Wake Up"
            1 -> "Wash Up"
            2 -> "Eat Food"
            3 -> "Journal"
            4 -> "Pray"
            5 -> "Reflect on Day"
            6 -> "Prepare Tomorrow"
            7 -> "Eat Healthy Dinner"
            8 -> "Unplug"
            9 -> "Relaxation"
            else -> "Unknown"
        }
    }
}

/**
 * This class is used for the alarm.
 * When the alarm is pressed, it goes to play the specified sound.
 * In this case, it is the default sound.
 */

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val alarmSound: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        val ringtone: Ringtone = RingtoneManager.getRingtone(context, alarmSound)
        ringtone.play()
    }
}