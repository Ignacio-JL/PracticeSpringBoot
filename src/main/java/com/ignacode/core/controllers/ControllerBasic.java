package com.ignacode.core.controllers;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ignacode.core.configuration.Pages;
import com.ignacode.core.model.Post;

@Controller
@RequestMapping("/home")
public class ControllerBasic {
	
	public List<Post> getPosts() {
		ArrayList<Post> post = new ArrayList<>();
		
		post.add(new Post(1, "Desarrollo web es un término que define la creación de sitios web para Internet o una intranet.","http://localhost/img/post.jpg",new Date(),"Desarrollo web" ));
		post.add(new Post(2, "Desarrollo web es un término que define la creación de sitios web para Internet o una intranet.","http://localhost/img/post.jpg",new Date(),"Desarrollo web Front-End" ));
		post.add(new Post(3, "Desarrollo web es un término que define la creación de sitios web para Internet o una intranet.","http://localhost/img/post.jpg",new Date(),"Desarrollo web Back-End" ));
		post.add(new Post(4, "Desarrollo web es un término que define la creación de sitios web para Internet o una intranet.","http://localhost/img/post.jpg",new Date(),"Desarrollo web UX/UI" ));
		
		
		return post;
	}
	
	@GetMapping(path = {"/posts","/"})
	public String saludar(Model model) {
		model.addAttribute("posts", this.getPosts());//EL model lo usamos para pasar los datos a la pagina HTML en este caso, las cards y usar los parametros en el for each(th:each) para generar las cards 
		//Con el getPosts obtenemos la lista post hecha arriba
		return "index";
	}
	//Model es para pasar datos a la Vista
	//con ModelAndView podemos hacer algo similiar como /post, No usaremos este pero lo dejo de todas formas
	@GetMapping(path="/public")
	public ModelAndView post() {
		ModelAndView modelAndView = new ModelAndView(Pages.HOME); //Pages.Home = "index"
		modelAndView.addObject("posts", this.getPosts());
		return modelAndView;
	}
	//hacemos que desde el home/ o home/posts se redirijan a home/post=1
	
	@GetMapping(path = {"/post","/post/p/{numPost}"})
	public ModelAndView getPostIndividual(
			@PathVariable(required = true, name = "numPost") int id//Para que la url sea post/1 post/2 etc, en vez de post?id=1
			) {
		ModelAndView modelAndView = new ModelAndView(Pages.POST); //indicamos el post.html
		
		List<Post> postFiltrado = this.getPosts()
							.stream()
							.filter( (p) -> {
								return p.getId() == id;
							}).collect(Collectors.toList());
		
		modelAndView.addObject("post", postFiltrado.get(0));
		return modelAndView;		
	}
	
	@GetMapping("/postNew")
	public ModelAndView getForm() {
		return new ModelAndView("form").addObject("post", new Post());
	}
	
	@PostMapping("/addNewPost")
	public String addNewPost(Post post, Model model) {// post porque es el nombre del th:object en form.html
		List<Post> posts = this.getPosts();
		posts.add(post);
		model.addAttribute("posts", posts);
		return "index";
	}
	

}
