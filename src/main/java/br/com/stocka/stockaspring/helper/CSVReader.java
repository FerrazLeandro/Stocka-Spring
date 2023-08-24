package br.com.stocka.stockaspring.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import br.com.stocka.stockaspring.dto.ProductDto;

public class CSVReader {

    public static List<ProductDto> parseProducts(InputStream inputStream) throws IOException {
        List<ProductDto> products = new ArrayList<>();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            // Variável para identificar a primeira linha
            boolean isFirstLine = true; 
            while ((line = reader.readLine()) != null) {
                // Pula para a próxima iteração do loop se for um header
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                // Separa os valores
                String[] fields = line.split(",");
                // Extrair os campos do arquivo CSV e criar um objeto ProductDto
                ProductDto product = new ProductDto();
                product.setName(fields[0].isEmpty() ? "" : fields[0]);
                product.setType(fields[1].isEmpty() ? "" : fields[1]);
                product.setQuantityInStock(Integer.parseInt(fields[2].isEmpty() ? "0" : fields[2]));
                product.setPrice(new BigDecimal(fields[3].isEmpty() ? "0" : fields[3]));
                product.setCompetitionPrice(new BigDecimal(fields[4].isEmpty() ? "0" : fields[4]));
                product.setCost(new BigDecimal(fields[5].isEmpty() ? "0" : fields[5]));
                product.setPhysicalPosition(fields[6].isEmpty() ? "" : fields[6]);
                product.setBarCode(fields[7].isEmpty() ? "" : fields[7]);
                product.setExpirationDate(LocalDate.parse(fields[8].isEmpty() ? "01/01/1999" : fields[8], dateFormatter));
                // Salva o produto na lista temporária
                products.add(product);
            }
        }
        // retorna a lista completa
        return products;
    }
}
