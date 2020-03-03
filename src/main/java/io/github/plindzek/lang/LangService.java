package io.github.plindzek.lang;

import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class LangService {

    private LangRepository repository;

    LangService(LangRepository repository) {
        this.repository = repository;
    }

    public List<LangDTO> findAll() {
        return repository
                .findAll()
                .stream()
                .map(LangDTO::new)
                .collect(toList());
    }


}


