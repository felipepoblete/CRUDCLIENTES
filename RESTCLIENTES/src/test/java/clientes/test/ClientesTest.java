package clientes.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import clientes.modelo.Cliente;
import clientes.modelo.IClienteDao;

@RunWith(SpringRunner.class)
@DataJpaTest

public class ClientesTest {

	@Autowired
	TestEntityManager manager;

	@Autowired
	IClienteDao dao;

	@Before
	public void setUp() throws Exception {

	Cliente cliente = new Cliente("18356987-4", "Pedro", "Soto", "pedro.s@gmail.com", "87460306");
	this.manager.persist(cliente);
	cliente = new Cliente("9356987-4", "Carla", "Soto", "carla.s@gmail.com", "7859664");
	this.manager.persist(cliente);
	cliente = new Cliente("20356987-4", "Andrea", "Torres", "andrea.t@gmail.com", "6874664");
	this.manager.persist(cliente);
	}
	
	@Test
	public void cuandoClienteConSuIdArrojaTrue() {
		System.out.println(this.dao.findById("18356987-4").get());
		assertTrue(true);
	}
	
	@Test
	public void cuandoTodosLosClientesInsertadosTrue() {
		int cuantos = this.dao.findAll().size();
		assertTrue("Ahora son " + cuantos + " pero deberia ser 3", cuantos == 3);
	}

	@Test
	public void eliminar1ClienteTrue() {
		this.dao.deleteById("9356987-4");
		int cuantos = this.dao.findAll().size();
		assertTrue("Ahora son " + cuantos + "usuarios y deberian ser 2", cuantos == 2);
	}

	@Test
	public void cuandoModificamosClienteEntoncesSeObtieneModificacion() {
		this.dao.save(new Cliente("4356987-4", "Maria", "Perez", "maria.p@gmail.com", "9968798"));
		Cliente maria = this.dao.findById("4356987-4").get();
		assertNotNull(maria);
		assertTrue("Es " + maria.getNombres() + " pero deberia ser Peter ", maria.getNombres().equals("Maria"));
	}
	
	@Test
	public void cuandoInsertamos1ArrojaTrue() {
		this.dao.save(new Cliente("4356987-4", "Maria", "Perez", "maria.p@gmail.com", "9968798"));
		int cuantos = this.dao.findAll().size();
		assertTrue("Ahora son " + cuantos + " pero deberían ser 4", cuantos==4);
	}
	
}