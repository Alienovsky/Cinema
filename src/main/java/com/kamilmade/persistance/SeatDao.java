package com.kamilmade.persistance;

import com.kamilmade.pojos.Seat;

public interface SeatDao {
    public void insert(Seat seat);
    public Seat findById(int seatId);
}
