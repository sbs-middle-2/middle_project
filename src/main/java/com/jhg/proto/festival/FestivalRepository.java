package com.jhg.proto.festival;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FestivalRepository extends JpaRepository<Festival, Integer> {
    // 모든 축제 데이터 가져오기
    List<Festival> findAll();

    // ID로 축제 데이터 가져오기
    Festival findById(int id);

    /*
    // 제목(title)로 축제 데이터 가져오기
    List<Festival> findByTitle(String title);
    */
}