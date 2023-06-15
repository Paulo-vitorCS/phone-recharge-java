package br.com.phoneRecharges.repositories;

import br.com.phoneRecharges.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
