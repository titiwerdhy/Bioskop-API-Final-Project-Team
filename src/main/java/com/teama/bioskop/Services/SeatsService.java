package com.teama.bioskop.Services;

import com.teama.bioskop.Helpers.DataNotFoundException;
import com.teama.bioskop.Models.Seats;
import com.teama.bioskop.Repositories.SeatsRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SeatsService {
    private SeatsRepository seatsRepository;

    public List<Seats> getAllSeats(){
        return this.seatsRepository.findAll();
    }

    public Page<Seats> getAllSeatsPaged(String studioName, int pageNo, int pageSize, String sort, String order){
        Pageable pageable;
        if (sort == null) {
            if (studioName == null) {
                pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by("updatedAt").descending());
            }else{
                pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by("updated_at").descending());
            }
        }else{
            if (order.equals("ascending")) {
                pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by(sort).ascending());
            }else{
                pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by(sort).descending());
            }
        }
        if (studioName == null) {
            return this.seatsRepository.findAll(pageable);
        }else{
            return this.seatsRepository.getStudioName(studioName, pageable);
        }
    }

    public Seats insertNewSeats(Seats seats) {
        return this.seatsRepository.save(seats);
    }
    public Seats UpdateSeats ( Seats seats ){
        return this.seatsRepository.save(seats);
    }

    public void deleteSeatById(Integer id) throws DataNotFoundException {
        Optional<Seats> optionalSeat = seatsRepository.findById(id);
        if(optionalSeat.isEmpty()){
            throw new DataNotFoundException("Seat with id "+ id +"is Not Available");
        }
        this.seatsRepository.delete(optionalSeat.get());
    }

    public Seats getSeatById(Integer id) throws DataNotFoundException {
        Optional<Seats> optionalSeat = seatsRepository.findById(id);
        if(optionalSeat.isEmpty()){
            throw new DataNotFoundException("Seat with id "+ id +"is Not Available");
        }
        return optionalSeat.get();
    }

    public List<Seats> getSeatsAvailable(Boolean isAvailable){

        List<Seats> seatsAvailable = this.seatsRepository.getSeatsAvailable(isAvailable);
        return seatsAvailable;
    }

    public Seats getSeatById(Seats newseats) {
        return newseats;
    }

    public void deleteSeatById(Seats seat) {
    }
}
