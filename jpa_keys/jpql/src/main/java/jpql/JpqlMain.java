package jpql;

import javax.persistence.*;
import java.util.List;

public class JpqlMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            Member member = new Member();
            member.setUsername("member1");
            member.setAge(20);
            em.persist(member);

//            TypedQuery<Member> query = em.createQuery("select m from Member m", Member.class);
//            List<Member> resultList = query.getResultList();
//
//            for (Member member1 : resultList) {
//                System.out.println("member1 = " + member1);
//            }

            TypedQuery<MemberDTO> query = em.createQuery(
                    "select new jpql.MemberDTO(m.username, m.age) from Member m", MemberDTO.class);

            List<MemberDTO> result = query.getResultList();

            MemberDTO memberDTO = result.get(0);


            System.out.println("username = " + memberDTO.getUsername());
            System.out.println("age = " + memberDTO.getAge());


            tx.commit();
        }catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }finally {
            em.close();
        }
        emf.close();
    }
}
