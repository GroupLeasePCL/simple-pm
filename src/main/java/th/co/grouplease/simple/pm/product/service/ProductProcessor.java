package th.co.grouplease.simple.pm.product.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;
import th.co.grouplease.simple.pm.product.event.ProductCreatedEvent;
import th.co.grouplease.simple.pm.product.event.ProductDeletedEvent;
import th.co.grouplease.simple.pm.product.event.ProductNameChangedEvent;
import th.co.grouplease.simple.pm.product.event.ProductTimelineChangedEvent;


@Service
public class ProductProcessor {

    private final Logger logger = LoggerFactory.getLogger(ProductProcessor.class);

    @Async
    @TransactionalEventListener
    public void on(ProductCreatedEvent event){
        logger.info("Product created {}.", event);
    }

    @Async
    @TransactionalEventListener
    public void on(ProductDeletedEvent event){
        logger.info("Product deleted {}.", event);
    }

    @Async
    @TransactionalEventListener
    public void on(ProductNameChangedEvent event){
        logger.info("Product name changed {}.", event);
    }

    @Async
    @TransactionalEventListener
    public void on(ProductTimelineChangedEvent event){
        logger.info("Product timeline changed {}.", event);
    }
}
