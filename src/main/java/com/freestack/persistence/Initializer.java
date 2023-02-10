package com.freestack.persistence;

import com.freestack.persistence.models.Movie;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

public class Initializer {

	private final static String PERSISTANCE_UNIT_NAME = "myPostGreSqlEntityManager";

	public static void main(String[] args) {
		EntityManagerFactory emf =
		Persistence.createEntityManagerFactory(PERSISTANCE_UNIT_NAME);
		try {
			EntityManager entityManager = emf.createEntityManager();

			Movie aVeryBigMovie1 = new Movie();
			aVeryBigMovie1.setTitle("A first very big movie");
			aVeryBigMovie1.setDescription("A very big movie for big smart people");
			aVeryBigMovie1.setLength(75);
			aVeryBigMovie1.setReleaseYear(2012);

			Movie aVeryBigMovie2 = new Movie();
			aVeryBigMovie2.setTitle("Another very big movie 2");
			aVeryBigMovie2.setDescription("A very bigger movie for even bigger & smarter people ");
			aVeryBigMovie2.setLength(93);
			aVeryBigMovie2.setReleaseYear(2013);

			entityManager.getTransaction().begin();

			entityManager.persist(aVeryBigMovie1);
			entityManager.persist(aVeryBigMovie2);

			// point 2
			entityManager.getTransaction().commit();
			Query query = entityManager.createQuery("SELECT m FROM Movie m");
			List<Movie> results = query.getResultList();
			results.forEach(movie -> System.out.println(movie.getTitle()));

			// point 3
			TypedQuery<Movie> query2 = entityManager.createQuery("SELECT m FROM Movie m WHERE m.id = :idToSearch", Movie.class);
			query2.setParameter("idToSearch", 1L);
			Movie result = query2.getSingleResult();
			if(result != null ) {
				System.out.println(result.getTitle());
			}

			// point 4
			Query query3 = entityManager.createQuery("SELECT m.description, m.title FROM Movie m");
			List<Object[]> results3 = query3.getResultList();

			results3.forEach(arrayObject -> System.out.println(arrayObject[0] + "  " + arrayObject[1]));

			// point 5
			Query query4 = entityManager.createQuery("SELECT MAX(m.length) FROM Movie m");
			Integer maxLengthFound = (Integer) query4.getSingleResult();
			Query query5 = entityManager.createQuery("SELECT m FROM Movie m WHERE m.length = :maxLength");
			query5.setParameter("maxLength", maxLengthFound);
			Movie movieMaxLength = (Movie) query5.getSingleResult();
			System.out.println(movieMaxLength.toString());

			// point 6
			Query query6 = entityManager.createQuery("SELECT AVG(m.length) FROM Movie m");
			System.out.println(query6.getSingleResult());
			//results3.forEach(arrayObject -> System.out.println(arrayObject[0] + "  " + arrayObject[1]));

			// point 7
			System.out.println("Point 7");
			Query query7 = entityManager.createQuery("SELECT m FROM Movie m WHERE m.title LIKE '%very%'");
			List<Movie> movies = query7.getResultList();
			movies.forEach(m-> System.out.println(m.toString()));

			// point 8
			//Query query8 = entityManager.createQuery("SELECT AVG(m.length) FROM Movie m");
			//System.out.println(query6.getSingleResult());


			entityManager.close();
		} catch (Exception e) {

		} finally {
			emf.close();
		}
	}

}