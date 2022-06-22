package com.teama.bioskop.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalTime;
import java.util.Date;

@AllArgsConstructor //anotasi ini digunakan untuk generate constructor
@NoArgsConstructor //anotasi ini digunakan untuk generate constructor kosongan
@Getter //sebuah aksi saat kita mengambil sebuah nilai/values dari suatu variable/object.
@Setter //sebuah aksi saat kita memasukan sebuah nilai/values kedalam suatu variable/object
@Entity //annotasi yang mendifinisikan class sebagai tabel didatabase
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer scheduleId;

    private Integer filmCode;

    private Date tanggalTayang;

    private LocalTime jamMulai;

    private LocalTime jamSelesai;

    private  Double hargaTiket;


}
