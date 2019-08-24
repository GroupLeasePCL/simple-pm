package th.co.grouplease.simple.pm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import th.co.grouplease.simple.pm.product.command.CreateProductCommand;
import th.co.grouplease.simple.pm.product.command.CreateProductReleaseCommand;
import th.co.grouplease.simple.pm.product.repository.ProductRepository;
import th.co.grouplease.simple.pm.product.service.ProductService;
import th.co.grouplease.simple.pm.project.Project;
import th.co.grouplease.simple.pm.project.ProjectRepository;
import th.co.grouplease.simple.pm.resource.Resource;
import th.co.grouplease.simple.pm.resource.ResourceRepository;
import th.co.grouplease.simple.pm.resource.ResourceTeam;
import th.co.grouplease.simple.pm.resource.ResourceTeamRepository;
import th.co.grouplease.simple.pm.workinglog.TypeOfWork;
import th.co.grouplease.simple.pm.workinglog.WorkingEntry;
import th.co.grouplease.simple.pm.workinglog.WorkingEntryRepository;

import java.time.LocalDate;
import java.util.UUID;

@Component
public class DatabaseInitializer implements CommandLineRunner {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private ResourceTeamRepository resourceTeamRepository;
    @Autowired
    private ResourceRepository resourceRepository;
    @Autowired
    private WorkingEntryRepository workingEntryRepository;

    @Override
    public void run(String... args) throws Exception {
        // Initialize products and releases
        var productGL = productService.createProduct(
                new CreateProductCommand(UUID.randomUUID().toString(), "GL", LocalDate.of(2000, 1, 1), null));
        var productTels = productService.createProduct(
                new CreateProductCommand(UUID.randomUUID().toString(), "Tels", LocalDate.of(2005, 1, 1), null));
        var productFinWiz = productService.createProduct(
                new CreateProductCommand(UUID.randomUUID().toString(), "FinWiz", LocalDate.of(2018, 9, 1), null));

        productService.createProductRelease(
                new CreateProductReleaseCommand(UUID.randomUUID().toString(), productGL.getId(), "2.5.0", LocalDate.of(2019, 1, 1)));
        productService.createProductRelease(
                new CreateProductReleaseCommand(UUID.randomUUID().toString(), productGL.getId(), "2.5.1", LocalDate.of(2019, 2, 1)));

        productService.createProductRelease(
                new CreateProductReleaseCommand(UUID.randomUUID().toString(), productTels.getId(), "2.0.0", LocalDate.of(2019, 1, 1)));
        productService.createProductRelease(
                new CreateProductReleaseCommand(UUID.randomUUID().toString(), productTels.getId(), "2.0.1", LocalDate.of(2019, 2, 1)));

        productService.createProductRelease(
                new CreateProductReleaseCommand(UUID.randomUUID().toString(), productFinWiz.getId(), "1.0.0", LocalDate.of(2019, 1, 1)));
        productService.createProductRelease(
                new CreateProductReleaseCommand(UUID.randomUUID().toString(), productFinWiz.getId(), "1.1.0", LocalDate.of(2019, 2, 1)));

        // Initialize projects
        var revampCollectionProject = Project.create(UUID.randomUUID().toString(), "Revamp collection")
                .withProduct(productFinWiz);
        var revampCustodianProject = Project.create(UUID.randomUUID().toString(), "Revamp custodian")
                .withProduct(productFinWiz);
        projectRepository.save(revampCollectionProject);
        projectRepository.save(revampCustodianProject);

        var pdpaProject = Project.create(UUID.randomUUID().toString(), "Privacy data protection act");
        projectRepository.save(pdpaProject);

        // Initialize resources and teams
        var softwareDevelopmentTeam = ResourceTeam.create(UUID.randomUUID().toString(), "Software development");
        resourceTeamRepository.save(softwareDevelopmentTeam);
        var atip = Resource.create(UUID.randomUUID().toString(), "atip", "Atip", "Choowisetwanitch", "44428", LocalDate.of(2018, 1, 1))
                .withTeam(softwareDevelopmentTeam);
        var suchawadee = Resource.create(UUID.randomUUID().toString(), "suchawadee", "Suchawadee", "Sinma", "53773", LocalDate.of(2017, 1, 1))
                .withTeam(softwareDevelopmentTeam);
        resourceRepository.save(atip);
        resourceRepository.save(suchawadee);

        var govTeam = ResourceTeam.create(UUID.randomUUID().toString(), "Governance");
        resourceTeamRepository.save(govTeam);
        var endoo = Resource.create(UUID.randomUUID().toString(), "endoo", "Endoo", "Akarum", "64345", LocalDate.of(2018, 1, 1))
                .withTeam(govTeam);
        resourceRepository.save(endoo);

        // Initialize working entries
        workingEntryRepository.save(WorkingEntry.create(UUID.randomUUID().toString(), atip, TypeOfWork.PROJECT,
                LocalDate.of(2019, 1, 1))
                .withProject(revampCollectionProject));

        workingEntryRepository.save(WorkingEntry.create(UUID.randomUUID().toString(), suchawadee, TypeOfWork.PROJECT,
                LocalDate.of(2019, 1, 1))
                .withProject(revampCollectionProject));

        workingEntryRepository.save(WorkingEntry.create(UUID.randomUUID().toString(), endoo, TypeOfWork.PROJECT,
                LocalDate.of(2019, 1, 1))
                .withProject(pdpaProject));

        workingEntryRepository.save(WorkingEntry.create(UUID.randomUUID().toString(), atip, TypeOfWork.PROJECT,
                LocalDate.of(2019, 1, 2))
                .withProject(revampCustodianProject));

        workingEntryRepository.save(WorkingEntry.create(UUID.randomUUID().toString(), suchawadee, TypeOfWork.SUPPORT,
                LocalDate.of(2019, 1, 2)));
    }
}
