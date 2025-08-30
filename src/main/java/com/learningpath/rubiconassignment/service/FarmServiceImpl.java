package com.learningpath.rubiconassignment.service;

import com.learningpath.rubiconassignment.model.Farm;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class FarmServiceImpl implements FarmService {
    @Override
    public List<Farm> getAllFarms() {
        // TODO: Replace with actual DB/repository logic
        return new ArrayList<>();
    }
}
