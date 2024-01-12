package com.siti.ecommerce.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.siti.ecommerce.entity.Country;
import com.siti.ecommerce.entity.Product;
import com.siti.ecommerce.entity.ProductCategory;
import com.siti.ecommerce.entity.State;

import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.EntityType;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer{

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
		
		HttpMethod[] theUnsupportedActions = {HttpMethod.PUT, HttpMethod.POST, HttpMethod.DELETE};
		
		// disable HTTP methods for Product: PUT, POST and DELETE
		disableHttpMethods(Product.class, config, theUnsupportedActions);
		
		// disable HTTP methods for ProductCategory: PUT, POST and DELETE
		disableHttpMethods(ProductCategory.class, config, theUnsupportedActions);
		
		// disable HTTP methods for Country: PUT, POST and DELETE
		disableHttpMethods(Country.class, config, theUnsupportedActions);

		// disable HTTP methods for State: PUT, POST and DELETE
		disableHttpMethods(State.class, config, theUnsupportedActions);
		
		// call an internal helper method
		exposeIds(config);
 		
	}

	private void disableHttpMethods(Class theClass, RepositoryRestConfiguration config, HttpMethod[] theUnsupportedActions) {
		config.getExposureConfiguration()
			.forDomainType(theClass)
			.withItemExposure((metdata, httpmethods) -> httpmethods.disable(theUnsupportedActions))
			.withCollectionExposure((metdata, httpmethods) -> httpmethods.disable(theUnsupportedActions));
	}

	private void exposeIds(RepositoryRestConfiguration config) {
		
		// expose entity ids
		
		// get a list of all entity classes from the entity manager
		Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();
		
		// create an array of the entity types
		List<Class> entityClasses = new ArrayList<>();
		
		// get the entity types for the entities
		for(EntityType tempEntityType: entities) {
			entityClasses.add(tempEntityType.getJavaType());
		}
		
		// expose the entity ids for the array of entity/domain types
		Class[] domainTypes = entityClasses.toArray(new Class[0]);
		config.exposeIdsFor(domainTypes);
		
	}
	
	

}
