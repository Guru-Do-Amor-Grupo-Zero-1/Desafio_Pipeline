package br.com.gurudoamor.projeto.enums;

public enum Mensagem {
    AR_AR("Ar", "Ar",
            "Quando dois signos de Ar se encontram, a troca de ideias e a leveza do pensamento transformam essa conexão em um amor vibrante e inspirador.",
            "A afinidade entre os signos de Ar pode se traduzir em uma amizade repleta de conversas e compartilhamento de ideias, sem o calor do amor romântico."),

    TERRA_TERRA("Terra", "Terra",
            "A união dos signos de Terra cria um amor sólido e seguro, onde a praticidade e o real se unem em perfeita harmonia.",
            "Mesmo com uma base prática em comum, a conexão entre os signos de Terra pode se manifestar como uma amizade estável, sem a paixão intensa do amor."),

    FOGO_FOGO("Fogo", "Fogo",
            "Quando dois signos de Fogo se encontram, a paixão e a energia se intensificam, dando origem a um amor ardente e vibrante.",
            "A energia contagiante dos signos de Fogo pode se transformar numa amizade animada, onde a chama da paixão se mantém apenas como uma sintonia leve."),

    AGUA_AGUA("Água", "Água",
            "A profunda sensibilidade dos signos de Água permite que a conexão se transforme em um amor intenso e intuitivo, onde as emoções se encontram de forma sublime.",
            "Mesmo com uma forte sintonia emocional, a relação entre signos de Água pode se configurar como uma amizade íntima e compreensiva, sem a intensidade do amor romântico."),

    AR_FOGO("Ar", "Fogo",
            "Quando o intelecto do Ar se une à paixão do Fogo, a combinação gera um amor dinâmico, onde as ideias se transformam em ações cheias de entusiasmo.",
            "A química entre o pensamento livre do Ar e a energia impulsiva do Fogo pode resultar em uma amizade vibrante, sem a profundidade de um amor apaixonado."),

    TERRA_AGUA("Terra", "Água",
            "A fusão da estabilidade da Terra com a sensibilidade da Água cria um amor equilibrado, onde a razão se une à emoção em perfeita sintonia.",
            "Embora os signos de Terra e Água compartilhem afinidades, a relação pode se manifestar como uma amizade sólida, marcada pelo apoio mútuo e compreensão."),

    AR_TERRA("Ar", "Terra",
            "A mistura do pensamento livre do Ar com a determinação da Terra pode dar origem a um amor surpreendente, onde os sonhos se unem à realidade com maestria.",
            "A diferença entre o universo das ideias e a necessidade de solidez prática pode se traduzir numa amizade baseada no respeito e na troca enriquecedora, sem o fogo do amor."),

    FOGO_AGUA("Fogo", "Água",
            "A união da paixão do Fogo com a intuição da Água revela um amor arrebatador, onde energia e sensibilidade se complementam de forma surpreendente.",
            "Apesar da atração natural entre o Fogo e a Água, as diferenças podem manter a relação num patamar de amizade intensa e empática, sem a intensidade do amor."),

    AR_AGUA("Ar", "Água",
            "Quando o Ar e a Água se encontram, a combinação do raciocínio com a emoção resulta num amor profundo, onde ambos os universos se harmonizam.",
            "A tensão entre a lógica do Ar e a sensibilidade da Água pode favorecer uma amizade sincera, onde o apoio mútuo se destaca, mesmo sem a paixão romântica."),

    FOGO_TERRA("Fogo", "Terra",
            "A combinação do dinamismo do Fogo com a solidez da Terra pode originar um amor apaixonado, onde a energia se equilibra com uma base firme e real.",
            "Mesmo com a atração dos opostos, a interação entre o Fogo e a Terra pode resultar em uma amizade duradoura, onde a praticidade e o entusiasmo coexistem de forma harmônica.");

    private final String elemento1;
    private final String elemento2;
    private final String mensagemAmor;
    private final String mensagemAmizade;

    Mensagem(String elemento1, String elemento2, String mensagemAmor, String mensagemAmizade) {
        this.elemento1 = elemento1;
        this.elemento2 = elemento2;
        this.mensagemAmor = mensagemAmor;
        this.mensagemAmizade = mensagemAmizade;
    }

    public static String getMensagem(String elemento1, String elemento2, boolean isAmor) {
        for (Mensagem msg : values()) {
            // Verifica a combinação em qualquer ordem (ex: "Fogo_Água" == "Água_Fogo")
            if ((msg.elemento1.equals(elemento1) && msg.elemento2.equals(elemento2)) ||
                    (msg.elemento1.equals(elemento2) && msg.elemento2.equals(elemento1))) {
                return isAmor ? msg.mensagemAmor : msg.mensagemAmizade;
            }
        }
        return "Não foi possível determinar a compatibilidade.";
    }
}
