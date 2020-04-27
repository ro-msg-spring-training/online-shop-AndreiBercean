package ro.msg.learning.shop.dto.converter;

import ro.msg.learning.shop.dto.StockDTO;
import ro.msg.learning.shop.model.Location;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.model.Stock;
import ro.msg.learning.shop.model.StockID;

public class StockConverter {
    public static StockDTO generateDTOFromEntity(Stock stock){
        return new StockDTO(
                ProductConverter.generateDTOFromEntity(stock.getProduct()),
                LocationConverter.generateDTOFromEntity(stock.getLocation()),
                stock.getQuantity()
        );
    }

    public static Stock generateEntityFromDTO(StockDTO stockDTO){
        Location location = LocationConverter.generateEntityFromDTO(stockDTO.getLocationDTO());
        Product product = ProductConverter.generateEntityFromDTO(stockDTO.getProductDTO());
        return new Stock(
                new StockID(product.getProductId(), location.getLocationId()),
                stockDTO.getQuantity(),
                product,
                location
        );
    }
}
