package edu.temple.scopefunctionactivity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // You can test your helper functions by  calling them from onCreate() and
        // printing their output to the Log, which is visible in the LogCat:
        // eg. Log.d("function output", getTestDataArray().toString())

        Log.d("Function 1 output: ", getTestDataArray().toString())
        val listOne = listOf(10.2, 24.8, 1.9, 30.8, 55.8)
        val listTwo = listOf(11.6, 90.3, 37.4, 82.0, 10.8, 8.3)
        Log.d("Function 2 output: ", averageLessThanMedian(listOne).toString())
        Log.d("Function 2 output: ", averageLessThanMedian(listTwo).toString())
        val collection = getTestDataArray()
        val testView = getView(0, null, collection, this)

        Log.d("Function 3 output: ", testView.toString())

    }


    /* Convert all the helper functions below to Single-Expression Functions using Scope Functions */
    // eg. private fun getTestDataArray() = ...

    // HINT when constructing elaborate scope functions:
    // Look at the final/return value and build the function "working backwards"

    // Return a list of random, sorted integers
       private fun getTestDataArray() = MutableList(10) {
           Random.nextInt()
       }.apply {
           sort()
    }

    // Return true if average value in list is greater than median value, false otherwise
    private fun averageLessThanMedian(listOfNumbers: List<Double>) = listOfNumbers.run {
        //.run calls everything within brackets, then returns it, let it similar
        val average = average()
        sorted().let { sortedList ->
            val median: Double

            if(size % 2 == 0)
            {
                //if list size is even, do the old take middle two and divide trick
                median = (sortedList[size / 2] + sortedList[(size - 1) / 2]) / 2
            }
            else
            {
                median = sortedList[size / 2]
            }

            average < median
        }
    }

    // Create a view from an item in a collection, but recycle if possible (similar to an AdapterView's adapter)
    private fun getView(position: Int, recycledView: View?, collection: List<Int>, context: Context) =
        //cast the recycledView as a TextView is possible, add the changes made
        (recycledView as? TextView ?: TextView(context).apply {
            setPadding(5, 10, 10, 0)
            textSize = 22f
        }).also {
            it.text = collection[position].toString()
        }

}