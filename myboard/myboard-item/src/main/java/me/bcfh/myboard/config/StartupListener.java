package me.bcfh.myboard.config;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.jboss.logging.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import me.bcfh.myboard.model.Item;

@Component
public class StartupListener implements ApplicationListener<ContextRefreshedEvent> {

    private Logger log = Logger.getLogger(getClass());

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public void onApplicationEvent(ContextRefreshedEvent arg0) {
	log.info("Application started");
	FullTextEntityManager fem = Search.getFullTextEntityManager(em);
	try {
	    fem.createIndexer(Item.class).startAndWait();
	} catch (InterruptedException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

}
