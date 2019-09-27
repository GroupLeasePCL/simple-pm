package th.co.grouplease.simple.pm.service.project.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;
import th.co.grouplease.simple.pm.service.product.repository.ProductRepository;
import th.co.grouplease.simple.pm.service.project.event.ProjectCreatedEvent;
import th.co.grouplease.simple.pm.service.project.event.ProjectDeletedEvent;
import th.co.grouplease.simple.pm.service.project.event.ProjectStartedEvent;
import th.co.grouplease.simple.pm.service.project.read.model.ProjectEntry;
import th.co.grouplease.simple.pm.service.project.repository.ProjectEntryRepository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class ProjectProcessor {
    private final Logger logger = LoggerFactory.getLogger(ProjectProcessor.class);

    @Autowired
    private ProjectEntryRepository projectEntryRepository;
    @Autowired
    private ProductRepository productRepository;

    @Async
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    @TransactionalEventListener
    public void on(ProjectCreatedEvent event){
        logger.info("Project created {}.", event);
        var projectEntry = new ProjectEntry();
        projectEntry.setProjectId(event.getId());
        projectEntry.setName(event.getName());
        projectEntry.setStatus(event.getStatus());

        if(event.getProductId() != null){
            productRepository.findById(event.getProductId())
                    .ifPresent(projectEntry::setProduct);
        }

        projectEntryRepository.save(projectEntry);
    }

    @Async
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    @TransactionalEventListener
    public void on(ProjectStartedEvent event){
        logger.info("Project started {}.", event);

        Optional<ProjectEntry> optionalProjectEntry =
                projectEntryRepository.findByProjectId(event.getId());

        if(optionalProjectEntry.isPresent()){
            ProjectEntry projectEntry = optionalProjectEntry.get();
            projectEntry.setStatus(event.getStatus());
            projectEntry.setStartDate(LocalDate.now());
            projectEntryRepository.save(projectEntry);
        }
    }

    @Async
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    @TransactionalEventListener
    public void on(ProjectDeletedEvent event){
        logger.info("Project deleted {}.", event);
        projectEntryRepository.findByProjectId(event.getId())
                .ifPresent(projectEntry -> projectEntryRepository.delete(projectEntry));
    }
}
