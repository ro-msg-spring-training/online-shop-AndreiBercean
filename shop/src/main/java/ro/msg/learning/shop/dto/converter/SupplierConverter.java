package ro.msg.learning.shop.dto.converter;


import ro.msg.learning.shop.dto.SupplierDTO;
import ro.msg.learning.shop.model.Supplier;

public class SupplierConverter {
    public static SupplierDTO generateDTOFromEntity(Supplier supplier){
        return new SupplierDTO();
    }
}
