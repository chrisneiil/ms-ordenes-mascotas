package com.duoc.msordenesmascotas.repository;

import com.duoc.msordenesmascotas.model.OrdenCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdenRepository extends JpaRepository<OrdenCompra, Long> {

    List<OrdenCompra> findByEstadoIgnoreCase(String estado);
}