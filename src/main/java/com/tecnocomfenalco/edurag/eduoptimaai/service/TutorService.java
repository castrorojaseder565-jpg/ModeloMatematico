package com.tecnocomfenalco.edurag.eduoptimaai.service;

import com.tecnocomfenalco.edurag.eduoptimaai.model.Tutor;
import com.tecnocomfenalco.edurag.eduoptimaai.repository.TutorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TutorService {

    private final TutorRepository repository;

    public TutorService(TutorRepository repository) {
        this.repository = repository;
    }

    public List<Tutor> findAll() { return repository.findAll(); }
    public Optional<Tutor> findById(Long id) { return repository.findById(id); }
    public Tutor save(Tutor tutor) { return repository.save(tutor); }
    public void deleteById(Long id) { repository.deleteById(id); }
}
