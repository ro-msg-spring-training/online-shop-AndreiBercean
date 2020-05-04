package ro.msg.learning.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.model.Supplier;
import ro.msg.learning.shop.repository.SupplierRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SupplierService {

    private final SupplierRepository supplierRepository;

    public Supplier findById(Supplier supplier){
        Optional<Supplier> supplierFound  = supplierRepository.findById(supplier.getSupplierId());
        return supplierFound.orElseGet(Supplier::new);
    }

    public List<Supplier> findAll(){
        return supplierRepository.findAll();
    }

    public Supplier insert(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    public Supplier update(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    public Supplier delete(Supplier supplier) {
        supplierRepository.deleteById(supplier.getSupplierId());
        return supplier;
    }
}
