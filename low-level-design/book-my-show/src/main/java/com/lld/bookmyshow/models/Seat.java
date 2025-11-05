package com.lld.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity // makes the class managed by JPA.
@Table(name = "seat") //Tells JPA which DB table name to use.
public class Seat extends BaseModel {

    private String seatNumber;

    private int rowValue;

    private int columnValue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auditorium_id")
    Auditorium auditorium;

    @Enumerated(EnumType.ORDINAL)
    SeatType seatType;
}
