package br.docket.cartorio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.docket.cartorio.entity.Cartorio;
import br.docket.cartorio.entity.Certidao;
import br.docket.cartorio.service.CertidaoService;

@Controller
@RequestMapping("/certidao")
public class CertidaoController {

	@Autowired
	private CertidaoService certidaoService;
	
	@GetMapping("/list")
	public String showCartorioList(Model model) {
	    model.addAttribute("certidoes", certidaoService.findAll());
	    return "certidoes-list";
	}
	
    @GetMapping("/cadastrar")
    public String showAddForm(Certidao certidao) {
        return "add-certidao";
    }
    
    /**
	 * Cadastra uma nova certidao
	 * @param certidao
	 * @return
	 */
    @PostMapping("/addcertidao")
    public String addCertidao(@Validated Certidao certidao, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-certidao";
        }
        
        certidaoService.salvar(certidao);
        return "redirect:/certidao/list";
    }
	
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Certidao certidao = certidaoService.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Id da certidao inválido: " + id));
        
        model.addAttribute("certidao", certidao);
        return "update-certidao";
    }
    
    @PostMapping("/update/{id}")
    public String updateCertidao(@PathVariable("id") long id, @Validated Certidao certidao, 
      BindingResult result, Model model) {
        if (result.hasErrors()) {
            certidao.setId(id);
            return "update-certidao";
        }
            
        certidaoService.salvar(certidao);
        return "redirect:/certidao/list";
    }
        
    @GetMapping("/delete/{id}")
    public String deleteCertidao(@PathVariable("id") long id, Model model) {
        Certidao certidao = certidaoService.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Certidão com id inválido: " + id));
        certidaoService.remover(certidao);
        return "redirect:/certidao/list";
    }
}
