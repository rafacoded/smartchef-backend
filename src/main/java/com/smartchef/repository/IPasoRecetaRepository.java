package com.smartchef.repository;

import com.smartchef.model.PasoReceta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPasoRecetaRepository extends JpaRepository<PasoReceta, Long> {

    List<PasoReceta> findByRecetaIdRecetaOrderByOrdenAsc(Long idReceta);
}

