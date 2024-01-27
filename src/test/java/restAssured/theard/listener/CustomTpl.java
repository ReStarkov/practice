package restAssured.theard.listener;

import io.qameta.allure.restassured.AllureRestAssured;

public class CustomTpl {
    //для красивого логирования в отчетах
    private static final AllureRestAssured FILTER = new AllureRestAssured();

    private CustomTpl(){

    }
    public AllureRestAssured withCustomTemplates(){
        FILTER.setRequestTemplate("http-request.ftl");
        FILTER.setResponseTemplate("http-response.ftl");
        return FILTER;
    }

    private static class  InitLogFilter{
        private static final CustomTpl logFilter = new CustomTpl();
    }

    public static CustomTpl customFilter(){
        return InitLogFilter.logFilter;
    }

}
