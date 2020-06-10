package com.example.covid19trackingapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.covid19trackingapp.utilites.Urls
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.utils.ColorTemplate
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

//    var barChart:BarChart = TODO()
    lateinit var bardata : BarData
    lateinit var bardataset:BarDataSet
      var arraylist = ArrayList<BarEntry>()
    //    lateinit var barChart:BarChart
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        gettingBarEntries()
        loadData()
        bardataset = BarDataSet(arraylist, "Data Set")
        bardata = BarData(bardataset)
        barchartCovid.data = bardata
//        bardataset.setColor(Int(ColorTemplate.MATERIAL_COLORSzz)
        bardataset.setValueTextColor(Color.BLACK)
        bardataset.valueTextSize = 16f
    }






    fun gettingBarEntries(){
        arraylist.add(BarEntry(1f,2f))
        arraylist.add(BarEntry( 2f,4f))
        arraylist.add(BarEntry(3f,6f))
        arraylist.add(BarEntry(4f,8f))

    }


    fun loadData() {



        Log.d("Urls",Urls.url1)
        val jsonObj = object : JsonObjectRequest(
            Request.Method.GET,
            Urls.url1,
            null,
            object : Response.Listener<JSONObject> {
                override fun onResponse(response: JSONObject) {
                    Log.d("responseData",""+response)

                    val insideJson = response.getJSONObject("Global")
                    val totalconfirmedCases = insideJson.getString("TotalConfirmed")
                    Log.d("Total Cases",totalconfirmedCases)

                }
            },
            Response.ErrorListener {error ->

            Log.e("responseError",error.toString())

            }){

        }
        val requestQue = Volley.newRequestQueue(this)
        requestQue.add(jsonObj)

    }
}

