import models.*;
import spark.ModelAndView;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App{
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
    public static void main(String[] args) {
        port(getHerokuAssignedPort());
        staticFileLocation("/public");

        //show all listed sightings and both endangered and non endangered animals
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Animal> animals = Animal.all();
            model.put("animals", animals);
            List<EndangeredAnimal> endangeredAnimals = EndangeredAnimal.allEndangered();
            model.put("endangeredAnimals", endangeredAnimals);
            List<Sighting> sightings = Sighting.all();
            model.put("sightings", sightings);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine() );

        //show form to create a new animal
        get("/animals/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "Animal-form.hbs");
        }, new HandlebarsTemplateEngine() );

        //process a form to create new animal
        post("/animals", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String animalName = req.queryParams("name");
            Animal newAnimal = new Animal(animalName);
            newAnimal.save();
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine() );

        //show form to create new endangered animal
        get("/endangered/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "Endangered-form.hbs");
        }, new HandlebarsTemplateEngine() );

        //process a form to create new endangered animal
        post("/endangered", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String endangeredName = req.queryParams("name");
            String health = req.queryParams("health");
            String age = req.queryParams("age");
            EndangeredAnimal newEndangeredAnimal = new EndangeredAnimal(endangeredName, health, age);
            newEndangeredAnimal.save();
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine() );

        //delete all animals and endangered animals and sightings
        get("/animals/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            Animal.clearAll();
            EndangeredAnimal.clearAll();
            Sighting.clearAll();
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine() );

        //delete individual animal
        get("/animals/:animalId/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfAnimal = Integer.parseInt(req.queryParams("animalId"));
            Animal.deleteById(idOfAnimal);
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine() );

        //delete individual endangered animal
        get("/endangered/:animalId/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfAnimal = Integer.parseInt(req.queryParams("animalId"));
            EndangeredAnimal.deleteById(idOfAnimal);
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine() );

        //delete all sightings
        get("/sightings/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            Sighting.clearAll();
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine() );
    }
}