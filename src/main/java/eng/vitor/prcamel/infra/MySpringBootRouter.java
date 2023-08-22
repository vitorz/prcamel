package eng.vitor.prcamel.infra;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

import eng.vitor.prcamel.model.entities.Point;

@Component
public class MySpringBootRouter extends RouteBuilder {

    @Override
    public void configure() {
        restConfiguration()
                .port(8080)
                .enableCORS(true)
                .apiContextPath("/api-doc")
                .apiProperty("api.title", "Test REST API")
                .apiProperty("api.version", "v1")
                .component("servlet")
                .bindingMode(RestBindingMode.json);
        rest("/lines")
                .get("/space").consumes("application/json")
                .to("bean:spaceService?method=space")
                .get("/lines/{n}").consumes("application/json")
                .to("bean:spaceService?method=lines(${header.n})")
                .post("/point").type(Point.class).to("bean:spaceService?method=point(${body})")
                .delete("/space").to("bean:spaceService?method=delete");
    }

}
