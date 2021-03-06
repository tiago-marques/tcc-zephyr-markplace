package br.com.zephyrplace.bean;


import java.text.SimpleDateFormat;
import java.util.Date;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.zephyrplace.facade.UsuarioFacade;
import br.com.zephyrplace.hibernate.Usuario;

@Named("usuarioBean")
@RequestScoped
public class UsuarioBeanImpl {

	private Usuario usuario;
	private String usuarioLogado = "";
	private String resposta = "Bem vindo a Rede Social";

	public UsuarioBeanImpl(){
		usuario = new Usuario();
	}
	
	@Inject
	private UsuarioFacade usuarioFacade;

	public String getResposta() {
		return resposta;
	}

	public void setResposta(String resposta) {
		this.resposta = resposta;
	}

	public String getUsuarioLogado() {
		return this.usuarioLogado;
	}

	public void setUsuarioLogado(String usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	
	public String receber() {
		try {
			setUsuarioLogado(usuarioFacade.consultarUsuario(getUsuario()).getUsuario());
			usuario = new Usuario();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (usuarioLogado.equals("")) {
			setResposta("Usuario ou Senha incorretos.");
		} else {
			setResposta(usuarioFacade.helloWorld());
		}
		
		return "/paginas/index";
	}

	public String exibirData() {
		Date date = new Date();
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
		return formatador.format(date);
	}
	
	public String telaCadastro(){
		return "/admin/admin";
	}
	
	public String telaIndex(){
		return "/paginas/index";
	}

	public void inserir() throws Exception {
		usuarioFacade.inserirUsuario(usuario);
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	
	
}
