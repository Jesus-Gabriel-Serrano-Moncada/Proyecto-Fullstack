package com.example.Pagos.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Pagos.model.Pago;

public interface PagoRepository extends JpaRepository<Pago, Long> {
    List<Pago>findByEstado(String estado);
}
