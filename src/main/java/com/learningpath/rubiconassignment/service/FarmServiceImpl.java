package com.learningpath.rubiconassignment.service;

import com.learningpath.rubiconassignment.model.Farm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.learningpath.rubiconassignment.repoes.FarmRepository;

@Service
public class FarmServiceImpl implements FarmService {
    @Autowired
    private FarmRepository farmRepository;

    @Override
    public List<Farm> getAllFarms() {
        return farmRepository.findAll();
    }
}
