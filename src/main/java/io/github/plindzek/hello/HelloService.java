package io.github.plindzek.hello;

import io.github.plindzek.lang.Lang;
import io.github.plindzek.lang.LangRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
class HelloService {
    /**
     * default value, when we dont receive "name" in request and we have null
     */
    static final String FALLBACK_NAME = "add \"?name=your name\" at the end of this site address";
    /**
     * default values, when we dont receive "lang" in request and we have null
     */
    static final Lang FALLBACK_LANG = new Lang(1, "Hello", "pl", "Name of car: ",
	    "Spalanie LPG w litrach na 100km: ", "Cena LPG: ", "Ilość przejechanych kilometrów na LPG: ",
	    "Spalanie PB95 w litrach na 100 km: ", "Cena PB95: ", "ilość przejechanych kilometrów na PB95: ",
	    "Koszt trasy wyniesie: ", "Oblicz", "Wyjście", "Zapisz profil", "Wczytaj profil");

    private LangRepository repository;

    HelloService(LangRepository repository) {
	this.repository = repository;
    }

    String prepareGreeting(String name, Integer langId) {
        langId=Optional.ofNullable(langId).orElse(FALLBACK_LANG.getLangId());
	var welcomeMsg = repository.findById(langId).orElse(FALLBACK_LANG).getWelcomeMsg();
	var nameToWelcome =  Optional.ofNullable(name).orElse(FALLBACK_NAME);

	return welcomeMsg + "<br />" + nameToWelcome + "!";
    }
 }
