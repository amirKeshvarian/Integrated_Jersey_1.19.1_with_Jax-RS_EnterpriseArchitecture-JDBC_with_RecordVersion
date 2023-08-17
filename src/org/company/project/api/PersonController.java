package org.company.project.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.log4j.Logger;
import org.company.project.common.exception.ValidationException;
import org.company.project.common.json.JSON;
import org.company.project.common.wrapper.ErrorHandler;
import org.company.project.model.entity.Person;
import org.company.project.model.service.PersonService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

//MVC API-BASE MULTI ACTION SUPPORT
@Path("/person")
public class PersonController {
    private static final Logger LOGGER = Logger.getLogger(PersonController.class);

    @Path("/save.do")
    @GET//@POST
    @Produces("application/json")
    public String save(@QueryParam("name") String name,
                       @QueryParam("family") String family,
                       @QueryParam("salary") String salary) {
        try {
            if(name == null || family == null || name.isEmpty() || family.isEmpty())
                throw new ValidationException();
            Person person = new Person()
                    .setName(name)
                    .setFamily(family)
                    .setSalary(Long.parseLong(salary));
            return JSON.getJson(PersonService.getInstance().save(person).findAll());
        } catch (Exception e) {
            try {
                return JSON.getJson(ErrorHandler.getError(e));
            } catch (JsonProcessingException ex) {
                LOGGER.error(ex.getStackTrace());
                return null;
            }
        }
    }
    @Path("/asyncSave.do")
    @GET//@POST
    @Produces("application/json")
    public String asyncSave (@QueryParam("name") String name,
                             @QueryParam("family") String family,
                             @QueryParam("salary") String salary){

        try {
            return JSON.getJson(PersonService.getInstance().asyncSave(new Person()
                    .setName(name)
                    .setFamily(family)
                    .setSalary(Long.parseLong(salary))).findAll());
        } catch (Exception e) {
            try {
                return JSON.getJson(ErrorHandler.getError(e));
            } catch (JsonProcessingException ex) {
                LOGGER.error(ex.getStackTrace());
                return null;
            }
        }
    }

    @Path("/change.do")
    @GET//@POST
    @Produces("application/json")
    public String change(@QueryParam("id") String personId,
                         @QueryParam("name") String name,
                         @QueryParam("family") String family,
                         @QueryParam("salary") String salary,
                         @QueryParam("recordversion") String recordVersion) {

        try {
            Person person = new Person()
                    .setPersonId(Long.parseLong(personId))
                    .setName(name)
                    .setFamily(family)
                    .setSalary(Long.parseLong(salary))
                    .setRecordVersion(Long.parseLong(recordVersion));
            return JSON.getJson(PersonService.getInstance().change(person).findAll());
        } catch (Exception e) {
            try {
                return JSON.getJson(ErrorHandler.getError(e));
            } catch (JsonProcessingException ex) {
                LOGGER.error(ex.getStackTrace());
                return null;
            }
        }
    }

    @Path("/remove.do")
    @GET//@POST
    @Produces("application/json")
    public String remove(@QueryParam("id") String personId, @QueryParam("recordversion") String recordVersion) {
        try {
            return JSON.getJson(PersonService
                    .getInstance()
                    .remove(new Person()
                            .setPersonId(Long.parseLong(personId))
                            .setRecordVersion(Long.parseLong(recordVersion))).findAll());
        } catch (Exception e) {
            try {
                return JSON.getJson(ErrorHandler.getError(e));
            } catch (JsonProcessingException ex) {
                LOGGER.error(ex.getStackTrace());
                return null;
            }
        }
    }
    @Path("/findOne.do")
    @GET
    @Produces("application/json")
    public String findOne (@QueryParam("id")String personId){
        try {
            return JSON.getJson(PersonService.getInstance().findOne(new Person().setPersonId(Long.parseLong(personId))));
        } catch (Exception e) {
            try {
                return JSON.getJson(ErrorHandler.getError(e));
            } catch (JsonProcessingException ex) {
                LOGGER.error(ex.getStackTrace());
                return null;
            }
        }
    }
    @Path("/findAll.do")
    @GET
    @Produces("text/plain")
    public String findAll () {
        try {
            return JSON.getJson(PersonService.getInstance().findAll());
        } catch (Exception e) {
            try {
                return JSON.getJson(ErrorHandler.getError(e));
            } catch (JsonProcessingException ex) {
                LOGGER.error(ex.getStackTrace());
                return null;
            }
        }
    }
}
