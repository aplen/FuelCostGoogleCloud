package io.github.plindzek.lang;

import io.github.plindzek.appUser.AppUserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
class LangService {

    static final String FALLBACK_NAME = "kitty";
    static final Lang FALLBACK_LANG = new Lang(2, "Hello", "en", "Name of car: ", "LPG l/100km", "LPG price: ", "km on LPG: ", "pb l/100km", "PB95 price: ", "km on PB95: ", "Trip cost: ", "calculate", "exit", "save", "use this car");

    private LangRepository langRepository;
    private AppUserService appUserService;

    LangService(LangRepository langRepository, AppUserService appUserService) {
        this.langRepository = langRepository;
        this.appUserService = appUserService;
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
        var loggedUser = appUserService.getLoggedUserName();
        langId = Optional.ofNullable(langId).orElse(appUserService.findByName(loggedUser).getLangId());

        var nameToWelcome = Optional.ofNullable(loggedUser).orElse(FALLBACK_NAME);
        var welcomeMsg = langRepository.findById(langId).orElse(FALLBACK_LANG).getWelcomeMsg();
        return welcomeMsg + "<br />" + nameToWelcome + "!";
    }

    Lang findById(Integer langId) {
        return langRepository.findById(langId).orElse(FALLBACK_LANG);
    }
}


