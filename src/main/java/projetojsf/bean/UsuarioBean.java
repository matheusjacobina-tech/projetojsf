package projetojsf.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import projetojsf.dao.UsuarioDAO;
import projetojsf.model.UsuarioPessoa;

@ManagedBean(name = "usuarioBean")
@ViewScoped
public class UsuarioBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
    
    private UsuarioPessoa usuarioPessoa = new UsuarioPessoa();	
	private List<UsuarioPessoa> listUsuarioPessoa = new ArrayList<UsuarioPessoa>(); // SEM PAGINATOR	
	private UsuarioDAO<UsuarioPessoa> usuarioDAO = new UsuarioDAO<UsuarioPessoa>();

	
	//	Getter Setter
	public List<UsuarioPessoa> getListUsuarioPessoa() {
		return listUsuarioPessoa;
	}
	
	public UsuarioPessoa getUsuarioPessoa() {
		return usuarioPessoa;
	}
	
	public void setUsuarioPessoa(UsuarioPessoa usuarioPessoa) {
		this.usuarioPessoa = usuarioPessoa;
	}
	
	
	// Metodos
	@PostConstruct
	public void init() {
		listUsuarioPessoa = usuarioDAO.listar(UsuarioPessoa.class);
	}
	
    public String salva() {
        usuarioDAO.salvar(usuarioPessoa);
        init();
        return "";
    }
    
    public String remover() {
    	try {
			usuarioDAO.deletarPorId(usuarioPessoa);
			
			//listUsuarioPessoa.list.remove(usuarioPessoa);
			
			usuarioPessoa = new UsuarioPessoa();
			init();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação: ", "Removido com sucesso!"));			
		} catch (Exception e) {
			e.printStackTrace();
		}    	
		return "";
	}
    
    public String editar() {
    	return "";
	}
    
}
