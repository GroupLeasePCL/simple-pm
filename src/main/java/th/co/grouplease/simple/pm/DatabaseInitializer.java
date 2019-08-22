package th.co.grouplease.simple.pm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import th.co.grouplease.simple.pm.product.Product;
import th.co.grouplease.simple.pm.product.ProductRelease;
import th.co.grouplease.simple.pm.product.ProductReleaseRepository;
import th.co.grouplease.simple.pm.product.ProductRepository;
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

@Component
public class DatabaseInitializer implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductReleaseRepository productReleaseRepository;
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
        var productGL = productRepository.save(Product.create("GL", LocalDate.of(2000, 1, 1)));
        var productTels = productRepository.save(Product.create("Tels", LocalDate.of(2005, 1, 1)));
        var productFinWiz = productRepository.save(Product.create("FinWiz", LocalDate.of(2018, 9, 1)));

        productReleaseRepository.save(ProductRelease.create(productGL, "2.5.0", LocalDate.of(2019, 1, 1)));
        productReleaseRepository.save(ProductRelease.create(productGL, "2.5.1", LocalDate.of(2019, 2, 1)));

        productReleaseRepository.save(ProductRelease.create(productTels, "2.0.0", LocalDate.of(2019, 1, 1)));
        productReleaseRepository.save(ProductRelease.create(productTels, "2.0.1", LocalDate.of(2019, 2, 1)));

        productReleaseRepository.save(ProductRelease.create(productFinWiz, "1.0.0", LocalDate.of(2019, 1, 1)));
        productReleaseRepository.save(ProductRelease.create(productFinWiz, "1.1.0", LocalDate.of(2019, 2, 1)));

        // Initialize projects
        var revampCollectionProject = Project.create("Revamp collection")
                .withProduct(productFinWiz);
        var revampCustodianProject = Project.create("Revamp custodian")
                .withProduct(productFinWiz);
        projectRepository.save(revampCollectionProject);
        projectRepository.save(revampCustodianProject);

        var pdpaProject = Project.create("Privacy data protection act");
        projectRepository.save(pdpaProject);

        // Initialize resources and teams
        var softwareDevelopmentTeam = ResourceTeam.create("Software development");
        resourceTeamRepository.save(softwareDevelopmentTeam);
        var atip = Resource.create("atip", "Atip", "Choowisetwanitch", "44428", LocalDate.of(2018, 1, 1))
                .withTeam(softwareDevelopmentTeam);
        var suchawadee = Resource.create("suchawadee", "Suchawadee", "Sinma", "53773", LocalDate.of(2017, 1, 1))
                .withTeam(softwareDevelopmentTeam);
        resourceRepository.save(atip);
        resourceRepository.save(suchawadee);

        var govTeam = ResourceTeam.create("Governance");
        resourceTeamRepository.save(govTeam);
        var endoo = Resource.create("endoo", "Endoo", "Akarum", "64345", LocalDate.of(2018, 1, 1))
                .withTeam(govTeam);
        resourceRepository.save(endoo);

        // Initialize working entries
        workingEntryRepository.save(WorkingEntry.create(atip, TypeOfWork.PROJECT,
                LocalDate.of(2019, 1, 1))
                .withProject(revampCollectionProject));

        workingEntryRepository.save(WorkingEntry.create(endoo, TypeOfWork.PROJECT,
                LocalDate.of(2019, 1, 1))
                .withProject(pdpaProject));

        workingEntryRepository.save(WorkingEntry.create(atip, TypeOfWork.PROJECT,
                LocalDate.of(2019, 1, 2))
                .withProject(revampCustodianProject));

        workingEntryRepository.save(WorkingEntry.create(suchawadee, TypeOfWork.SUPPORT,
                LocalDate.of(2019, 1, 2)));
    }
}
