package io.github.plindzek.hello;

import io.github.plindzek.lang.Lang;
import io.github.plindzek.lang.LangRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
class HelloService {

    static final String FALLBACK_NAME = "kitty";
    static final Lang FALLBACK_LANG = new Lang(1,"Default Welcome","pl","Name of car: ","LPG l/100km","LPG price: ","km on LPG: ","pb l/100km","PB95 price: ","km on PB95: ","Trip cost: ","calculate","exit","save","use this car");

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
