package br.docket.cartorio.controller.rest;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@RestController
@RequestMapping("/v1/cartorio")
public class CartorioRestController {
	
	@Autowired
	private CartorioService cartorioService;
	
	@Autowired
	private CertidaoService certidaoService;

	/**
	 * Cadastra um novo cartorio
	 * @param cartorio
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, headers = "Content-Type=application/json")
	public String cadastrarCartorio(@RequestBody Cartorio cartorio) {
		cartorioService.salvar(cartorio);
		return "Cartorio " + cartorio.getId() + " cadastrado.";
	}
	
	/**
	 * Edita um cartorio existente
	 * @param cartorio
	 * @return
	 */
	@GetMapping(value = { "/editar/{idCartorio}" })
	@ResponseBody
	public Cartorio editarCartorio(@PathVariable Long idCartorio) {
		Optional<Cartorio> opt = cartorioService.findById(idCartorio);
		if (opt.isPresent()) {
			return opt.get();
		}
		return new Cartorio();
	}
}