package Exercicio2;

public class PaisService {
	PaisDAO dao = new PaisDAO();
	
	public  int criar(Pais pais) {
		return dao.criar(pais);
	}
	public void atualizar(Pais pais) {
		dao.atualizar(pais);
	}
	public void excluir(int id) {
		dao.excluir(id);
	}
	public Pais carregar(int id) {
		Pais pais = dao.carregar(id);
		return pais;
	}
	public Pais MaiorP() {
		return dao.maiorpopulacao();
	}
	public Pais MenorA() {
		return dao.menorArea();
	}
	public Pais[] Vetor() {
		return dao.Vetor1();
	}
}
