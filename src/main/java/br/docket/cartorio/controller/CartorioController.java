package br.docket.cartorio.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import br.docket.cartorio.entity.Cartorio;
import br.docket.cartorio.service.CartorioService;
import br.docket.cartorio.service.CertidaoService;

/**
 * Classe controladora que gerencia as requisições da aplicação
 * @author Herick
 *
 */
@Controller
public class CartorioController {
	
	@Autowired
	private CartorioService cartorioService;
	
	@Autowired
	private CertidaoService certidaoService;
	
	@GetMapping("/index")
	public String showCartorioList(Model model) {
	    model.addAttribute("cartorios", cartorioService.findAll());
	    return "index";
	}
	
    @GetMapping("/signup")
    public String showAddForm(Cartorio cartorio, Model model) {
    	model.addAttribute("certidoes", certidaoService.findAll());
        return "add-cartorio";
    }

	/**
	 * Cadastra um novo cartorio
	 * @param cartorio
	 * @return
	 */
    @PostMapping("/addcartorio")
    public String addCartorio(@Validated Cartorio cartorio, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-cartorio";
        }
        
        cartorioService.salvar(cartorio);
        return "redirect:/index";
    }
	
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Cartorio cartorio = cartorioService.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Id do cartório inválido: " + id));
        
        model.addAttribute("cartorio", cartorio);
        return "update-cartorio";
    }
    
    @PostMapping("/update/{id}")
    public String updateCartorio(@PathVariable("id") long id, @Validated Cartorio cartorio, 
      BindingResult result, Model model) {
        if (result.hasErrors()) {
            cartorio.setId(id);
            return "update-cartorio";
        }
            
        cartorioService.salvar(cartorio);
        return "redirect:/index";
    }
        
    @GetMapping("/delete/{id}")
    public String deleteCartorio(@PathVariable("id") long id, Model model) {
        Cartorio cartorio = cartorioService.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Cartório com id inválido: " + id));
        cartorioService.remover(cartorio);
        return "redirect:/index";
    }
}