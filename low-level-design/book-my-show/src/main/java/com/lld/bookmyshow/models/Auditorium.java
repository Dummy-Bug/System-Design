package com.lld.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Auditorium extends BaseModel {
    private String name;
    private int capacity;

    @OneToMany(mappedBy = "auditorium")
    List<Seat> seats;

    @OneToMany(mappedBy = "auditorium")
    List<Show> shows;

    @ManyToOne
    @JoinColumn(name = "theatre_id")
    Theatre theatre;

}
