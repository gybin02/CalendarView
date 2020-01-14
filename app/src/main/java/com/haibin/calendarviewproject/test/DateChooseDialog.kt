package com.haibin.calendarviewproject.test


import android.app.Dialog
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.haibin.calendarview.CalendarView
import com.haibin.calendarviewproject.R
import java.util.*

/**
 * Desc:日期选择对话框
 * <p>
 * Date: 2019-12-18
 * Copyright: Copyright (c) 2010-2019
 * Company: @微微科技有限公司
 * Updater:
 * Update Time:
 * Update Comments:
 *
 * @Author: zhengxiaobin
 */

class DateChooseDialog(context: Context, start: Calendar? = null, end: Calendar? = null) : Dialog(context, R.style.SingleChooseDialog) {
    private val end: Calendar?
    private val start: Calendar?
    val monthEn = arrayOf("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec")

    private var onDateChooseListener: OnDateChooseListener? = null
    private lateinit var tvTitle: TextView
    private lateinit var tvTitlePre: TextView
    private lateinit var tvTitleNext: TextView


    private lateinit var tvDone: TextView
    private lateinit var mCalendarView: CalendarView

    init {
        init()
        this.start = start
        this.end = end
    }

    private fun init() {
        val contentView = LayoutInflater.from(context).inflate(R.layout.layout_date_choose, null)
        setContentView(contentView)
        val layoutParams = contentView.layoutParams
        layoutParams.width = context.resources.displayMetrics.widthPixels
        contentView.layoutParams = layoutParams
        window?.setGravity(Gravity.BOTTOM)
        window?.setWindowAnimations(R.style.popup_anim_bottom)

        initView(contentView)
    }

    private fun initView(content: View) {
        tvTitle = content.findViewById(R.id.tv_title)
        tvTitlePre = content.findViewById<TextView>(R.id.tv_title_pre)
        tvTitleNext = content.findViewById<TextView>(R.id.tv_title_next)


        tvDone = content.findViewById(R.id.tv_done)
        mCalendarView = content.findViewById(R.id.calendarView)
        mCalendarView.setOnMonthChangeListener { year, month ->
            initMonthHead(month)
        }
        mCalendarView.setOnCalendarInterceptListener(object : CalendarView.OnCalendarInterceptListener {
            override fun onCalendarIntercept(calendar: com.haibin.calendarview.Calendar): Boolean {
                return calendar.timeInMillis > Calendar.getInstance().timeInMillis
            }

            override fun onCalendarInterceptClick(calendar: com.haibin.calendarview.Calendar?, isClick: Boolean) {
                //yes
            }
        })
        
        tvDone.setOnClickListener { v ->
            val calendars = mCalendarView.selectCalendarRange
            if (calendars != null && calendars.size != 0) {
                val start = calendars[0]
                val end = calendars[calendars.size - 1]

                val startCalendar = Calendar.getInstance()
                startCalendar.timeInMillis = start.timeInMillis
                val ennCalendar = Calendar.getInstance()
                ennCalendar.timeInMillis = end.timeInMillis

                onDateChooseListener?.onChoose(startCalendar, ennCalendar)
                dismiss()
            }
        }
        initMonthHead(mCalendarView.curMonth)
    }

    /**
     * 初始化默认值
     */
    private fun initMonthHead(curMonth: Int) {
        tvTitle.text = monthEn[curMonth - 1]
        var indexPre = curMonth - 2
        if (indexPre < 0) {
            indexPre = 11
        }
        tvTitlePre.text = monthEn[indexPre]

        var indexNext = curMonth
        if (indexNext > 11) {
            indexNext = 0
        }
        tvTitleNext.text = monthEn[indexNext]
    }


    /**
     * Desc: 选择回调
     *
     * Date: 2019-11-11
     *
     * @param onDateChooseListener 回调
     */
    fun setChooseListener(onDateChooseListener: OnDateChooseListener) {
        this.onDateChooseListener = onDateChooseListener;
    }
}
