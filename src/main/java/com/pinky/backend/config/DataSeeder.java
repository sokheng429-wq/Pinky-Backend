package com.pinky.backend.config;

import com.pinky.backend.entity.Gender;
import com.pinky.backend.entity.Notes;
import com.pinky.backend.entity.Product;
import com.pinky.backend.entity.Role;
import com.pinky.backend.entity.User;
import com.pinky.backend.repository.ProductRepository;
import com.pinky.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Seeds the H2 in-memory database on every startup with the same 12
 * perfumes the React frontend previously mocked locally in
 * src/services/productService.js (SEED_PRODUCTS), so the UI looks
 * identical whether it's talking to the local fallback or this API.
 */
@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (userRepository.count() == 0) {
            userRepository.save(User.builder()
                    .name("Admin")
                    .email("admin@pinky.com")
                    .password(passwordEncoder.encode("Admin@123"))
                    .role(Role.ADMIN)
                    .build());
        }

        if (productRepository.count() > 0) {
            return;
        }

        productRepository.saveAll(List.of(
                Product.builder()
                        .name("Rose Elegance")
                        .brand("Chanel")
                        .gender(Gender.FEMALE)
                        .price(59)
                        .oldPrice(75.0)
                        .rating(4.8)
                        .reviewsCount(128)
                        .badge("Best Seller")
                        .stock(24)
                        .image("https://images.unsplash.com/photo-1594035910387-fea47794261f?q=80&w=800&auto=format&fit=crop")
                        .smell("A romantic floral scent with fresh roses, vanilla and soft musk.")
                        .notes(new Notes("Fresh Rose, Bergamot", "Peony & Jasmine", "Vanilla & White Musk"))
                        .size("50 ml")
                        .build(),

                Product.builder()
                        .name("Pink Dream")
                        .brand("Dior")
                        .gender(Gender.FEMALE)
                        .price(49)
                        .oldPrice(null)
                        .rating(4.6)
                        .reviewsCount(96)
                        .badge("New")
                        .stock(40)
                        .image("https://images.unsplash.com/photo-1547887538-e3a2f32cb1cc?q=80&w=800&auto=format&fit=crop")
                        .smell("Sweet fruity fragrance with strawberry, peach and warm sugar notes.")
                        .notes(new Notes("Strawberry & Peach", "Sugared Petals", "Warm Sugar & Musk"))
                        .size("50 ml")
                        .build(),

                Product.builder()
                        .name("Luxury Blossom")
                        .brand("Gucci")
                        .gender(Gender.FEMALE)
                        .price(89)
                        .oldPrice(110.0)
                        .rating(4.9)
                        .reviewsCount(203)
                        .badge("Best Seller")
                        .stock(12)
                        .image("https://images.unsplash.com/photo-1595425970377-c9703cf48b7c?q=80&w=800&auto=format&fit=crop")
                        .smell("Elegant jasmine mixed with amber and a deep feminine touch.")
                        .notes(new Notes("Jasmine & Pear", "Amber & Orchid", "Sandalwood & Musk"))
                        .size("75 ml")
                        .build(),

                Product.builder()
                        .name("Princess Love")
                        .brand("Chanel")
                        .gender(Gender.FEMALE)
                        .price(69)
                        .oldPrice(null)
                        .rating(4.5)
                        .reviewsCount(74)
                        .badge(null)
                        .stock(30)
                        .image("https://images.unsplash.com/photo-1619994403073-2cec844b8e63?q=80&w=800&auto=format&fit=crop")
                        .smell("A charming perfume with flowers, vanilla and creamy sweetness.")
                        .notes(new Notes("Lychee & Freesia", "White Flowers", "Vanilla Cream"))
                        .size("50 ml")
                        .build(),

                Product.builder()
                        .name("Golden Queen")
                        .brand("YSL")
                        .gender(Gender.FEMALE)
                        .price(99)
                        .oldPrice(130.0)
                        .rating(4.9)
                        .reviewsCount(311)
                        .badge("Best Seller")
                        .stock(8)
                        .image("https://images.unsplash.com/photo-1600612253971-422e7f7faeb6?q=80&w=800&auto=format&fit=crop")
                        .smell("Luxury golden fragrance with oud, rose and warm spices.")
                        .notes(new Notes("Saffron & Rose", "Oud & Spice", "Golden Amber"))
                        .size("75 ml")
                        .build(),

                Product.builder()
                        .name("Sweet Angel")
                        .brand("Dior")
                        .gender(Gender.FEMALE)
                        .price(55)
                        .oldPrice(null)
                        .rating(4.4)
                        .reviewsCount(58)
                        .badge("New")
                        .stock(35)
                        .image("https://images.unsplash.com/photo-1590736969955-71cc94901144?q=80&w=800&auto=format&fit=crop")
                        .smell("Soft powdery perfume with lavender and sweet vanilla.")
                        .notes(new Notes("Lavender & Bergamot", "Iris Powder", "Sweet Vanilla"))
                        .size("50 ml")
                        .build(),

                Product.builder()
                        .name("Royal Oud")
                        .brand("Tom Ford")
                        .gender(Gender.MALE)
                        .price(120)
                        .oldPrice(150.0)
                        .rating(4.9)
                        .reviewsCount(240)
                        .badge("Best Seller")
                        .stock(15)
                        .image("https://images.unsplash.com/photo-1595425970377-c9703cf48b6f?q=80&w=800&auto=format&fit=crop")
                        .smell("Deep, smoky oud wrapped in leather and warm spice.")
                        .notes(new Notes("Black Pepper", "Oud & Leather", "Cedarwood & Amber"))
                        .size("100 ml")
                        .build(),

                Product.builder()
                        .name("Midnight Black")
                        .brand("Dior")
                        .gender(Gender.MALE)
                        .price(99)
                        .oldPrice(null)
                        .rating(4.7)
                        .reviewsCount(167)
                        .badge("New")
                        .stock(22)
                        .image("https://images.unsplash.com/photo-1619994403073-2cec844b8e63?q=80&w=800&auto=format&fit=crop")
                        .smell("Bold and mysterious, with dark spice and smoky woods.")
                        .notes(new Notes("Bergamot & Pepper", "Incense", "Smoky Woods"))
                        .size("75 ml")
                        .build(),

                Product.builder()
                        .name("Gentleman Elite")
                        .brand("Chanel")
                        .gender(Gender.MALE)
                        .price(140)
                        .oldPrice(165.0)
                        .rating(4.8)
                        .reviewsCount(189)
                        .badge("Best Seller")
                        .stock(10)
                        .image("https://images.unsplash.com/photo-1594035910387-fea47794261f?q=80&w=800&auto=format&fit=crop")
                        .smell("A refined, confident scent built on citrus and vetiver.")
                        .notes(new Notes("Citrus Zest", "Vetiver", "Tonka Bean"))
                        .size("100 ml")
                        .build(),

                Product.builder()
                        .name("Ocean Luxury")
                        .brand("YSL")
                        .gender(Gender.MALE)
                        .price(110)
                        .oldPrice(null)
                        .rating(4.5)
                        .reviewsCount(92)
                        .badge(null)
                        .stock(27)
                        .image("https://images.unsplash.com/photo-1592945403244-b3fbafd7f539?q=80&w=800&auto=format&fit=crop")
                        .smell("Fresh aquatic notes with a clean, energizing finish.")
                        .notes(new Notes("Sea Spray & Mint", "Marine Accord", "White Musk"))
                        .size("75 ml")
                        .build(),

                Product.builder()
                        .name("Velvet Rose")
                        .brand("Gucci")
                        .gender(Gender.FEMALE)
                        .price(110)
                        .oldPrice(135.0)
                        .rating(4.7)
                        .reviewsCount(143)
                        .badge("New")
                        .stock(18)
                        .image("https://images.unsplash.com/photo-1588405748880-12d1d2a59d75?q=80&w=800&auto=format&fit=crop")
                        .smell("Plush, velvety rose layered over soft suede and musk.")
                        .notes(new Notes("Velvet Rose", "Suede Accord", "Soft Musk"))
                        .size("75 ml")
                        .build(),

                Product.builder()
                        .name("Amber Storm")
                        .brand("Tom Ford")
                        .gender(Gender.MALE)
                        .price(130)
                        .oldPrice(null)
                        .rating(4.6)
                        .reviewsCount(77)
                        .badge(null)
                        .stock(20)
                        .image("https://images.unsplash.com/photo-1615634262417-9b5c2a1f1c7c?q=80&w=800&auto=format&fit=crop")
                        .smell("Warm amber and smoked woods for a bold night out.")
                        .notes(new Notes("Cardamom", "Amber", "Smoked Wood"))
                        .size("100 ml")
                        .build()
        ));
    }
}
