package com.apachesolr.example.repository;

import com.apachesolr.example.models.Product;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.solr.core.RequestMethod;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.convert.MappingSolrConverter;
import org.springframework.data.solr.core.mapping.SimpleSolrMappingContext;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@ImportResource("classpath:spring-solr.xml")
public class ExampleRepository {
    @Autowired
    SolrClient solrClient;

    SolrTemplate solrTemplate;

    @Value("${solr.collection.name}")
    String collectionName;

    @PostConstruct
    public void init() {
        solrTemplate = new SolrTemplate(solrClient);
        solrTemplate.setSolrConverter(new MappingSolrConverter(new SimpleSolrMappingContext()));
    }



    public Product saveProduct(Product product) {
        try {
            UpdateResponse updateResponse = solrTemplate.saveBean(collectionName,product);
            solrTemplate.commit(collectionName);
            System.out.println("update response status: "+updateResponse.getStatus());
            return product;
        } catch (Exception e) {
            System.out.println("exception: "+e);
            return null;
        }
    }

    public Product findById(String id) {
        try {
            Optional<Product> product = solrTemplate.getById(collectionName,id, Product.class);
            if(product.isPresent()) {
                return product.get();
            }
            else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("exception: "+e);
            return null;
        }
    }

    public List<Product> findBySearchTerm(String searchTerm) {
        try {
            List<Product> productList = new ArrayList<>();
            SolrQuery solrQuery = new SolrQuery();
            solrQuery.set("q","name:"+searchTerm);
            QueryResponse queryResponse  = solrTemplate.getSolrClient().query(collectionName,solrQuery);
            SolrDocumentList solrDocumentList = queryResponse.getResults();
            for(SolrDocument solrDocument: solrDocumentList) {
               Product product = solrTemplate.convertSolrDocumentToBean(solrDocument, Product.class);
               productList.add(product);
            }
            return productList;
        } catch (Exception e) {
            System.out.println("exception: "+e);
            return null;
        }
    }
}
