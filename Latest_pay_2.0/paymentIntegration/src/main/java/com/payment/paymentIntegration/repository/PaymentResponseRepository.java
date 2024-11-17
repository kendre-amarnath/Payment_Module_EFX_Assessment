package com.payment.paymentIntegration.repository;

import com.payment.paymentIntegration.entity.PaymentResponse;
import org.hibernate.sql.ast.tree.expression.JdbcParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;


@Repository
public interface PaymentResponseRepository extends JpaRepository<PaymentResponse, Long> {
    Optional<PaymentResponse> findByOrderId(Long orderId);
}
