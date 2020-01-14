package com.haibin.calendarviewproject.test

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.haibin.calendarviewproject.R

class Main2Activity : AppCompatActivity() {

    private var dateDialog: DateChooseDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
    }

    fun onDialog(view: View) {
        if (dateDialog == null) {
            dateDialog = DateChooseDialog(this)
        }
        dateDialog!!.setChooseListener(OnDateChooseListener { start, end ->

            Toast.makeText(this, "start: " + start.toString(), Toast.LENGTH_LONG).show()
        })
        dateDialog!!.show()
    }
}
