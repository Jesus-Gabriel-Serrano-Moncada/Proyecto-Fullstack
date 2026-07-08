package com.example.Envios.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Envios.model.Envio;

@Repository
public interface EnvioRepository extends JpaRepository<Envio, Long> {

    List<Envio> findByOrdenId(Long ordenId);

    List<Envio> findByEstado(String estado);

}