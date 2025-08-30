package com.learningpath.rubiconassignment.repoes;

import com.learningpath.rubiconassignment.model.Farm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FarmRepository extends JpaRepository<Farm, Long> {
}
