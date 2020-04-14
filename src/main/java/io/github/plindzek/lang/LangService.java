package io.github.plindzek.lang;

import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
class LangService {

    private LangRepository repository;

    LangService(LangRepository repository) {
        this.repository = repository;
    }

    /*
     *returns langId and langCode from Lang
     */
    List<LangDTO> findAll() {
        return repository
                .findAll()
                .stream()
                .map(LangDTO::new)
                .collect(toList());
    }


}


