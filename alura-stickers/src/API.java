public enum API {
    IMDB_TOP_MOVIES("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json",
            new ExtratorDeConteudoDoIMDB()),
    NASA("https://api.nasa.gov/planetary/apod?api_key=wah98P8EUNkD6aWXHomTAAzV5sF4GSfyS0PiJq7f&start_date=2022-06-12&end_date=2022-06-14",
            new ExtratorDeConteudoDaNasa()),
    LINGUAGENS("http://localhost:8080/linguagens",
            new ExtratorDeConteudoDeLinguagem());

    private String url;
    private ExtratorDeConteudo extrator;

    API(String url, ExtratorDeConteudo extrator) {
        this.url = url;
        this.extrator = extrator;
    }

    public String getUrl() {
        return url;
    }

    public ExtratorDeConteudo getExtrator() {
        return extrator;
    }

}