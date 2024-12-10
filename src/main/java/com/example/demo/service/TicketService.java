package com.example.demo.service;

import com.example.demo.dto.TicketDTO;
import com.example.demo.model.Ticket;
import com.example.demo.repository.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class TicketService {
    TicketRepository ticketRepository;

    UserProfileService userProfileService;

    FlightService flightService;

    public List<Ticket> getAll() {
        return ticketRepository.findAll();
    }

    public List<Ticket> getAllByUser(int id) {
        return ticketRepository.findByUserProfileId(id);
    }

    public Ticket createTicket(TicketDTO ticketDTO) {
        Ticket ticket = new Ticket();
        ticket.setPnr(ticketDTO.getPnr());
        ticket.setUserProfile(userProfileService.getUserByUsername(ticketDTO.getUsername()));
        ticket.setFlight(flightService.findById(ticketDTO.getFlightId()));
        ticket.setCreatedAt(LocalDateTime.now());
        return ticketRepository.save(ticket);
    }
}
