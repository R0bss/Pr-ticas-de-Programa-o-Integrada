package Exercicio2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Pais {
	int id;
	String nome, menorA, maiorP;
	long populacao;
	 double area;

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	public Pais() {

	}

	public Pais(int id, String nome, long populacao, double area) {
		this.id = id;
		this.nome = nome;
		this.populacao = populacao;
		this.area = area;
	}
	// jdbc:mysql://localhost:3306/?user=root
	// Obtem conexao com o banco de dados

	public static Connection obtemConexao() throws SQLException {
		return DriverManager
				.getConnection("jdbc:mysql://localhost:3306/pais?user=root&useTimezone=true&serverTimezone=UTC&useSSL=false");
				//"jdbc:mysql://localhost:3306/?user=root"
			//"jdbc:mysql://localhost:3306/pais?user=root&password=&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true\"");
	}
	
	public void criar() {
		String sqlInsert = "INSERT INTO pais(nome,populacao,area) VALUES (?, ?, ?)";
		try (Connection conn = obtemConexao(); 
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setString(1, getNome());
			stm.setLong(2, getPopulacao());
			stm.setDouble(3, getArea());
			stm.execute();
			String sqlQuery = "SELECT LAST_INSERT_ID()";
			try (PreparedStatement stm2 = conn.prepareStatement(sqlQuery); 
					ResultSet rs = stm2.executeQuery();) {
				if (rs.next()) {
					setId(rs.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void atualizar() {
		String sqlUpdate = "UPDATE pais.pais SET nome=?, populacao=?, area=? WHERE id=?;";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = obtemConexao(); 
					PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
			stm.setString(1, getNome());
			stm.setLong(2, getPopulacao());
			stm.setDouble(3, getArea());
			stm.setInt(4, getId());
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void excluir() {
		String sqlDelete = "DELETE FROM pais.pais WHERE id = ?;";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = obtemConexao(); 
				PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
			stm.setInt(1, getId());
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void carregar() {
		String sqlSelect = "SELECT * FROM pais.pais WHERE id =?";
		try (Connection conn = obtemConexao(); 
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, getId());
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					setNome(rs.getString("nome"));
					setPopulacao(rs.getLong("populacao"));
					setArea(rs.getDouble("area"));

				} else {
					setId(-1);
					setNome(null);
					setPopulacao(0);
					setArea(0);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
	}

	public void menorArea() {
		String sqlSelect = "SELECT  nome, MIN(area) FROM pais.pais GROUP BY id ORDER BY area ASC";
		try (Connection conn = obtemConexao(); PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					setMenorA(rs.getString("nome"));
				} else {

				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
	}

	public void maiorpopulacao() {
		String sqlSelect = "SELECT  nome, max(populacao) FROM pais.pais group by id order by populacao desc";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = obtemConexao(); 
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					setMaiorP(rs.getString("nome"));
				} else {

				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
	}

	public static Pais[] Vetor(){
		Pais pais = null;
		Pais[] vetor = new Pais[3];
		int count = 0;
		String sqlSelect = "SELECT id, nome, populacao, area FROM pais.pais limit 3";
		try (Connection conn = obtemConexao(); PreparedStatement stm = conn.prepareStatement(sqlSelect);){
			try (ResultSet rs = stm.executeQuery();){
				while (rs.next()) {
					Integer id = rs.getInt("id");
					String nome = rs.getString("nome");
					Long populacao = rs.getLong("populacao");
					Double area = rs.getDouble("area");
					pais = new Pais(id, nome, populacao, area);
					vetor[count++] = pais;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return vetor;
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
		return maiorP;
	}

	public void setMaiorP(String pais) {
		this.maiorP = pais;
	}

	public String getMenorA() {
		return menorA;
	}

	public void setMenorA(String pais) {
		this.menorA = pais;
	}
	public Pais[] Vetor1() {
		return Vetor();
	}
	

	@Override
	public String toString() {
		return "[id=" + id + ", nome=" + nome + ", população=" + populacao + ", area=" + area + "]";
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
}
