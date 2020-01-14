package com.haibin.calendarviewproject.test;

import java.util.Calendar;

/**
 * Desc:
 * <p> 日期选择回调
 * Date: 2019-12-18
 * Copyright: Copyright (c) 2010-2019
 * Company: @微微科技有限公司
 * Updater:
 * Update Time:
 * Update Comments:
 *
 * @Author: zhengxiaobin
 */
public interface OnDateChooseListener {
    /**
     * 选择回调
     *
     * @param start
     * @param end
     */
    void onChoose(Calendar start, Calendar end);
}