package Exercicio2;



//import java.io.Serializable;

public class Pais{

	private int id;
	 private String nome;
	 private long populacao;
	 private double area;
	 private String maiorp;
	 private String menora;
	 
	public Pais(int id, String nome, long populacao, double area) {
		setId(id);
		setNome(nome);
		setPopulacao(populacao);
		setArea(area);
	}
	public Pais() {
		
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public long getPopulacao() {
		return populacao;
	}

	public void setPopulacao(long populacao) {
		this.populacao = populacao;
	}

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}
	public String getMaiorP() {
		return maiorp;
	}
	public void setMaiorP(String pais) {
		this.maiorp = pais;
	}


	public String getMenorA() {
		return menora;
	}


	public void setMenorA(String pais) {
		this.menora = pais;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(area);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + id;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + (int) (populacao ^ (populacao >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pais other = (Pais) obj;
		if (Double.doubleToLongBits(area) != Double.doubleToLongBits(other.area))
			return false;
		if (id != other.id)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (populacao != other.populacao)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "[id=" + id + ", nome=" + nome + ", população=" + populacao + ", area=" + area + "]";
	}
	 	
}
