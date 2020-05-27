package test;
import model.Pais;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import service.PaisService;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PaisTest{ 
	Pais pais, copia;
	static int id = 0; 
	
	
	@Before
	public void setUp() throws Exception {
		System.out.println("setup");
		pais = new Pais();
		pais.setId(id);
		pais.setNome("Estados Unidos");
		pais.setPopulacao(100);
		pais.setArea(10000);
		//pais = new Pais(id, "Estados Unidos",100,100.00);
		//copia = new Pais(id, "Estados Unidos",100,100.00);
		copia = new Pais();
		copia.setId(id);
		copia.setNome("Estados Unidos");
		copia.setPopulacao(100);
		copia.setArea(10000);
		System.out.println(pais);
		System.out.println(copia);
		System.out.println(id);
	}
	@Test
	public void test00Carregar() {
		System.out.println("carregar");
		Pais fixture = new Pais();
		fixture.setId(3);
		fixture.setNome("Russia");
		fixture.setPopulacao(146793744);
		fixture.setArea(9596961);
		PaisService novoService = new PaisService();
		Pais novo = novoService.carregar(3);
		System.out.print(novo);
		System.out.print("\n" + fixture);
		assertEquals("Teste Carregar",fixture,novo);
	}
	@Test
	public void test01Criar() {
		System.out.println("criar");
		PaisService paisService = new PaisService();
		id = paisService.criar(pais);
		System.out.println(id);
		copia.setId(id);
		pais = paisService.carregar(id);
		assertEquals("testa criacao", pais.toString(), copia.toString());
	}
	@Test
	public void test02Atualizar() {
		System.out.println("Atualizar");
		pais.setPopulacao(999999);
		copia.setPopulacao(999999);
		PaisService paisService = new PaisService();
		paisService.atualizar(pais);
		pais = paisService.carregar(pais.getId());
		System.out.print(pais + "teste");
		System.out.print("\n" + copia);
		assertEquals("testa atualizacao", pais.toString(), copia.toString());
	}
	@Test
	public void test03Excluir() {
		System.out.println("excluir");
		PaisService paisService = new PaisService();
		copia.setId(-1);
		copia.setNome(null);
		copia.setPopulacao(0);
		copia.setArea(0);
		paisService.excluir(id);
		paisService.carregar(id);
		//pais.excluir();
		//pais.carregar();
		assertEquals("testa exclusao", pais, copia);
	}
	@Test
	public void testMaiorPopulacao() {
//		pais.maiorpopulacao();
		PaisService paisService = new PaisService();
		System.out.println("Maior Populacao");
		System.out.println(paisService.MaiorP());
//		System.out.print("China");
//		System.out.print("\n" + pais.getMaiorP());
//		System.out.println("\n--------------------------");
//		assertEquals("Testa Maior populacao","China", pais.getMaiorP());
		
	}
	@Test
	public void testMenorArea() {
		PaisService paisService = new PaisService();
		System.out.println("Menor area");
		System.out.println(paisService.MenorA());
		//System.out.print("Japao");
		//Pais fixture = new Pais(4, "Japao", 377975, 126320000);
		//System.out.print("\n" + pais.getMenorA());
		//System.out.println("\n--------------------------");
		//System.out.println(pais.getMenorA());
		//assertEquals("Testa menor Area: ","Japao", pais.getMenorA());
	}
	@Test
	public void testVetor() {
		PaisService paisService = new PaisService();
		System.out.println("3 vetores: ");
		Pais[] vet = paisService.Vetor();
		for (Pais pais : vet) {
			System.out.println(pais);
		}
	}
	
}
