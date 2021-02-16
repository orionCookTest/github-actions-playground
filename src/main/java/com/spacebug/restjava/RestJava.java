package com.spacebug.restjava;

import com.spacebug.restjava.health.RestJavaHealthCheck;
import com.spacebug.restjava.resource.HelloWorldResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;
import io.swagger.v3.oas.integration.SwaggerConfiguration;
import io.swagger.v3.oas.models.OpenAPI;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.representer.Representer;

import java.io.InputStream;
import java.util.Arrays;
import java.util.HashSet;

public class RestJava extends Application<RestJavaConfiguration> {

    private static HashSet<String> packages = new HashSet<>();

    public static void main(String[] args) throws Exception {
        new RestJava().run(args);
    }

    @Override
    public void run(RestJavaConfiguration configuration, Environment environment) throws Exception {
        setupOpenApiPackages();
        registerResources(environment);
        registerHealthChecks(environment);
        configureOpenApi(environment);
    }

    private static void setupOpenApiPackages() {
        packages.add("com.spacebug.restjava.resource");
    }

    private void registerResources(Environment environment) {
        environment.jersey().register(HelloWorldResource.class);
    }

    private void registerHealthChecks(Environment environment) {
        environment.healthChecks().register("restjava", new RestJavaHealthCheck());
    }

    public static void configureOpenApi(Environment environment) {

        OpenAPI oas = loadOpenApi();
        SwaggerConfiguration oasConfig = new SwaggerConfiguration()
            .openAPI(oas)
            .prettyPrint(true)
            .ignoredRoutes(Arrays.asList("/swagger", "/swagger.{type}", "/health-check", "/mock"))
            .resourcePackages(packages);
        environment.jersey().register(new OpenApiResource().openApiConfiguration(oasConfig));
    }

    private static OpenAPI loadOpenApi() {

        Representer representer = new Representer();
        representer.getPropertyUtils().setSkipMissingProperties(true);
        Yaml yaml = new Yaml(representer);
        OpenAPI openAPI = null;

        try (InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("OpenAPI.yaml")) {
            openAPI = yaml.loadAs(in, OpenAPI.class);
        } catch (Exception e) {
            System.err.println("Could not load OpenAPI details into java object" + e.toString());
        }
        return openAPI;
    }
}
