package com.naver.npns.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeHelper {
    private long mNow;
    private Date mDate;
    private SimpleDateFormat mFormat = new SimpleDateFormat("aa hh:mm");

     public TimeHelper() {
     }

     public String getTime() {
         mNow = System.currentTimeMillis();
         mDate = new Date(mNow);
         return mFormat.format(mDate);
     }
}
