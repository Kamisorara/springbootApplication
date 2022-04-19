package com.application.repo;

import com.application.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    Account findAllByUsername(String username);

    //@Transactional @Modifying 一定要加
    @Transactional
    @Modifying
    @Query("update Account set password =?2 where username=?1")
    int updatePasswordByUserName(String userName, String password);
}
