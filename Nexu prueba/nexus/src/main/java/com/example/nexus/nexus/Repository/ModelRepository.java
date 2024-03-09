package com.example.nexus.nexus.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.nexus.nexus.Entity.Brand;
import com.example.nexus.nexus.Entity.Model;


@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {
    List<Model> findByBrand(Brand brand);

    boolean existsByNameAndBrand(String name, Brand brand);

    List<Model> findByAveragePriceBetween(Long menor, Long mayor);

}
