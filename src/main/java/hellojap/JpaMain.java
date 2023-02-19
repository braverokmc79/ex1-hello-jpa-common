package hellojap;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
	public static void main(String[] args) {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("hello");
		
		EntityManager em=emf.createEntityManager();
		
		//JPA 의 모든 데이터 변경은  트랜잭션 안에서 실행 되어야 한다.
		EntityTransaction tx=em.getTransaction();
		tx.begin();
			
		try {
			/*생성
			Member member =new Member();
			member.setId(2L);
			member.setName("HelloB");
			
			em.persist(member);
			*/
			
			/*조회
			Member findMember  =em.find(Member.class, 1L);
			System.out.println("findMember  = "+ findMember.getId());
			 */
			
			/* 삭제
			 Member findMember  =em.find(Member.class, 1L);
			 em.remove(findMember);
			 */
			
			/*수정 */
			Member findMember  =em.find(Member.class, 1L);
			findMember.setName("JH");
		
			List<Member> reuslt=em.createQuery("select m from Member as m " , Member.class)
					.setFirstResult(5)
					.setMaxResults(8)
					.getResultList();
			
			for(Member member : reuslt) {
				System.out.println("member.name = " + member.getName());
			}
			
			
			tx.commit();
		}catch(Exception e) {
			tx.rollback();
		}finally {
			em.close();
		}
	
		
		emf.close();	
	}
	
	
}
