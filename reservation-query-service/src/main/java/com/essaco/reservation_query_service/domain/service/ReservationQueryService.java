package com.essaco.reservation_query_service.domain.service;

import com.essaco.reservation_query_service.domain.model.ReservationView;
import com.essaco.reservation_query_service.domain.repository.ReservationViewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationQueryService {

    private final ReservationViewRepository repository;

    public List<ReservationView> findAll() {
        return repository.findAll();
    }
}

