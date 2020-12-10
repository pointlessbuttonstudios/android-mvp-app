package com.example.mycupoftea.member;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.mycupoftea.FetchDataManager;
import com.example.mycupoftea.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Database(entities = {Member.class}, version = 1, exportSchema = false)
public abstract class MemberRoomDatabase extends RoomDatabase
{
    private static Context con;
    private static MemberRoomDatabase INSTANCE;
    private static RoomDatabase.Callback roomDatabaseCallback =
            new RoomDatabase.Callback()
            {
                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db)
                {
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };
    public static MemberRoomDatabase getDatabase(final Context context)
    {
        if (INSTANCE == null)
        {
            synchronized (MemberRoomDatabase.class)
            {
                if (INSTANCE == null)
                {
                    // create database here
                    con = context;
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MemberRoomDatabase.class, "member_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(roomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
    public abstract MemberDao memberDao();

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void>
    {
        private final MemberDao mDao;
        PopulateDbAsync(MemberRoomDatabase db)
        {
            Log.d("MemberRoomDatabse-->", "PopulateDbAsync()");
            mDao = db.memberDao();
        }
        @Override
        protected final Void doInBackground(final Void... params)
        {
            String teamJSON = FetchDataManager.getJSONString(con, R.raw.team);
            Log.d("MemberRoomDatabase-->", teamJSON);
            try
            {
                if (teamJSON != null && teamJSON.length() > 0)
                {
                    JSONArray jSONMemberArray = new JSONArray(teamJSON);
                    if (jSONMemberArray.length() > 0)
                    {
                        for (int i = 0; i < jSONMemberArray.length(); i++)
                        {
                            JSONObject JSONMemberObject = (JSONObject) jSONMemberArray.get(i);

                            String ID = "";
                            String name = "";
                            String position = "";
                            String image = "";
                            String personality = "";
                            String interests = "";
                            String preferences = "";

                            // Always check if JSON exists before doing work
                            if (JSONMemberObject.has("id"))
                            {
                                try
                                {
                                    ID = JSONMemberObject.getString("id");
                                }
                                catch (Exception exception)
                                {
                                    exception.printStackTrace();
                                }
                            }
                            if (JSONMemberObject.has("name"))
                            {
                                try
                                {
                                    name = JSONMemberObject.getString("name");
                                }
                                catch (Exception exception)
                                {
                                    exception.printStackTrace();
                                }
                            }
                            if (JSONMemberObject.has("position"))
                            {
                                try
                                {
                                    position = JSONMemberObject.getString("position");
                                }
                                catch (Exception exception)
                                {
                                    exception.printStackTrace();
                                }
                            }
                            if (JSONMemberObject.has("profile_image"))
                            {
                                try
                                {
                                    image = JSONMemberObject.getString("profile_image");
                                }
                                catch (Exception exception)
                                {
                                    exception.printStackTrace();
                                }
                            }
                            if (JSONMemberObject.has("personality"))
                            {
                                try
                                {
                                    personality = JSONMemberObject.getString("personality");
                                }
                                catch (Exception exception)
                                {
                                    exception.printStackTrace();
                                }
                            }
                            if (JSONMemberObject.has("interests"))
                            {
                                try
                                {
                                    interests = JSONMemberObject.getString("interests");
                                }
                                catch (Exception exception)
                                {
                                    exception.printStackTrace();
                                }
                            }
                            if (JSONMemberObject.has("dating_preferences"))
                            {
                                try
                                {
                                    preferences = JSONMemberObject.getString("dating_preferences");
                                }
                                catch (Exception exception)
                                {
                                    exception.printStackTrace();
                                }
                            }
                            Member member = new Member(ID, name, position, image, personality, interests, preferences);
                            mDao.insert(member);
                        }
                    }
                    else
                    {
                        Log.e("PopulateDbAsync-->", "JSON string is empty or null");
                    }
                }
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
            return null;
        }
    }
}
