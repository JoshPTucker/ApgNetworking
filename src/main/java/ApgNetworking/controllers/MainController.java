package ApgNetworking.controllers;

import ApgNetworking.configurations.CloudinaryConfig;
import ApgNetworking.models.ApgPost;
import ApgNetworking.models.ApgUser;
import ApgNetworking.models.Course;
import ApgNetworking.repositories.CourseRepository;
import ApgNetworking.repositories.PostRepository;
import ApgNetworking.repositories.RoleRepository;
import ApgNetworking.repositories.UserRepository;
import ApgNetworking.services.UserService;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.naming.AuthenticationException;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Arrays;
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
	private RoleRepository roleRepo;
	@Autowired
	CloudinaryConfig cloudc;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private PostRepository postRepository;
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
		model.addAttribute("actCourses", courserepo.findAll());
		return "register";
	}

	@PostMapping("/register")
	public String displayform(@Valid@ModelAttribute("apgUser")ApgUser apgUser, BindingResult result, Model model,@RequestParam("file")MultipartFile file) {
		if (result.hasErrors()||file.isEmpty()) {
			model.addAttribute("actCourses", courserepo.findAll());
			return "register";
		} else {
			try {
				Map uploadResult =  cloudc.upload(file.getBytes(),
						ObjectUtils.asMap("resourcetype", "auto"));
				apgUser.setPicUrl(uploadResult.get("url").toString());
				String thePassword = apgUser.getPassword();
				apgUser.setPassword(passwordEncoder.encode(thePassword));
				apgUser.setEnabled(true);
				apgUser.setRoles(Arrays.asList(roleRepo.findByRole("STUDENT")));
				if(!apgUser.getLinkedIn().startsWith("https://")){
					apgUser.setLinkedIn("https://"+apgUser.getLinkedIn());
				}
				if(!apgUser.getGithub().startsWith("https://")){
					apgUser.setGithub("https://"+apgUser.getGithub());
				}
					userRepo.save(apgUser);
			} catch (IOException e){
				e.printStackTrace();
				model.addAttribute("actCourses", courserepo.findAll());
				return "redirect:/register";
			}
			return "login";
		}
	}
	@RequestMapping ("/profilepage")
	public String profilePage(Authentication authentication, Model model){
		model.addAttribute("currUser",userRepo.findByUsername(authentication.getName()));
		return "profilepage";
	}
	//What handles the adding a course
	@GetMapping("/addcourse")
	public String addcourse(Model model) {
		model.addAttribute("course", new Course());
		return "addcourse";
	}

	@PostMapping("/addcourse")
	public String showCourse(@Valid @ModelAttribute("course") Course course, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "addcourse";
		} else {

			courserepo.save(course);
			model.addAttribute("allCourses", courserepo.findAll());
			return "roster";
		}

	}
	@RequestMapping("/roster")
		public String roster(Model model){
		model.addAttribute("allCourses", courserepo.findAll());
		return "roster";
	}
	@GetMapping("/makepost")
	public String makePost(Model model){
		model.addAttribute("post", new ApgPost());
		return "makepost";
	}
	@PostMapping("/makepost")
	public String savePost(@Valid @ModelAttribute("post") ApgPost post, BindingResult result, Model model,Authentication authentication){
		if (result.hasErrors()) {
			return "makepost";
		} else {
			post.setApguser(userRepo.findByUsername(authentication.getName()));
			if(!post.getLink().startsWith("https://")){
				post.setLink("https://"+post.getLink());
			}
			postRepository.save(post);
			model.addAttribute("posts", postRepository.findAll());
			return "newsfeed";
		}
	}
	@RequestMapping("/newsfeed")
	public String newsFeed(Model model){
		model.addAttribute("posts", postRepository.findAll());
		return "newsfeed";
	}
}
