package study.datajpa.repository;

import org.springframework.stereotype.Repository;
import study.datajpa.entity.Member;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class MemberJpaRepository {

    @PersistenceContext
    private EntityManager em;

    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    public void delete(Member member) {
        em.remove(member);
    }
    public long count() {
        return em.createQuery("SELECT COUNT(m) FROM Member m", Long.class)
                .getSingleResult();
    }

    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    public List<Member> findAll() {
      return em.createQuery("SELECT m FROM Member m",Member.class)
              .getResultList();
    }

    public Member find(Long id) {
        return em.find(Member.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Member> findByUsernameAndAgeGreaterThan(String username, int age) {
        return em.createQuery("SELECT m FROM Member m WHERE m.username = :username and m.age > :age")
                .setParameter("username", username)
                .setParameter("age", age)
                .getResultList();
    }

    public List<Member> findByUsername(String username) {
        return em.createNamedQuery("Member.findByUsername", Member.class)
                .setParameter("username", username)
                .getResultList();
    }


    public List<Member> findByPage(int age, int offset, int limit) {
        return em.createQuery("SELECT m FROM Member m WHERE m.age = :age ORDER BY m.username DESC", Member.class)
                .setParameter("age", age)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    public long totalCount(int age){
        return em.createQuery("SELECT COUNT(m) FROM Member m WHERE m.age = :age",Long.class)
                .setParameter("age",age)
                .getSingleResult();
    }

}
