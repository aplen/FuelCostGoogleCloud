package io.github.plindzek.lang;

/*
@author Adam
DTO class created because we dont need send all data from Langs entity
when GET request is execute
 */
class LangDTO {
    private Integer langId;
    private String langCode;


    LangDTO(Lang lang) {
        this.langCode = lang.getLangCode();
        this.langId = lang.getLangId();
    }

    public Integer getLangId() {
        return langId;
    }

    public void setLangId(Integer langId) {
        this.langId = this.langId;
    }

    public String getLangCode() {
        return langCode;
    }

    public void setLangCode(String langcode) {
        this.langCode = langCode;
    }
}
