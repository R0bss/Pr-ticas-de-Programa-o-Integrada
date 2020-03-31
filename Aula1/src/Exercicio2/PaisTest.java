package Exercicio2;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PaisTest {
	Pais pais, copia;
	static int id = 0;

	@Before
	public void setUp() throws Exception {
		System.out.println("setup");
		pais = new Pais(id, "Estados Unidos",100,100.00);
		copia = new Pais(id, "Estados Unidos",100,100.00);
		System.out.println(pais);
		System.out.println(copia);
		System.out.println(id);
	}
	@Test
	public void test00Carregar() {
		System.out.println("carregar");
		Pais fixture = new Pais(5, "Russia", 146793744, 9596961);
		Pais novo = new Pais(5, null, 0, 0);
		novo.carregar();
		System.out.print(novo);
		System.out.print("\n" + fixture);
		assertEquals("Teste Carregar",fixture,novo);
	}

	@Test
	public void test01Criar() {
		System.out.println("criar");
		pais.criar();
		id = pais.getId();
		System.out.println(id);
		copia.setId(id);
		assertEquals("testa criacao", pais.toString(), copia.toString());
	}

	@Test
	public void test02Atualizar() {
		System.out.println("Atualizar");
		pais.setPopulacao(999999);
		copia.setPopulacao(999999);
		pais.atualizar();
		pais.carregar();
		System.out.print(pais + "teste");
		System.out.print("\n" + copia);
		assertEquals("testa atualizacao", pais.toString(), copia.toString());
	}

	@Test
	public void test03Excluir() {
		System.out.println("excluir");
		copia.setId(-1);
		copia.setNome(null);
		copia.setPopulacao(0);
		copia.setArea(0);
		pais.excluir();
		pais.carregar();
		assertEquals("testa exclusao", pais, copia);
	}

	@Test
	public void testMaiorPopulacao() {
		pais.maiorpopulacao();
		System.out.println("Maior Populacao");
		System.out.print("China");
		System.out.print("\n" + pais.getMaiorP());
		System.out.println("\n--------------------------");
		assertEquals("Testa Maior populacao","China", pais.getMaiorP());

	}
	@Test
	public void testMenorArea() {
		System.out.println("Menor area");
		System.out.print("Japao");
		//Pais fixture = new Pais(4, "Japao", 377975, 126320000);
		System.out.print("\n" + pais.getMenorA());
		System.out.println("\n--------------------------");
		System.out.println(pais.getMenorA());
		//assertEquals("Testa menor Area: ","Japao", pais.getMenorA());
	}
	@Test
	public void testVetor() {
		System.out.println("3 vetores: ");
		Pais[] vet = pais.Vetor1();
		for (Pais pais : vet) {
			System.out.println(pais);
		}
	}

}
