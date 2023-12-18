package com.ecommerce_project.product_service.FakeStore;

import com.ecommerce_project.product_service.Adapters.IThirdPartyRequestDTO;
import lombok.Data;

@Data
public class FakeStoreRequestDTO extends IThirdPartyRequestDTO {
    private String title;
    private String price;
    private String description;
    private String image;
    private String category;
}
