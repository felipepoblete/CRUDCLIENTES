
package clientes.modelo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import clientes.modelo.Cliente;

	@Repository
	public interface IClienteDAO extends JpaRepository<Cliente, String>{

	}


