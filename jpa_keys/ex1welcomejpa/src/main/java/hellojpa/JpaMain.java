package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

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

//            Movie movie = new Movie();
//            movie.setDirector("aaaa");
//            movie.setActor("bbbb");
//            movie.setName("바람과함께사라지다");
//            movie.setPrice(10000);
//
//            em.persist(movie);

//            Member member = new Member();
//            member.setUsername("user1");
//            member.setCreatedBy("lee");
//            member.setCreatedDate(LocalDateTime.now());
//
//            em.persist(member);
//
//            em.flush();
//            em.clear();

//            Movie findMovie = em.find(Movie.class, movie.getId());
//            System.out.println("findMovie = " + findMovie);

//            Member member = em.find(Member.class, 1L);
//
//              printUser(member);
              
//            printMemberAndTeam(member);

//            Team team = new Team();
//            team.setName("teamA");
//            em.persist(team);
//
//            Member member1 = new Member();
//            member1.setUsername("member1");
//            member1.setTeam(team);
//
//            em.persist(member1);
//
//            em.flush();
//            em.clear();
//
//            Member m = em.find(Member.class, member1.getId());
//            System.out.println("m = " + m.getTeam().getClass());
//
//            System.out.println(" ==================== ");
//            m.getTeam().getName();
//            System.out.println(" ==================== ");

            ///
//            Member findMember = em.find(Member.class, member.getId());

//            Member findMember = em.getReference(Member.class, member.getId());
//
//            System.out.println("before findMember = " + findMember.getClass());
//            System.out.println("findMember.username = " + findMember.getUsername());
//            System.out.println("after findMember = " + findMember.getClass());

//            System.out.println("findMember = " + findMember.getClass());
//            System.out.println("findMember.Id = " + findMember.getId());
//            System.out.println("findMember.getUsername = " + findMember.getUsername());

//              Child child1 = new Child();
//              Child child2 = new Child();
//
//              Parent parent = new Parent();
//
//              parent.addChild(child1);
//              parent.addChild(child2);
//
//              em.persist(parent);
//              em.persist(child1);
//              em.persist(child2);
//
//
//              em.flush();
//              em.clear();
//
//            Parent findParent = em.find(Parent.class, parent.getId());
//            //findParent.getChildList().remove(0);
//
//            em.remove(findParent);

//            Member member = new Member();
//            member.setUsername("hello");
//            member.setHomeAddress(new Address("city","street","100"));
//            member.setWorkPeriod(new Period());
//
//            em.persist(member);

//            Member member = new Member();
//            member.setUsername("member1");
//            member.setHomeAddress(new Address("homeCity","street","1000"));
//
//            member.getFavoriteFoods().add("돈까스");
//            member.getFavoriteFoods().add("초밥");
//            member.getFavoriteFoods().add("김치찌개");
//
//            member.getAddressHistory().add(new AddressEntity("old1","street","1000"));
//            member.getAddressHistory().add(new AddressEntity("old2","street","1000"));
//
//            em.persist(member);
//
//            em.flush();
//            em.clear();
//
//            System.out.println(" ======START====== ");
//
//            Member findMember = em.find(Member.class, member.getId());

//            List<Address> addressHistory = findMember.getAddressHistory();
//            for (Address address : addressHistory) {
//                System.out.println("address = " + address.getCity());
//            }
//
//            Set<String> favoriteFoods = findMember.getFavoriteFoods();
//            for (String favoriteFood : favoriteFoods) {
//                System.out.println("favoriteFood = " + favoriteFood);
//            }
//            Address old = findMember.getHomeAddress();

            //homeCity => newCity
//            findMember.setHomeAddress(new Address("newCity", old.getStreet(), old.getZipcode()));
//
//            findMember.getFavoriteFoods().remove("돈까스");
//            findMember.getFavoriteFoods().add("까르보나라");

//            findMember.getAddressHistory().remove(new AddressEntity("old1","street","1000"));
//            findMember.getAddressHistory().add(new AddressEntity("newCity1","street","1000"));

//            List<Member> result = em.createQuery(
//                    "select m from Member m where m.username like '%kim%'",
//                    Member.class).getResultList();


            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Member> query = cb.createQuery(Member.class);

            Root<Member> m = query.from(Member.class);
            CriteriaQuery<Member> cq = query.select(m).where(cb.equal(m.get("username"), "kim"));
            List<Member> resultList = em.createQuery(cq)
                    .getResultList();


            tx.commit();                                //트랜젝션을 커밋

        } catch (Exception e){
            e.printStackTrace();
            tx.rollback();                              //문제가 발생하면 롤백

        } finally {
            em.close();                                 //사용이 끝난 EntityManager 는 반드시 닫아줄 것
        }

        emf.close();        //웹 애플리케이션의 경우 WAS 가 내려갈 때 EntityManagerFactory 를 닫아줘야 함
    }

//    private static void printUser(Member member) {
//        System.out.println("member.getUsername() = " + member.getUsername());
//    }
//
//    private static void printMemberAndTeam(Member member) {
//
//        String username = member.getUsername();
//        System.out.println("username = " + username);
//
//        Team team = member.getTeam();
//        System.out.println("team.getName() = " + team.getName());
//    }
}
