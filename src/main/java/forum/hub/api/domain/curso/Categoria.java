package forum.hub.api.domain.curso;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Categoria {
    DESENVOLVIMENTO("Desenvolvimento"),
    FRONTEND("Frontend"),
    BACKEND("Backend"),
    MARKETING("Marketing"),
    INOVACAO("Inovação"),
    GESTAO("Gestão"),
    UX_UI("UX/UI");

    private final String nomePortugues;

    Categoria(String nomePortugues) {
        this.nomePortugues = nomePortugues;
    }

    @JsonValue
    public String getNomePortugues() {
        return nomePortugues;
    }
}
