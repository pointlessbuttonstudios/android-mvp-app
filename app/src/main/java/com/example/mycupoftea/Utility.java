package com.example.mycupoftea;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.preference.PreferenceManager;
import com.bumptech.glide.request.RequestOptions;
import com.example.mycupoftea.member.MemberViewModel;

import java.io.IOException;
public class Utility
{
    private static SharedPreferences sharedPreferences;
    static final String MEMBER_TAG = "MEMBER";
    static final String ADDED_MEMBER = "ADDED_AMINA";
    private static MemberViewModel memberViewModel;

    // singleton
    private static Utility INSTANCE;
    public static void init(Context context, MemberViewModel mvm)
    {
        INSTANCE = new Utility(context, mvm);
    }
    private Utility(Context context, MemberViewModel mvm)
    {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        memberViewModel = mvm;
    }
    public static boolean hasAddedMember()
    {
        return sharedPreferences.getBoolean(ADDED_MEMBER, false);
    }
    public static void setAddedMember(boolean added)
    {
        sharedPreferences.edit().putBoolean(ADDED_MEMBER, added).apply();
    }
    /**
     * Basic internet connectivity check
     * @return true if there is internet connectivity
     */
    public static boolean isOnline()
    {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int     exitValue = ipProcess.waitFor();
            return (exitValue == 0);
        }
        catch (IOException e)          { e.printStackTrace(); }
        catch (InterruptedException e) { e.printStackTrace(); }

        return false;
    }
    /**
     * Creates a black & white color filter to apply to images
     * @return bw color filter
     */
    public static ColorMatrixColorFilter transformBW()
    {
        ColorMatrix matrix = new ColorMatrix();
        matrix.setSaturation(0);
        return new ColorMatrixColorFilter(matrix);
    }
    /**
     * Creates options to customize image loads with Glide
     * @return requestOptions
     */
    public static RequestOptions imageHandler()
    {
        return new RequestOptions().placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground);
    }
    public static MemberViewModel getMemberViewModel()
    {
        return memberViewModel;
    }
}
