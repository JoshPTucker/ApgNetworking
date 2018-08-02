package ApgNetworking.controllers;

import ApgNetworking.configurations.CloudinaryConfig;
import ApgNetworking.models.ApgUser;
import ApgNetworking.models.Course;
import ApgNetworking.repositories.CourseRepository;
import ApgNetworking.repositories.UserRepository;
import ApgNetworking.services.UserService;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

@Controller
public class MainController {
	@Autowired
	private UserService userService;
	@Autowired
	private CourseRepository courserepo;
	@Autowired
	private UserRepository  userRepo;
	@Autowired
	CloudinaryConfig cloudc;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@RequestMapping("/")
	public String index() {
		return"index";
	}

	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("apgUser", new ApgUser());
		return "register";
	}

	@PostMapping("/register")
	public String displayform(@Valid@ModelAttribute("apgUser")ApgUser apgUser, BindingResult result, Model model,@RequestParam("file")MultipartFile file) {
		if (result.hasErrors()||file.isEmpty()) {
			return "register";
		} else {
			try {
				Map uploadResult =  cloudc.upload(file.getBytes(),
						ObjectUtils.asMap("resourcetype", "auto"));
				apgUser.setPicUrl(uploadResult.get("url").toString());
				String thePassword = apgUser.getPassword();
				apgUser.setPassword(passwordEncoder.encode(thePassword));
					userRepo.save(apgUser);
			} catch (IOException e){
				e.printStackTrace();
				return "redirect:/register";
			}
			return "login";
		}
	}

	//What handles the adding a course
	@GetMapping("/addCourse")
	public String addcourse(Model model) {
		model.addAttribute("course", new Course());
		return "addCourse";
	}

	@PostMapping("/addCourse")
	public String showCourse(@Valid @ModelAttribute("course") Course course, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "addCourse";
		} else {

			courserepo.save(course);
			model.addAttribute("allCourses", courserepo.findAll());
			return "courseList";
		}

	}
}
