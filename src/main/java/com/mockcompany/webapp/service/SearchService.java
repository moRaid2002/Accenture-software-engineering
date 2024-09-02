package com.mockcompany.webapp.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mockcompany.webapp.data.ProductItemRepository;
import com.mockcompany.webapp.model.ProductItem;

@Service
public class SearchService {
     
    private final ProductItemRepository productItemRepository;

    @Autowired
    public SearchService(ProductItemRepository productItemRepository) {
        this.productItemRepository = productItemRepository;
    }

    public Collection<ProductItem> search (String query){

        Iterable<ProductItem> allItems = this.productItemRepository.findAll();
        List<ProductItem> itemList = new ArrayList<>();
        boolean isExactMatch = query.startsWith("\"") && query.endsWith("\"");
        if (isExactMatch) query = query.substring(1, query.length()-1);

        // This is a loop that the code inside will execute on each of the items from the database.
        for (ProductItem item : allItems) {
            // TODO: Figure out if the item should be returned based on the query parameter!
            String name = item.getName().toLowerCase();
            String des = item.getDescription().toLowerCase();
            String lowerQuery = query.toLowerCase();
            if(isExactMatch){
                if (name.equals(lowerQuery) || des.equals(lowerQuery)) itemList.add(item);
            }else{
                if(name.contains(lowerQuery)|| des.contains(lowerQuery)) itemList.add(item);
            }
            
            
            
        }
        return itemList;
    }
    
}
