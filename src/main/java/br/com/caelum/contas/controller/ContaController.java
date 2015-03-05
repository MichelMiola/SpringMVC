package br.com.caelum.contas.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.contas.dao.ContaDAO;
import br.com.caelum.contas.modelo.Conta;

@Controller
public class ContaController {
	
	private ContaDAO contaDAO;

	@Autowired
	public ContaController(ContaDAO contaDAO) {
		this.contaDAO = contaDAO;
	}
	
	@RequestMapping("/form")
	public String formulario() {
		return "conta/formulario";
	}
	
	@RequestMapping("/adicionaConta")
	public String adiciona(@Valid Conta conta, BindingResult result) {
		
		// validando a descri��o
	    if(result.hasErrors()) {
	      return "conta/formulario";
	    }
		
		System.out.println("Conta adicionada �: " + conta.getDescricao());
		
		contaDAO.adiciona(conta);
		
		return "conta/conta-adicionada";
	}
	
	@RequestMapping("/listaContas")
	public ModelAndView lista(){
		
		List<Conta> contas = contaDAO.lista();
		
		ModelAndView mv = new ModelAndView("conta/lista");
		mv.addObject("todasContas", contas);
		
		return mv;
	}
	

	@RequestMapping("/removeConta")
	public String remove(Conta conta){
		
		System.out.println("ID da conta: " + conta.getId());
		
		contaDAO.remove(conta);
		
		//Podemos fazer um redirecionamento na lado do servidor (forward) ou 
		//pelo navegador, no lado do cliente (redirect)
		
		return "redirect: listaContas";
	}
	
	@RequestMapping("mostraConta")
	public String mostra(Long id, Model model) {
		
		model.addAttribute("conta", contaDAO.buscaPorId(id));
		
		return "conta/mostra";
	}
	
	@RequestMapping("/alteraConta")
	public String altera(Conta conta) {
		
		contaDAO.altera(conta);
		
		return "redirect: listaContas";
		
	}
	
	@RequestMapping("/pagaConta")
	public void paga(Long id, HttpServletResponse resp) {
		
		contaDAO.paga(id);
		
		resp.setStatus(200);
	}
	
}
