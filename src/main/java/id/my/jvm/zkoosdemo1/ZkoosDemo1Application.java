package id.my.jvm.zkoosdemo1;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import id.my.jvm.zkoosdemo1.customer.Customer;
import id.my.jvm.zkoosdemo1.customer.CustomerRepository;

@SpringBootApplication
public class ZkoosDemo1Application {

    private static final String[][] ANIME_CHARACTERS = {
            // Naruto
            {"Naruto Uzumaki", "Naruto"},
            {"Sasuke Uchiha", "Naruto"},
            {"Sakura Haruno", "Naruto"},
            {"Kakashi Hatake", "Naruto"},
            {"Itachi Uchiha", "Naruto"},
            {"Gaara", "Naruto"},
            {"Hinata Hyuga", "Naruto"},
            {"Shikamaru Nara", "Naruto"},
            {"Rock Lee", "Naruto"},
            {"Jiraiya", "Naruto"},
            // One Piece
            {"Monkey D. Luffy", "One Piece"},
            {"Roronoa Zoro", "One Piece"},
            {"Nami", "One Piece"},
            {"Usopp", "One Piece"},
            {"Vinsmoke Sanji", "One Piece"},
            {"Tony Tony Chopper", "One Piece"},
            {"Nico Robin", "One Piece"},
            {"Franky", "One Piece"},
            {"Brook", "One Piece"},
            {"Jinbe", "One Piece"},
            // Jujutsu Kaisen
            {"Yuji Itadori", "Jujutsu Kaisen"},
            {"Megumi Fushiguro", "Jujutsu Kaisen"},
            {"Nobara Kugisaki", "Jujutsu Kaisen"},
            {"Satoru Gojo", "Jujutsu Kaisen"},
            {"Nanami Kento", "Jujutsu Kaisen"},
            {"Maki Zenin", "Jujutsu Kaisen"},
            {"Toge Inumaki", "Jujutsu Kaisen"},
            {"Panda", "Jujutsu Kaisen"},
            {"Suguru Geto", "Jujutsu Kaisen"},
            {"Ryomen Sukuna", "Jujutsu Kaisen"},
            // Demon Slayer
            {"Tanjiro Kamado", "Demon Slayer"},
            {"Nezuko Kamado", "Demon Slayer"},
            {"Zenitsu Agatsuma", "Demon Slayer"},
            {"Inosuke Hashibira", "Demon Slayer"},
            {"Giyu Tomioka", "Demon Slayer"},
            {"Shinobu Kocho", "Demon Slayer"},
            {"Kyojuro Rengoku", "Demon Slayer"},
            {"Tengen Uzui", "Demon Slayer"},
            {"Muichiro Tokito", "Demon Slayer"},
            {"Mitsuri Kanroji", "Demon Slayer"},
            // Doraemon
            {"Doraemon", "Doraemon"},
            {"Nobita Nobi", "Doraemon"},
            {"Shizuka Minamoto", "Doraemon"},
            {"Takeshi Goda", "Doraemon"},
            {"Suneo Honekawa", "Doraemon"},
            {"Dorami", "Doraemon"},
            {"Hidetoshi Dekisugi", "Doraemon"},
            {"Sewashi Nobi", "Doraemon"},
            {"Nobisuke Nobi", "Doraemon"},
            {"Tamako Nobi", "Doraemon"},
    };

    public static void main(String[] args) {
        SpringApplication.run(ZkoosDemo1Application.class, args);
    }

    @Bean
    CommandLineRunner seedCustomers(CustomerRepository customerRepository) {
        return args -> {
            if (customerRepository.count() == 0) {
                for (int i = 0; i < ANIME_CHARACTERS.length; i++) {
                    String name = ANIME_CHARACTERS[i][0];
                    String series = ANIME_CHARACTERS[i][1];
                    String slug = name.toLowerCase().replaceAll("[^a-z0-9]+", ".").replaceAll("^\\.|\\.$", "");
                    String seriesSlug = series.toLowerCase().replaceAll("[^a-z0-9]+", "");
                    String email = slug + "@" + seriesSlug + ".fan";
                    String phone = String.format("+62 812-0000-%04d", i + 1);
                    customerRepository.save(new Customer(null, name, email, phone, series));
                }
            }
        };
    }

}
