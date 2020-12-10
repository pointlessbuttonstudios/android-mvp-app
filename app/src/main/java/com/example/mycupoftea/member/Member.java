package com.example.mycupoftea.member;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="member_table")
public class Member implements Parcelable
{
    @PrimaryKey
    @NonNull
    @ColumnInfo(name="ID")
    private String ID;

    @NonNull
    @ColumnInfo(name="name")
    private String name;

    @NonNull
    @ColumnInfo(name="position")
    private String position;

    @NonNull
    @ColumnInfo(name="image")
    private String image;

    @NonNull
    @ColumnInfo(name="personality")
    private String personality;

    @NonNull
    @ColumnInfo(name="interests")
    private String interests;

    @NonNull
    @ColumnInfo(name="preferences")
    private String preferences;

    public Member(String ID, String name, String position, String image, String personality, String interests, String preferences)
    {
        this.ID = ID;
        this.name = name;
        this.position = position;
        this.image = image;
        this.personality = personality;
        this.interests = interests;
        this.preferences = preferences;
    }
    protected Member(Parcel in)
    {
        ID = in.readString();
        name = in.readString();
        position = in.readString();
        image = in.readString();
        personality = in.readString();
        interests = in.readString();
        preferences = in.readString();
    }
    public static final Creator<Member> CREATOR = new Creator<Member>()
    {
        @Override
        public Member createFromParcel(Parcel in)
        {
            return new Member(in);
        }

        @Override
        public Member[] newArray(int size)
        {
            return new Member[size];
        }
    };

    public String getID()
    {
        return ID;
    }
    public String getName()
    {
        return name;
    }
    public String getPosition()
    {
        return position;
    }
    public String getImage()
    {
        return image;
    }
    public String getPersonality()
    {
        return personality;
    }
    public String getInterests()
    {
        return interests;
    }
    public String getPreferences()
    {
        return preferences;
    }
    @NonNull
    @Override
    public String toString()
    {
        return "ID: " + this.ID +
                "\nname: " + this.name +
                "\nposition: " + this.position +
                "\nimage:" + this.image +
                "\npersonality: " + this.personality +
                "\ninterests: " + this.interests +
                "\npreferences: " + this.preferences;
    }
    @Override
    public int describeContents()
    {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(ID);
        dest.writeString(name);
        dest.writeString(position);
        dest.writeString(image);
        dest.writeString(personality);
        dest.writeString(interests);
        dest.writeString(preferences);
    }
}