package io.github.plindzek.lang;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
class LangService {

    static final String FALLBACK_NAME = "kitty";
    static final Lang FALLBACK_LANG = new Lang(2, "Hello", "en", "Name of car: ", "LPG l/100km", "LPG price: ", "km on LPG: ", "pb l/100km", "PB95 price: ", "km on PB95: ", "Trip cost: ", "calculate", "exit", "save", "use this car");

    private LangRepository langRepository;

    LangService(LangRepository langRepository) {
        this.langRepository = langRepository;
    }

    /*
     *returns langId and langCode from Lang
     */
    List<LangDTO> findAll() {
        return langRepository
                .findAll()
                .stream()
                .map(LangDTO::new)
                .collect(toList());
    }

    String prepareLogin(Integer langId) {
        langId = Optional.ofNullable(langId).orElse(FALLBACK_LANG.getLangId());
        var welcomeMsg = langRepository.findById(langId).orElse(FALLBACK_LANG).getWelcomeMsg();
//        var nameToWelcome = Optional.ofNullable(appUser.getUsername()).orElse(FALLBACK_NAME);
        return welcomeMsg + "<br />" + FALLBACK_NAME + "!";
    }

}


