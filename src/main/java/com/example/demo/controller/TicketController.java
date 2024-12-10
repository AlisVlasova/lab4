package com.example.demo.controller;

import com.example.demo.dto.TicketDTO;
import com.example.demo.model.Ticket;
import com.example.demo.service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@AllArgsConstructor
public class TicketController {

    TicketService ticketService;

    @GetMapping
    public List<Ticket> getAllTickets() {
        return ticketService.getAll();
    }

    @GetMapping("/{id}")
    public List<Ticket> getAllTicketsByUserId(@PathVariable int id) {
        return ticketService.getAllByUser(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Ticket save(@RequestBody TicketDTO ticket) {
        return ticketService.createTicket(ticket);

    }


}
