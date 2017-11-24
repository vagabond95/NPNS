package com.naver.npns.util;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.provider.Settings.Secure;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

public class UuidHelper {
    public static final String PREFS_FILE = "uuid_file.xml";
    public static final String PREFS_DEVICE_ID = "uuid";

    public static String getUuid(Context context) {

        final SharedPreferences prefs = context.getSharedPreferences(PREFS_FILE, 0);
        final String uuids = prefs.getString(PREFS_DEVICE_ID, null);

        if (uuids != null) {
            return uuids;
        } else {
            String androidId = Secure.getString(context.getContentResolver(), Secure.ANDROID_ID);
            UUID uuid = null;
            try {
                if (!"9774d56d682e549c".equals(androidId)) {
                    uuid = UUID.nameUUIDFromBytes(androidId.getBytes("utf8"));
                } else {
                    if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
                        String deviceId = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
                        uuid = deviceId != null ? UUID.nameUUIDFromBytes(deviceId.getBytes("utf8")) : UUID.randomUUID();
                    }
                }
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
            prefs.edit().putString(PREFS_DEVICE_ID, uuid.toString()).commit();
            return uuid.toString();
        }
    }
}
