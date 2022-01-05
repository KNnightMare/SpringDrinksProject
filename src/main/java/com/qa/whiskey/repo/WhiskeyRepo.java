package com.qa.whiskey.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.whiskey.domain.Whiskey;

public interface WhiskeyRepo extends JpaRepository <Whiskey, Long> {

}
