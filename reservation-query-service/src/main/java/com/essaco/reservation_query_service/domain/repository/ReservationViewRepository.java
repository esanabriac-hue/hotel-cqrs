package com.essaco.reservation_query_service.domain.repository;

import com.essaco.reservation_query_service.domain.model.ReservationView;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReservationViewRepository extends MongoRepository<ReservationView, String> {
}
