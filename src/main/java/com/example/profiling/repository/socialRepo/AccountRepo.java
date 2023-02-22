package com.example.profiling.repository.socialRepo;

import com.example.profiling.entity.socialEntity.Account;
import com.example.profiling.entity.socialEntity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepo extends JpaRepository<Account, Integer> {
    Account findByAccountId(Integer accountId);

    Account findByAccountName(String accountName);
}
