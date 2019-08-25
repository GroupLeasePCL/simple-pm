package th.co.grouplease.simple.pm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import th.co.grouplease.simple.pm.product.domain.model.Product;
import th.co.grouplease.simple.pm.product.domain.model.ProductRelease;
import th.co.grouplease.simple.pm.product.repository.ProductReleaseRepository;
import th.co.grouplease.simple.pm.product.repository.ProductRepository;
import th.co.grouplease.simple.pm.project.command.CreateProjectCommand;
import th.co.grouplease.simple.pm.project.service.ProjectService;
import th.co.grouplease.simple.pm.resource.domain.model.Resource;
import th.co.grouplease.simple.pm.resource.repository.ResourceRepository;
import th.co.grouplease.simple.pm.resource.domain.model.ResourceTeam;
import th.co.grouplease.simple.pm.resource.repository.ResourceTeamRepository;
import th.co.grouplease.simple.pm.workinglog.domain.model.TypeOfWork;
import th.co.grouplease.simple.pm.workinglog.domain.model.WorkingEntry;
import th.co.grouplease.simple.pm.workinglog.repository.WorkingEntryRepository;

import java.time.LocalDate;
import java.util.UUID;

@Component
public class DatabaseInitializer implements CommandLineRunner {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductReleaseRepository productReleaseRepository;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private ResourceTeamRepository resourceTeamRepository;
    @Autowired
    private ResourceRepository resourceRepository;
    @Autowired
    private WorkingEntryRepository workingEntryRepository;

    @Override
    public void run(String... args) {
        // Initialize products and releases
        var productGL = productRepository.save(
                new Product("GL", LocalDate.of(2000, 1, 1), null));
        var productTels = productRepository.save(
                new Product("Tels", LocalDate.of(2005, 1, 1), null));
        var productFinWiz = productRepository.save(
                new Product("FinWiz", LocalDate.of(2018, 9, 1), null));

        productReleaseRepository.save(
                new ProductRelease(productGL, "2.5.0", LocalDate.of(2019, 1, 1)));
        productReleaseRepository.save(
                new ProductRelease(productGL, "2.5.1", LocalDate.of(2019, 2, 1)));

        productReleaseRepository.save(
                new ProductRelease(productTels, "2.0.0", LocalDate.of(2019, 1, 1)));
        productReleaseRepository.save(
                new ProductRelease(productTels, "2.0.1", LocalDate.of(2019, 2, 1)));

        productReleaseRepository.save(
                new ProductRelease(productFinWiz, "1.0.0", LocalDate.of(2019, 1, 1)));
        productReleaseRepository.save(
                new ProductRelease(productFinWiz, "1.1.0", LocalDate.of(2019, 2, 1)));

        // Initialize projects
        var revampCollectionProject = projectService.createProject(new CreateProjectCommand(UUID.randomUUID().toString(), "Revamp collection", productFinWiz.getId()));
        var revampCustodianProject = projectService.createProject(new CreateProjectCommand(UUID.randomUUID().toString(), "Revamp custodian", productFinWiz.getId()));
        var pdpaProject = projectService.createProject(new CreateProjectCommand(UUID.randomUUID().toString(), "Privacy data protection act", null));

        // Initialize resources and teams
        var softwareDevelopmentTeam = new ResourceTeam("Software development");
        resourceTeamRepository.save(softwareDevelopmentTeam);
        var atip = new Resource(softwareDevelopmentTeam, "atip", "Atip", "Choowisetwanitch",
                "44428", LocalDate.of(2018, 1, 1), null);
        var suchawadee = new Resource(softwareDevelopmentTeam, "suchawadee", "Suchawadee", "Sinma",
                "53773", LocalDate.of(2017, 1, 1), null);
        resourceRepository.save(atip);
        resourceRepository.save(suchawadee);

        var govTeam = new ResourceTeam("Governance");
        resourceTeamRepository.save(govTeam);
        var endoo = new Resource(govTeam, "endoo", "Endoo", "Akarum",
                "64345", LocalDate.of(2018, 1, 1), null);
        resourceRepository.save(endoo);

        // Initialize working entries
        workingEntryRepository.save(new WorkingEntry(atip, TypeOfWork.PROJECT, revampCollectionProject, null,
                LocalDate.of(2019, 1, 1)));

        workingEntryRepository.save(new WorkingEntry(suchawadee, TypeOfWork.PROJECT, revampCollectionProject, null,
                LocalDate.of(2019, 1, 1)));

        workingEntryRepository.save(new WorkingEntry(endoo, TypeOfWork.PROJECT, pdpaProject, null,
                LocalDate.of(2019, 1, 1)));

        workingEntryRepository.save(new WorkingEntry(atip, TypeOfWork.PROJECT, revampCustodianProject, null,
                LocalDate.of(2019, 1, 2)));

        workingEntryRepository.save(new WorkingEntry(suchawadee, TypeOfWork.SUPPORT, null, null,
                LocalDate.of(2019, 1, 2)));
    }
}
