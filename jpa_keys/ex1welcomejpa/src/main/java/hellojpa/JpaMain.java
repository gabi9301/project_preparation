package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); //트랜젝션을 받아옴
        tx.begin();                                 //트랜젝션을 시작함

        try {                                       //Spring 을 함께 사용한다면 Spring 이 이러한 예외를 관리하므로
//            Member member = new Member();           //try catch 문을 생략할 수 있다.
//            member.setId(1L);
//            member.setName("HelloA");

            //em.persist(member);



//            team.getMembers().add(member);
//            em.flush();
//            em.clear();

            Movie movie = new Movie();
            movie.setDirector("aaaa");
            movie.setActor("bbbb");
            movie.setName("바람과함께사라지다");
            movie.setPrice(10000);

            em.persist(movie);

            em.flush();
            em.clear();

            Movie findMovie = em.find(Movie.class, movie.getId());
            System.out.println("findMovie = " + findMovie);

            tx.commit();                                //트랜젝션을 커밋

        } catch (Exception e){
            tx.rollback();                              //문제가 발생하면 롤백

        } finally {
            em.close();                                 //사용이 끝난 EntityManager 는 반드시 닫아줄 것
        }

        emf.close();        //웹 애플리케이션의 경우 WAS 가 내려갈 때 EntityManagerFactory 를 닫아줘야 함
    }
}
