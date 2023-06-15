package br.com.phoneRecharges.repositories;

import br.com.phoneRecharges.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
