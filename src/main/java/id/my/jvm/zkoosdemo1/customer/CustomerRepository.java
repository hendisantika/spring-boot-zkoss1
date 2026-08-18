package id.my.jvm.zkoosdemo1.customer;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by IntelliJ IDEA.
 * Project : zkoos-demo1
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 18/08/26
 * Time: 22.22
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
