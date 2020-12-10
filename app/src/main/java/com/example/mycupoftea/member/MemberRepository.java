package com.example.mycupoftea.member;
import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;
import java.util.List;
public class MemberRepository
{
    private MemberDao memberDao;
    private LiveData<List<Member>> allMembers;
    MemberRepository(Application application)
    {
        MemberRoomDatabase memberRoomDatabase = MemberRoomDatabase.getDatabase(application);
        memberDao = memberRoomDatabase.memberDao();
        allMembers = memberDao.getAllMembers();
    }
    LiveData<List<Member>> getAllMembers()
    {
        return allMembers;
    }
    public void update(Member member)
    {
        new updateAsyncTask(memberDao).execute(member);
    }
    public void insert(Member member)
    {
        new insertAsyncTask(memberDao).execute(member);
    }
    public void deleteAll()
    {
        new deleteAllMembersAsyncTask(memberDao).execute();
    }
    public void delete(Member member)
    {
        new deleteAsyncTask(memberDao).execute(member);
    }
    private static class updateAsyncTask extends AsyncTask<Member, Void, Void>
    {
        private MemberDao asyncTaskDao;

        updateAsyncTask(MemberDao dao)
        {
            asyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(final Member... params)
        {
            asyncTaskDao.update(params[0]);
            return null;
        }
    }
    private static class insertAsyncTask extends AsyncTask<Member, Void, Void>
    {
        private MemberDao asyncTaskDao;
        insertAsyncTask(MemberDao dao)
        {
            asyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(final Member... params)
        {
            asyncTaskDao.insert(params[0]);
            return null;
        }
    }
    private static class deleteAllMembersAsyncTask extends AsyncTask<Void, Void, Void>
    {
        private MemberDao asyncTaskDao;
        deleteAllMembersAsyncTask(MemberDao dao)
        {
            asyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(Void... voids)
        {
            asyncTaskDao.deleteAll();
            return null;
        }
    }
    private static class deleteAsyncTask extends AsyncTask<Member, Void, Void>
    {
        private MemberDao asyncTaskDao;
        deleteAsyncTask(MemberDao dao)
        {
            asyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(final Member... params)
        {
            asyncTaskDao.deleteMember(params[0]);
            return null;
        }
    }
}
