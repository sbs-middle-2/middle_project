package com.jhg.proto.festival;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service
public class FestivalService {
    private final FestivalRepository festivalRepository;

    public List<Festival> getList() {
        return this.festivalRepository.findAll();
    }
}