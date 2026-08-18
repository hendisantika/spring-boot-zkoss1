package id.my.jvm.zkoosdemo1;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import id.my.jvm.zkoosdemo1.customer.Customer;
import id.my.jvm.zkoosdemo1.customer.CustomerRepository;

@SpringBootApplication
public class ZkoosDemo1Application {

    public static void main(String[] args) {
        SpringApplication.run(ZkoosDemo1Application.class, args);
    }

    @Bean
    CommandLineRunner seedCustomers(CustomerRepository customerRepository) {
        return args -> {
            if (customerRepository.count() == 0) {
                customerRepository.save(new Customer(null, "Budi Santoso", "budi.santoso@example.com", "+62 812-1111-2222", "Jakarta"));
                customerRepository.save(new Customer(null, "Siti Aminah", "siti.aminah@example.com", "+62 813-3333-4444", "Bandung"));
                customerRepository.save(new Customer(null, "John Doe", "john.doe@example.com", "+62 814-5555-6666", "Surabaya"));
            }
        };
    }

}
