package com.graysan.springrest.controller;

import com.graysan.springrest.model.Ogretmen;
import com.graysan.springrest.repository.OgretmenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping(path = "/ogretmen")
public class OgretmenController {
    private final OgretmenRepository ogretmenRepository;

    public OgretmenController(OgretmenRepository ogretmenRepository) {
        this.ogretmenRepository = ogretmenRepository;
    }

    @GetMapping(path = "/getall", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Ogretmen>> getAll() {
        // localhost:8080/springrest/ogretmmen/getall
        try
        {
            List<Ogretmen> temp = ogretmenRepository.getAll();
            return ResponseEntity.ok(temp);
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(path = "/getbyid/{id}")
    public ResponseEntity<Ogretmen> getById(@PathVariable(name = "id") long id)
    {
        // localhost:8080/springrest/ogretmmen/getbyid/1
        try
        {
            Ogretmen ogr = ogretmenRepository.getByID(id);
            return ResponseEntity.ok(ogr);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping(path = "/delete/{id}", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> deleteById(@PathVariable(name = "id") long id)
    {
        // localhost:8080/springrest/ogretmmen/delete/1
        try
        {
            boolean result = ogretmenRepository.deleteByID(id);
            if(result){
                return ResponseEntity.ok(id + "nolu kayıt başarı ile silindi.");
            }else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(id + " nolu kayıt bulunamadı");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(id + " nolu kayıt silinemedi");
        }
    }

    @PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> save(@RequestBody Ogretmen ogr){
        // localhost:8080/springrest/ogretmmen/save
        try
        {
            boolean result = ogretmenRepository.save(ogr);
            if(result){
                return ResponseEntity.ok("Kayıt başarı ile kaydedildi.");
            }else {
                return ResponseEntity.internalServerError().body("Kayıt başarı ile kaydedilemedi server error");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Kayıt başarı ile kaydedilemedi");
        }
    }

}
