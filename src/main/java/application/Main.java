package application;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entities.Pessoa;

public class Main {

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpamaven");

		EntityManager entityManager = entityManagerFactory.createEntityManager();

		Pessoa guilherme = new Pessoa(null, "Guilherme", "guilherme@gmail.com");
		Pessoa thais = new Pessoa(null, "Thais", "thais@gmail.com");
		Pessoa vinicius = new Pessoa(null, "Vinicius", "vinicius@gmail.com");
		Pessoa julia = new Pessoa(null, "Julia", "julia@gmail.com");

		entityManager.getTransaction().begin();

		entityManager.persist(guilherme);

		Pessoa guilhermeAlterado = entityManager.find(Pessoa.class, guilherme.getId());
		guilhermeAlterado.setEmail("vilatoro_gui@gmail.com");

		entityManager.merge(guilhermeAlterado);

		entityManager.persist(thais);
		entityManager.persist(vinicius);
		entityManager.persist(julia);

		Pessoa juliaRemocao = entityManager.find(Pessoa.class, julia.getId());
		entityManager.remove(juliaRemocao);

		entityManager.getTransaction().commit();

		List<Pessoa> pessoas = Arrays.asList(entityManager.find(Pessoa.class, guilherme.getId()),
				entityManager.find(Pessoa.class, thais.getId()),
				  entityManager.find(Pessoa.class, vinicius.getId()));		

		pessoas.forEach(System.out::println);

		entityManager.close();
		entityManagerFactory.close();
	}
}