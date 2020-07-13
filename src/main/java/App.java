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

        //get: show all listed sightings and both endangered and non endangered animals
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

        //get: show form to create a new animal
        get("/animals/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Animal> animals = Animal.all();
            model.put("animals", animals);
            List<EndangeredAnimal> endangeredAnimals = EndangeredAnimal.allEndangered();
            model.put("endangeredAnimals", endangeredAnimals);
            return new ModelAndView(model, "Animal-form.hbs");
        }, new HandlebarsTemplateEngine() );

        //post: process a form to create new animal
        post("/animals", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String animalName = req.queryParams("name");
            Animal newAnimal = new Animal(animalName);
            newAnimal.save();
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine() );

        //get: show form to create new endangered animal
        get("/endangered/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Animal> animals = Animal.all();
            model.put("animals", animals);
            List<EndangeredAnimal> endangeredAnimals = EndangeredAnimal.allEndangered();
            model.put("endangeredAnimals", endangeredAnimals);
            return new ModelAndView(model, "Endangered-form.hbs");
        }, new HandlebarsTemplateEngine() );

        //post: process a form to create new endangered animal
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

        //get: delete all animals and endangered animals and sightings
        get("/animals/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            Animal.clearAll();
            EndangeredAnimal.clearAll();
            Sighting.clearAll();
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine() );

        //get: delete individual animal
        get("/animals/:animalId/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfAnimal = Integer.parseInt(req.queryParams("animalId"));
            Animal.deleteById(idOfAnimal);
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine() );

        //get: delete individual endangered animal
        get("/endangered/:animalId/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfAnimal = Integer.parseInt(req.queryParams("animalId"));
            EndangeredAnimal.deleteById(idOfAnimal);
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine() );

        //get: delete all sightings
        get("/sightings/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            Sighting.clearAll();
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine() );

        //get an animal's details together with sightings reported
        get("/animals/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfAnimal = Integer.parseInt(req.queryParams("id"));
            Animal foundAnimal = Animal.findById(idOfAnimal);
            model.put("animal", foundAnimal);
            List<Sighting> animalSightings = foundAnimal.findSightings();
            model.put("sightings", animalSightings);
            List<Animal> animals = Animal.all();
            model.put("animals", animals);
            List<EndangeredAnimal> endangeredAnimals = EndangeredAnimal.allEndangered();
            model.put("endangeredAnimals", endangeredAnimals);
            return new ModelAndView(model, "Animal-details.hbs");
        }, new HandlebarsTemplateEngine() );

        //get endangered animal's details together with sightings reported
        get("/endangered/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfAnimal = Integer.parseInt(req.queryParams("id"));
            EndangeredAnimal foundAnimal = EndangeredAnimal.findById(idOfAnimal);
            model.put("animal", foundAnimal);
            List<Sighting> animalSightings = foundAnimal.findSightings();
            model.put("sightings", animalSightings);
            List<Animal> animals = Animal.all();
            model.put("animals", animals);
            List<EndangeredAnimal> endangeredAnimals = EndangeredAnimal.allEndangered();
            model.put("endangeredAnimals", endangeredAnimals);
            return new ModelAndView(model, "Endangered-details.hbs");
        }, new HandlebarsTemplateEngine() );

        //get: show a form to update animal
        get("/animals/:id/edit", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfAnimal = Integer.parseInt(req.queryParams("id"));
            Animal foundAnimal = Animal.findById(idOfAnimal);
            model.put("editAnimal", foundAnimal);
            List<Animal> animals = Animal.all();
            model.put("animals", animals);
            List<EndangeredAnimal> endangeredAnimals = EndangeredAnimal.allEndangered();
            model.put("endangeredAnimals", endangeredAnimals);
            return new ModelAndView(model, "Animal-form.hbs");
        }, new HandlebarsTemplateEngine() );

        //post: process form to update animal
        post("/animals/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String animalName = req.queryParams("name");
            int idOfAnimal = Integer.parseInt(req.queryParams("id"));
            Animal foundAnimal = Animal.findById(idOfAnimal);
            foundAnimal.update(animalName);
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine() );

        //get: show a form to update endangered animal
        get("/endangered/:id/edit", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfAnimal = Integer.parseInt(req.queryParams("id"));
            EndangeredAnimal foundAnimal = EndangeredAnimal.findById(idOfAnimal);
            model.put("editAnimal", foundAnimal);
            List<Animal> animals = Animal.all();
            model.put("animals", animals);
            List<EndangeredAnimal> endangeredAnimals = EndangeredAnimal.allEndangered();
            model.put("endangeredAnimals", endangeredAnimals);
            return new ModelAndView(model, "Endangered-form.hbs");
        }, new HandlebarsTemplateEngine() );

        //post: process form to update endangered animal
        post("/endangered/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String animalName = req.queryParams("name");
            String animalHealth = req.queryParams("health");
            String animalAge = req.queryParams("age");
            int idOfAnimal = Integer.parseInt(req.queryParams("id"));
            EndangeredAnimal foundAnimal = EndangeredAnimal.findById(idOfAnimal);
            foundAnimal.update(animalName, animalHealth, animalAge);
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine() );

        //get: delete individual sighting
        get("/animals/:animalId/sightings/id/delete", (req, res) -> {
            int idOfSighting = Integer.parseInt(req.queryParams("id"));
            Sighting sightingToDelete = Sighting.findById(idOfSighting);
            Sighting.deleteById(idOfSighting);
            res.redirect("/");
            return null;
        },new HandlebarsTemplateEngine() );

        //get: show a form to record new animal sighting
        get("/animals/sighting/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Animal> animals = Animal.all();
            model.put("animals", animals);
            List<EndangeredAnimal> endangeredAnimals = EndangeredAnimal.allEndangered();
            model.put("endangeredAnimals", endangeredAnimals);
            return new ModelAndView(model, "Animal-sighting-form.hbs");
        }, new HandlebarsTemplateEngine() );

        //post: process a form to record new sighting
        post("/animals/sighting", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String rangerName = req.queryParams("rangerName");
            String location = req.queryParams("location");
            int idOfAnimal = Integer.parseInt(req.queryParams("animalId"));
            Sighting newSighting = new Sighting(location, rangerName, idOfAnimal);
            newSighting.save();
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine() );

        //get: show a form to record new animal sighting
        get("/endangered/sighting/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Animal> animals = Animal.all();
            model.put("animals", animals);
            List<EndangeredAnimal> endangeredAnimals = EndangeredAnimal.allEndangered();
            model.put("endangeredAnimals", endangeredAnimals);
            return new ModelAndView(model, "Endangered-sighting-form.hbs");
        }, new HandlebarsTemplateEngine() );

        //post: process a form to record new sighting
        post("/endangered/sighting", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String rangerName = req.queryParams("rangerName");
            String location = req.queryParams("location");
            int idOfAnimal = Integer.parseInt(req.queryParams("animalId"));
            Sighting newSighting = new Sighting(location, rangerName, idOfAnimal);
            newSighting.save();
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine() );

        //get individual sighting with its details

        //get: show form to update a sighting

        //post: process form to update a sighting
    }
}