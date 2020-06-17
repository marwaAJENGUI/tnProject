package tn.esprit.spring.controller;

import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.sevice.impl.UserServiceImpl;
import tn.esprit.spring.entity.Recherche;
import tn.esprit.spring.entity.Subject;
import tn.esprit.spring.service.interfaces.IRechercheService;
import tn.esprit.spring.service.interfaces.ISubjectService;

@RestController
public class ForumController {

	@Autowired
	UserServiceImpl UserService;

	@Autowired
	ISubjectService subjectService;

	@Autowired
	IRechercheService rechercheService;

////////afficher sujets à la une//////////

	@GetMapping("/listsubject")
	@ResponseBody
	public List<String> getdate() {
		List<String> list = subjectService.sub();
		return list;
	}

/////////find subject by title//////////
	@GetMapping("/showSubject/{title}")
	@ResponseBody
	public Subject Subject(@PathVariable("title") String title) {
		return subjectService.listbytitle(title);
	}
/////////////////sujects adéquats au profil/////////////////////

	@GetMapping("/findinterested")
	@ResponseBody
	public Response findinterested() {

		String v = rechercheService.extractt(UserController.USERCONNECTED.getId());
		List<Subject> list = subjectService.findbyType(v);

		if (list != null && list.size() != 0) {
			return Response.status(Status.OK).entity(list).build();

		} else {
			return Response.status(Status.NOT_FOUND).entity("Do a little search ;) ").build();

		}

	}

//////////////////save my search + return subjects researched/////////////////////
	@PostMapping("/search/{type}")
	@ResponseBody
	public Response addSearch(@PathVariable("type") String type) {

		Recherche r = new Recherche(type);

		List<Subject> list = subjectService.findbyType(type);

		if (list.size() == 0) {
			return Response.status(Status.NOT_FOUND).entity("There is no subject with the type provided !").build();

		} else {

			rechercheService.addSearch(r, UserController.USERCONNECTED.getId());
			return Response.status(Status.OK).entity(list).build();
		}

	}

//////CRUD SUBJECT//////
///////list of all subjects///////////
	@GetMapping("/subjects")
	@ResponseBody
	public List<Subject> myy() {
		List<Subject> l = subjectService.myy();
		return l;
	}

	@PutMapping("/modify-subject")
	@ResponseBody
	public Subject updateUser(@RequestBody Subject s) {
		return subjectService.updateSubject(s);
	}

//////pour ADMIN/////test sujets redondants/////
	@PostMapping("/addSubject")
	@ResponseBody
	public Response addSubject(@RequestBody Subject u) {
		Subject subexists = subjectService.test(u.getType(), u.getDescription());
		if (subexists != null) {
			return Response.status(Status.NOT_FOUND).entity("There is already a subject exists with these informations")
					.build();

		} else {
			subjectService.addSubject(u);
			return Response.status(Status.OK).entity("add successful").build();
		}
	}

	@DeleteMapping("/delete-subject/{subject-id}")
	@ResponseBody
	public void deleteSubject(@PathVariable("subject-id") long subjectId) {
		subjectService.deleteSubject(subjectId);
	}

////////////suppression auto des sujets sans intéraction ////////////
//////////////TO DO/////////////////////

}
