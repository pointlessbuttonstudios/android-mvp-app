package com.example.mycupoftea.member;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface MemberDao
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Member member);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Member member);

    @Query("SELECT * FROM member_table")
    LiveData<List<Member>> getAllMembers();

    @Query("SELECT * FROM member_table LIMIT 1")
    Member[] getAnyMember();

    @Query("DELETE FROM member_table")
    void deleteAll();

    @Delete
    void deleteMember(Member member);
}
