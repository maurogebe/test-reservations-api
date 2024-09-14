package com.example.demo.application.usecases;

import com.example.demo.application.dtos.ReservationDTO;
import com.example.demo.application.exeptions.ApiRequestException;
import com.example.demo.application.mappers.CustomerMapper;
import com.example.demo.application.mappers.ReservationMapper;
import com.example.demo.domain.entities.Customer;
import com.example.demo.domain.entities.Reservation;
import com.example.demo.domain.repositories.CustomerRepository;
import com.example.demo.domain.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationUseCase {
    
    private final ReservationRepository reservationRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public ReservationUseCase(ReservationRepository reservationRepository, CustomerRepository customerRepository) {
        this.reservationRepository = reservationRepository;
        this.customerRepository = customerRepository;
    }

    public ReservationDTO getReservationById(Long id) {
        Optional<Reservation> reservationById = this.reservationRepository.findById(id);

        if(reservationById.isEmpty()) throw new ApiRequestException("No se encontró la reservación con ID: " + id, HttpStatus.NOT_FOUND);

        return ReservationMapper.INSTANCE.reservationToReservationDTO(reservationById.get());
    }
    
    public List<ReservationDTO> getReservations() {
        List<Reservation> reservations = this.reservationRepository.findAll();
        return ReservationMapper.INSTANCE.reservationsToReservationsDTO(reservations);
    }

    public void createReservation(ReservationDTO reservation) {
        boolean validateReservation = this.validateAvailability(reservation.getStartDate(), reservation.getEndDate());
        if(validateReservation) throw new ApiRequestException("Ya existe una reserva entre estas fechas", HttpStatus.CONFLICT);
        
        Optional<Customer> customerByEmail = this.customerRepository.findByEmail(reservation.getCustomer().getEmail());
        Customer customerSaved = null;
        if(customerByEmail.isEmpty()) customerSaved = this.customerRepository.save(CustomerMapper.INSTANCE.customerDTOToCustomer(reservation.getCustomer()));
        
        if(customerSaved != null) reservation.setCustomer(CustomerMapper.INSTANCE.customerToCustomerDTO(customerSaved));
        this.reservationRepository.save(ReservationMapper.INSTANCE.reservationDTOToReservation(reservation));
    }
    
    private boolean validateAvailability(LocalDateTime startDate, LocalDateTime endDate) {
        List<Reservation> conflictingReservations = reservationRepository.findConflictingReservations(startDate, endDate);
        return conflictingReservations.isEmpty();
    }
}
