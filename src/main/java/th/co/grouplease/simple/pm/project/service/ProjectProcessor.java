package th.co.grouplease.simple.pm.project.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;
import th.co.grouplease.simple.pm.project.event.ProjectCreatedEvent;
import th.co.grouplease.simple.pm.project.event.ProjectDeletedEvent;

@Service
public class ProjectProcessor {
    private final Logger logger = LoggerFactory.getLogger(ProjectProcessor.class);

    @TransactionalEventListener
    public void on(ProjectCreatedEvent event){
        logger.info("Project created {}.", event);
    }

    @TransactionalEventListener
    public void on(ProjectDeletedEvent event){
        logger.info("Project deleted {}.", event);
    }
}
