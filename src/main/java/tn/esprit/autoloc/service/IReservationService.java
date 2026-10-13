package tn.esprit.autoloc.service;

import java.util.List;
import tn.esprit.autoloc.domain.Reservation;

public interface IReservationService {
    Reservation saveReservation(Reservation reservation);
    Reservation getReservationById(Long id);
    List<Reservation> getAllReservations();
    Reservation updateReservation(Long id, Reservation reservation);
    void deleteReservation(Long id);
}