package br.com.stocka.stockaspring;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.com.stocka.stockaspring.dto.ProductDto;
import br.com.stocka.stockaspring.model.ProductModel;
import br.com.stocka.stockaspring.repository.ProductRepository;

@SpringBootApplication
public class StockaSpringApplication implements CommandLineRunner {

	private final ProductRepository productRepository;

	public static void main(String[] args) {
		SpringApplication.run(StockaSpringApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer configure() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry reg) {
				reg.addMapping("/**")
						.allowedOrigins("*")
						.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
						.allowedHeaders("*");
			}
		};
	}

	public StockaSpringApplication(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		List<ProductDto> products = new ArrayList<>();
		// Criar e adicionar produtos à lista
		products.add(createProduct("1 2 3 E TA PRONTO 300 G", "Alimentos", "5.90", "7.49", "10.5", 100, "0106JBS3DX",
				"Corredor A Prateleira 10", LocalDate.of(2023, 6, 30)));
		products.add(createProduct("1 2 3 E TA PRONTO 300 G (Duplicado)", "Alimentos", "5.90", "7.99", "10.5", 100,
				"0109AKWV4U", "Corredor A Prateleira 10", LocalDate.of(2023, 6, 30)));
		products.add(createProduct("ABACATE KG", "Alimentos", "3.80", "4.99", "-12.43", -12, "0106LWMAF6",
				"Corredor A Prateleira 10", LocalDate.of(2023, 6, 30)));
		products.add(createProduct("ABACAXI CALDA HELOMAR 400G", "Alimentos", "7.84", "9.99", "10.5", 100, "0101AWC309",
				"Corredor A Prateleira 10", LocalDate.of(2023, 6, 30)));
		products.add(
				createProduct("ABACAXI CALDA SCHRAMM 400G", "Alimentos", "8.41", "11.99", "1.00", 1, "N5D0000G6B",
						"Corredor A Prateleira 10", LocalDate.of(2023, 6, 30)));
		products.add(createProduct("ABACAXI EM CALDA TRIANGULO MINEIRO 400G", "Alimentos", "6.09", "7.99", "10.5", 100,
				"0102OK2ND5", "Corredor A Prateleira 10", LocalDate.of(2023, 6, 30)));
		products.add(createProduct("ABACAXI UN", "Alimentos", "3.50", "4.99", "11.00", 11, "N5D0000G1T",
				"Corredor A Prateleira 10", LocalDate.of(2023, 6, 30)));
		products.add(createProduct("ABOBORA  C/ COCO CELESTE 300G", "Alimentos", "4.50", "6.49", "10.5", 100,
				"0103QM17FD", "Corredor A Prateleira 10", LocalDate.of(2023, 6, 30)));
		products.add(createProduct("ABOBORA KG", "Alimentos", "2.50", "3.79", "9383.51", 9384, "0RR0000B6V",
				"Corredor A Prateleira 10", LocalDate.of(2023, 6, 30)));
		products.add(createProduct("ABOBRINHA KG", "Alimentos", "1.50", "4.49", "449.24", 449, "0RR0000BJP",
				"Corredor A Prateleira 10", LocalDate.of(2023, 6, 30)));
		products.add(createProduct("ABRIDOR TRAMONTINA", "Utensílios Domésticos", "24.13", "49.99", "1.00", 1,
				"0109CBG50D", "Corredor A Prateleira 10", LocalDate.of(2023, 6, 30)));
		products.add(
				createProduct("ABS ALWAYS PINK NOTURNO C/ ABAS 8UN", "Higiene", "5.22", "7.29", "10.5", 100,
						"01004J0Q8M", "Corredor A Prateleira 10", LocalDate.of(2023, 6, 30)));
		products.add(createProduct("ABS SEMPRE LIVRE ADAPT 32UN C/ ABAS", "Higiene", "10.20", "14.99", "1.00", 1,
				"010A228R57", "Corredor A Prateleira 10", LocalDate.of(2023, 6, 30)));
		products.add(createProduct("ABS SEMPRELIVRE  NOTURNO SECA 8UN C/ABAS", "Higiene", "4.95", "7.49", "10.5", 100,
				"0101CFWK5Y", "Corredor A Prateleira 10", LocalDate.of(2023, 6, 30)));
		products.add(createProduct("ABS. ALWAYS BASICO C/ABAS 8UN SUAVE", "Higiene", "2.28", "3.79", "4.50", 4,
				"010O0NQFVL", "Corredor A Prateleira 10", LocalDate.of(2023, 6, 30)));
		products.add(createProduct("ABSORVENTE  INTIMUS GEL 8UN ", "Higiene", "4.80", "6.99", "10.5", 100, "0109DA1JZJ",
				"Corredor A Prateleira 10", LocalDate.of(2023, 6, 30)));
		products.add(createProduct("ABSORVENTE ALWAYS PINK C/ABAS 8UN NOTURNO", "Higiene", "6.90", "9.99", "10.5", 100,
				"0109DA1JZM", "Corredor A Prateleira 10", LocalDate.of(2023, 6, 30)));
		products.add(
				createProduct("ABSORVENTE INTIMUS GEL NOTURNO 8UN", "Higiene", "6.30", "8.99", "10.5", 100,
						"0109DA1JZP", "Corredor A Prateleira 10", LocalDate.of(2023, 6, 30)));
		products.add(
				createProduct("ABSORVENTE INTIMUS GEL SUPER 8UN", "Higiene", "4.30", "6.49", "10.5", 100, "0109DA1JZR",
						"Corredor A Prateleira 10", LocalDate.of(2023, 6, 30)));
		products.add(createProduct("ABSORVENTE INTIMUS GEL SUUPER C/ABAS 8UN", "Higiene", "5.40", "7.49", "10.5", 100,
				"0109DA1JZT", "Corredor A Prateleira 10", LocalDate.of(2023, 6, 30)));
		products.add(
				createProduct("ABSORVENTE INTIMUS GEL ULTRA FINO NOTURNO 8UN", "Higiene", "6.90", "9.99", "10.5", 100,
						"0109DA1JZV", "Corredor A Prateleira 10", LocalDate.of(2023, 6, 30)));
		List<ProductModel> convertedProducts = convertToProductModel(products);
		// Salvar a lista de produtos
		productRepository.saveAll(convertedProducts);
	}

	private ProductDto createProduct(String name, String type, String price, String cost, String competitionPrice,
			int quantityInStock, String barCode, String physicalPosition, LocalDate expirationDate) {
		ProductDto product = new ProductDto();
		product.setName(name);
		product.setType(type);
		product.setPrice(new BigDecimal(price));
		product.setCost(new BigDecimal(cost));
		product.setCompetitionPrice(new BigDecimal(competitionPrice));
		product.setQuantityInStock(quantityInStock);
		product.setBarCode(barCode);
		product.setPhysicalPosition(physicalPosition);
		product.setExpirationDate(expirationDate);
		return product;
	}

	private List<ProductModel> convertToProductModel(List<ProductDto> products) {
		List<ProductModel> convertedProducts = new ArrayList<>();
		for (ProductDto productDto : products) {
			ProductModel product = new ProductModel();
			BeanUtils.copyProperties(productDto, product);
			convertedProducts.add(product);
		}
		return convertedProducts;
	}

}
