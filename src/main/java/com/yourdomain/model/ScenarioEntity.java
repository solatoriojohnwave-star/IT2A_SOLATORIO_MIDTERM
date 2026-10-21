package com.yourdomain.model;

public record ScenarioEntity(
        int id,
        String customerName,
        String movieTitle,
        String seatNo,
        double price
) {}