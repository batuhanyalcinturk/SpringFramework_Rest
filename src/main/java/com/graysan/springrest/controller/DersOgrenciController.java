package com.graysan.springrest.controller;

import com.graysan.springrest.model.DersOgrenci;
import com.graysan.springrest.repository.DersOgrenciRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/dersogrenci")
public class DersOgrenciController {

    private final DersOgrenciRepository dersOgrenciRepository;


    public DersOgrenciController(DersOgrenciRepository dersOgrenciRepository) {
        this.dersOgrenciRepository = dersOgrenciRepository;
    }

    @GetMapping(path = "/getall", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DersOgrenci>> getAll() {
        // localhost:8080/springrest/dersogrenci/getall
        try
        {
            List<DersOgrenci> temp = dersOgrenciRepository.getAll();
            return ResponseEntity.ok(temp);
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(path = "/getbyid/{id}")
    public ResponseEntity<DersOgrenci> getById(@PathVariable(name = "id") long id)
    {
        // localhost:8080/springrest/dersogrenci/getbyid/1
        try
        {
            DersOgrenci dersOgrenci = dersOgrenciRepository.getByID(id);
            return ResponseEntity.ok(dersOgrenci);
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
            boolean result = dersOgrenciRepository.deleteByID(id);
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
    public ResponseEntity<String> save(@RequestBody DersOgrenci dersOgrenci){
        // localhost:8080/springrest/dersogrenci/save
        try
        {
            boolean result = dersOgrenciRepository.save(dersOgrenci);
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
