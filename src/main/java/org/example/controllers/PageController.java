package org.example.controllers;

import org.example.entities.Zadanie;
import org.example.repositories.ZadanieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class PageController {

    @Autowired
    public ZadanieRepository rep;


    @RequestMapping("/")
    @ResponseBody
    public String mainPage() {
        return "Hello Spring Boot from mainPage() method!";
    }

    @RequestMapping("/hello")
    @ResponseBody
    public String pageTwo() {
        return "Hello Spring Boot from pageTwo() method!";
    }


    @RequestMapping("/listaZadan")
    @ResponseBody
    public String listaZadan() {
        StringBuilder odp = new StringBuilder();
        Zadanie zadanie = new Zadanie();
        //korzystając z obiektu repozytorium zapisujemy zadanie do bazy
        rep.save(zadanie);
        //korzystając z repozytorium pobieramy wszystkie zadania z bazy
        for (Zadanie i : rep.findAll()) {
            odp.append(i).append("<br>");
        }
        return odp.toString();
    }

    @RequestMapping("/data")
    @ResponseBody
    public String data(){

        Zadanie z;
        double k=1000;
        boolean wyk=false;
        for (int i=1;i<=10;i++){
            z = new Zadanie();
            z.setNazwa("zadanie "+i);
            z.setOpis("Opis czynnosci do wykonania w zadaniu "+i);
            z.setKoszt(k);
            z.setWykonane(wyk);
            wyk=!wyk;
            k+=200.50;
            rep.save(z);
        }
        return listaZadan();
    }

    @RequestMapping("/delete/{id}")
    @ResponseBody
    public String delete(@PathVariable Long id) {
        rep.deleteById(id);
        return listaZadan();
    }

    @RequestMapping("/findByWykonane/{wykonane}")
    @ResponseBody
    public String fetchByWykonane(@PathVariable boolean wykonane) {
        StringBuilder odp = new StringBuilder();
        for (Zadanie i : rep.findByWykonane(wykonane)) {
            odp.append(i).append("<br>");
        }
        return odp.toString();
    }

    @RequestMapping("/findhByKosztLessThan/{koszt}")
    @ResponseBody
    public String fetchByKosztLessThan(@PathVariable double koszt) {
        StringBuilder odp = new StringBuilder();
        for (Zadanie i : rep.findByKosztLessThan(koszt)) {
            odp.append(i).append("<br>");
        }
        return odp.toString();
    }

    @RequestMapping("/findByKosztBetween/{min}/{max}")
    @ResponseBody
    public String fetchByKosztBetween(@PathVariable double min,@PathVariable double max) {
        StringBuilder odp = new StringBuilder();
        for (Zadanie i : rep.findByKosztBetween(min,max)) {
            odp.append(i).append("<br>");
        }
        return odp.toString();
    }
} 