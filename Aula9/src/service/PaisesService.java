package service;

import java.util.ArrayList;
import dao.PaisDAO;
import model.Pais;

public class PaisesService {
	PaisDAO dao;
	
	public PaisesService(){
		dao = new PaisDAO();
		
	}
	public ArrayList<Pais> listarPais(){
		return dao.listarPais();
	}
	public ArrayList<Pais> listarPais(String chave){
		return dao.listarPais(chave);
	}
	
}
