package pdv.db.dump;

import java.util.HashMap;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import pdv.bean.Perfil;
import pdv.bean.Usuario;

public class DumpBanco {

	public static void main(String[] args) {
		
		System.out.println("Começo");

		EntityManager em = null;
		EntityManagerFactory emf = null;
		
		try {
			Properties pp = new Properties();
			pp.setProperty("hibernate.hbm2ddl.auto", "create");
			
			emf = Persistence.createEntityManagerFactory("PDV-AULA03", pp);
			em = emf.createEntityManager();
			em.getTransaction().begin();
			
			//popularam o banco
			HashMap<Integer, String> perfis = new HashMap();
			perfis.put(0, "Admin");
			perfis.put(1, "Gerente");
			perfis.put(2, "Vendedor");
			
			for (Integer nivel : perfis.keySet()) {
				Perfil p = new Perfil();
				p.setNome(perfis.get(nivel));
				p.setNivel(nivel);
				em.persist(p);
			}
			
			/* Atenção -  */
			em.getTransaction().commit();
			em.getTransaction().begin();
			
			HashMap<String, Integer> usuarios = new HashMap();
			usuarios.put("Admin 1", 0);
			usuarios.put("Eduardo", 1);
			usuarios.put("Fabio", 2);
			usuarios.put("Ana", 2);
			usuarios.put("Carlos", 2);
			usuarios.put("Admin 2", 0);
			
			for (String nome : usuarios.keySet()) {
				Usuario u = new Usuario();
				u.setNome(nome);
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
