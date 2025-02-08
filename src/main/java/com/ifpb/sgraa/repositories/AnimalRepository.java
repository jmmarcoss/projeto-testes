package com.ifpb.sgraa.repositories;

import com.ifpb.sgraa.models.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal, Long> {}

