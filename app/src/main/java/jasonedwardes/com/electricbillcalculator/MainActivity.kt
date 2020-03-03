package jasonedwardes.com.electricbillcalculator

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    /*
Electric bill calculator is designed to give you the average cost of running an electric appliance
Use the spinner to select the electric appliance.
Enter the hours you will be running the electric appliance each day
Enter the cost of electric (kWh) average uk price (14)
*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // buttons, textViews, int for the hours and kwh price buttons(onclick)

        var intCostPerKWh = 14
        var intHoursUsedPerDay = 6
        var intPricePerDay = 10
        txtKWh.text = "$intCostPerKWh"
        txtDay.text = "$intHoursUsedPerDay"
        var intcounter = 5

        btnKWhPlus.setOnClickListener {
            if (intCostPerKWh in 6..26) {
                intCostPerKWh += 1
                txtKWh.text = "$intCostPerKWh"
                clearTextView()
            }
        }
        btnKWhMinus.setOnClickListener {
            if (intCostPerKWh in 7..27) {
                intCostPerKWh -= 1
                txtKWh.text = "$intCostPerKWh"
                clearTextView()
            }
        }
        btnDayPlus.setOnClickListener {
            if (intHoursUsedPerDay in 0..23) {
                intHoursUsedPerDay += 1
                txtDay.text = "$intHoursUsedPerDay"
                clearTextView()
            }
        }
            btnDayMinus.setOnClickListener {
                if (intHoursUsedPerDay in 1..24) {
                    intHoursUsedPerDay -= 1
                    txtDay.text = "$intHoursUsedPerDay"
                    clearTextView()
                }
            }

            //array of common house hold electrical items
            val arrayAppliance = arrayOf(
                "Air conditioner",
                "Tumble dryer",
                "iron",
                "Dish washer",
                "Kettle",
                "oscillating Fan",
                "Fan heater",
                "Desktop pc",
                "Laptop",
                "Fridge",
                "Freezer",
                "fridge freezer",
                "Phone",
                "Television",
                "Hoover",
                "Washing machine",
                "Toaster"
            )
            // average amount of power the house hold electrical items use (Air conditioner uses 1500)
            val arrayApplianceWatts = intArrayOf(
                1500,
                3000,
                1100,
                1800,
                1800,
                50,
                1500,
                100,
                50,
                162,
                327,
                427,
                5,
                100,
                1400,
                500,
                1200
            )
            //select an item on the spinner
            val arrayAdapter =
                ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayAppliance)

            //attached array adapter to spinner
            id_appliance_spinner.adapter = arrayAdapter
            id_appliance_spinner.onItemSelectedListener = object :

                AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    p1: View?,
                    array_counter: Int,
                    p3: Long
                ) {
                    // displays (arrayAppliance[array_counter]) and the watts used (arrayApplianceWatts[array_counter])
                    // id_kWh_used is the textview that displays arrayApplianceWatts
                    id_kWh_used.text = "  " + arrayApplianceWatts[array_counter]
                    intcounter = array_counter
                    txt_cost_per_day.text = ""
                    txt_cost_per_week.text = ""
                    txt_cost_per_mouth.text = ""
                    txt_cost_per_year.text = ""
                    // get current spinner Appliance in kWh (int IntElectricUse ) -> (double doubleElectricUsed)

                }
            }
            //  hours per day * price per kWh * appliance kwh / 1000 = price per day
            btn_calculate.setOnClickListener {
                intPricePerDay =
                    (intHoursUsedPerDay * intCostPerKWh * arrayApplianceWatts[intcounter]) / 1000

                // sets the intPricePerDay at 1p minimal (tempted to remove phone from spinner)
                if (intPricePerDay < 1) {
                    intPricePerDay = 1
                }

                val finalPricePerDay = ConverToPounds(intPricePerDay)
                val finalPricePerWeek = ConverToPounds(intPricePerDay * 7)
                val finalPricePerMonth = ConverToPounds((intPricePerDay * 7) * 52 / 12)
                val finalPricePerYear = ConverToPounds(intPricePerDay * 365)


                txt_cost_per_day.text = finalPricePerDay
                txt_cost_per_week.text = finalPricePerWeek
                txt_cost_per_mouth.text = finalPricePerMonth
                txt_cost_per_year.text = finalPricePerYear

            }
        }



    // covert a int into a string pounds and pence
    private fun ConverToPounds(intPrice: Int): String {
        var strPrice = ""
        var intPound = 0
        var intCounter = 0
        var strPence = "09"
        for (x in 1..intPrice) {
            intCounter += 1
            if (intCounter == 100) {
                intCounter = 0
                intPound += 1
            }
            var intPence = intPrice - (intPound * 100)

                if (intPence in 1..9) {
                    strPence = "0$intPence"
                    strPrice = "£$intPound.$strPence"
                } else {
                    strPrice = "£$intPound.$intPence"
                }
            }
            return strPrice
        }

    //clear textViews
    private fun clearTextView() {
        txt_cost_per_day.text = ""
        txt_cost_per_week.text = ""
        txt_cost_per_mouth.text = ""
        txt_cost_per_year.text = ""
   }
}