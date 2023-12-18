package com.ecommerce_project.product_service.FakeStore;

import com.ecommerce_project.product_service.Adapters.IThirdPartyAdapter;
import com.ecommerce_project.product_service.Adapters.IThirdPartyRequestDTO;
import com.ecommerce_project.product_service.Adapters.IThirdPartyResponseDTO;
import com.ecommerce_project.product_service.Models.Product;

import java.util.List;

public class FakeStoreAdapter implements IThirdPartyAdapter {
    @Override
    public List<IThirdPartyResponseDTO> getAllProducts() {
        return null;
    }

    @Override
    public IThirdPartyResponseDTO getProductById(String id) {
        return null;
    }

    @Override
    public List<IThirdPartyResponseDTO> getTopXResults(String limit) {
        return null;
    }

    @Override
    public List<IThirdPartyResponseDTO> getSortedResults(String sortOrder) {
        return null;
    }

    @Override
    public List<IThirdPartyResponseDTO> getAllProductsInOneCategory(String category) {
        return null;
    }

    @Override
    public IThirdPartyResponseDTO addNewProduct(IThirdPartyRequestDTO requestDTO) {
        return null;
    }

    @Override
    public IThirdPartyResponseDTO updateAProduct(IThirdPartyRequestDTO requestDTO) {
        return null;
    }

    @Override
    public IThirdPartyResponseDTO deleteAProduct(IThirdPartyRequestDTO requestDTO) {
        return null;
    }

    @Override
    public List<IThirdPartyResponseDTO> getAllCategories() {
        return null;
    }

    @Override
    public Product convertDtoToEntity(IThirdPartyResponseDTO responseDTO) {
        return null;
    }

    @Override
    public IThirdPartyRequestDTO convertEntityToDto(Product product) {
        return null;
    }
}
