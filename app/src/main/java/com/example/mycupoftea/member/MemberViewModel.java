package com.example.mycupoftea.member;
import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

public class MemberViewModel extends AndroidViewModel
{
    private MemberRepository memberRepository;
    private LiveData<List<Member>> allMembers;

    public MemberViewModel(@NonNull Application application)
    {
        super(application);
        memberRepository = new MemberRepository(application);
        allMembers = memberRepository.getAllMembers();
    }
    public LiveData<List<Member>> getAllMembers()
    {
        return allMembers;
    }
    public void insert(Member member)
    {
        memberRepository.insert(member);
    }
    public void update(Member member)
    {
        memberRepository.update(member);
    }
    public void deleteAll()
    {
        memberRepository.deleteAll();
    }
    public void delete(Member member)
    {
        memberRepository.delete(member);
    }
}
