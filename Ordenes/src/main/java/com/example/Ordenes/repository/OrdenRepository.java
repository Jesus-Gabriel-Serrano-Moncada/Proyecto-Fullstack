package com.example.Ordenes.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Ordenes.model.Ordenes;


public interface OrdenRepository extends JpaRepository <Ordenes, Long>{
    List<Ordenes> findByUsuarioId(String usuarioId);    
}
