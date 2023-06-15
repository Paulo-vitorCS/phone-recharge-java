package br.com.phoneRecharges.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.phoneRecharges.domain.Recharge;

public interface RechargeRepository extends JpaRepository<Recharge, Long> {

}
