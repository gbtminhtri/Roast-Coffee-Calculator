package com.gbtminhtri.roastcoffeecalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.*
import com.gbtminhtri.videoapp.R
import java.math.RoundingMode

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edtMinuteFirstCrack:EditText = findViewById(R.id.edtMinFc)
        val edtSecondFirstCrack:EditText = findViewById(R.id.edtSecFc)
        val edtDevelopmentTimeRatio:EditText = findViewById(R.id.edtDevTimeRatio)
        val txtResultTotalMinute:TextView = findViewById(R.id.txtMinRoastTime)
        val txtResultTotalSecond:TextView = findViewById(R.id.txtSecTotalRoastTime)
        val btnResult:Button = findViewById(R.id.btnResult)

        btnResult.setOnClickListener {

            if (TextUtils.isEmpty(edtMinuteFirstCrack.text.toString()) || TextUtils.isEmpty(edtSecondFirstCrack.text.toString()) || TextUtils.isEmpty(edtDevelopmentTimeRatio.text.toString())){
                Toast.makeText(this,"Please enter first crack time and development ratio",Toast.LENGTH_SHORT).show()
            }else{
                val firstCrackMinute = edtMinuteFirstCrack.text.toString().toFloat()
                val firstCrackSecond = (edtSecondFirstCrack.text.toString().toFloat())/60
                val firstCrackTime = firstCrackMinute + firstCrackSecond
                if (edtDevelopmentTimeRatio.text.toString().toFloat()>=100){
                    Toast.makeText(this,"Please enter development ratio from 1 to 99",Toast.LENGTH_SHORT).show()
                    txtResultTotalMinute.text = ""
                    txtResultTotalSecond.text = ""
                }else{
                    val developmentTimeRatio = edtDevelopmentTimeRatio.text.toString().toFloat()
                    val totalRoastTime = firstCrackTime/(1-(developmentTimeRatio/100))
                    val intTotalRoastTime = totalRoastTime.toInt()
                    //val decimalTotalRoastTime = ((totalRoastTime - intTotalRoastTime)*60).toInt()
                    val decimalTotalRoastTime = ((totalRoastTime - intTotalRoastTime)*60).toBigDecimal().setScale(1,RoundingMode.UP).toInt()
                    //val roundedNumber = decimalTotalRoastTime.toBigDecimal().setScale(1, RoundingMode.UP).toDouble()
                    txtResultTotalMinute.text = intTotalRoastTime.toString()
                    txtResultTotalSecond.text = decimalTotalRoastTime.toString()
                    // I try to make another project
                }

            }
        }
    }
}