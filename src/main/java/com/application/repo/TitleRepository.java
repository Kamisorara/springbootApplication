package com.application.repo;

import com.application.entity.Account;
import com.application.entity.title;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TitleRepository extends JpaRepository<title, Integer> {
}
