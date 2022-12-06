package org.example.repositories;

import org.example.entities.Zadanie;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ZadanieRepository
 extends CrudRepository<Zadanie, Long>{
 List<Zadanie> findByWykonane(boolean wykonane);

 List<Zadanie> findByKosztLessThan(double koszt);

 List<Zadanie> findByKosztBetween(double dolny, double gorny);
 }