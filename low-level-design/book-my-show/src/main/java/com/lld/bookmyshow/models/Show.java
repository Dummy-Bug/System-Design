package com.lld.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Entity(name = "shows")
@Getter
@Setter
public class Show extends BaseModel {

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    private Instant startTime;

    private Instant endTime;

    @ManyToOne
    @JoinColumn(name = "auditorium_id")
    Auditorium auditorium;

    @OneToMany(mappedBy = "show")
    List<ShowSeat> showSeat;

}
