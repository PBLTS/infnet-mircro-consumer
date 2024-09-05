package com.infnet.spring.jpa.h2.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.infnet.spring.jpa.h2.component.VendaConsumer;
import com.infnet.spring.jpa.h2.model.Vendas;
import com.infnet.spring.jpa.h2.repository.VendaRepository;


@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class VendaController {



  @Autowired
  VendaRepository vendaRepository;

  @GetMapping("/venda")
  public ResponseEntity<List<Vendas>> getAll(@RequestParam(required = false) String title) {
    try {
      List<Vendas> vendas = new ArrayList<Vendas>();

      if (title == null)
      vendaRepository.findAll().forEach(vendas::add);
      else
      vendaRepository.findByDescriptionContainingIgnoreCase(title).forEach(vendas::add);

      if (vendas.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(vendas, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  



}
