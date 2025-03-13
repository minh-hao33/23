package com.example.hrms.biz.booking.controller;

import com.example.hrms.biz.booking.model.Booking;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bookings")
public class  BookingController {

    @RequestMapping("")
    public String openBookingView(Model model) {
        model.addAttribute("booking", new Booking());
        return "bookings";
    }
}