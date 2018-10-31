

package io.github.subhamtyagi.openinwhatsapp.model;

import android.content.Context;

public class Country {

    private String mName;
    private String mCountryISO;
    private int mCountryCode;
    private String mCountryCodeStr;
    private int mPriority;
    private int mResId;
    private int mNum;

    public Country(Context context, String str, int num) {
        String[] data = str.split(",");
        mNum = num;
        mName = data[0];
        mCountryISO = data[1];
        mCountryCode = Integer.parseInt(data[2]);
        mCountryCodeStr = "+" + data[2];
        if (data.length > 3) {
            mPriority = Integer.parseInt(data[3]);
        }
        String fileName = String.format("f%03d", num);
        mResId = context.getApplicationContext().getResources().getIdentifier(fileName, "drawable", context.getApplicationContext().getPackageName());
    }

    public String getName() {
        return mName;
    }

    public String getCountryISO() {
        return mCountryISO;
    }

    public int getCountryCode() {
        return mCountryCode;
    }

    public String getCountryCodeStr() {
        return mCountryCodeStr;
    }

    public int getPriority() {
        return mPriority;
    }

    public int getResId() {
        return mResId;
    }

    public int getNum() {
        return mNum;
    }
}
