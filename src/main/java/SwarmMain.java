import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.wildfly.swarm.Swarm;
import org.wildfly.swarm.datasources.DatasourcesFraction;
import org.wildfly.swarm.jaxrs.JAXRSArchive;

public class SwarmMain {

    public static void main(String[] args) throws Exception {
        Swarm swarm = new Swarm()
                .fraction(new DatasourcesFraction()
                        .jdbcDriver("com.mysql", (d) -> {
                            d.driverClassName("com.mysql.jdbc.Driver");
                            d.xaDatasourceClass("com.mysql.jdbc.jdbc2.optional.MysqlXADataSource");
                            d.driverModuleName("com.mysql");
                        })
                        .dataSource("MyDS", (ds) -> {
                            ds.driverName("com.mysql");
                            ds.connectionUrl("jdbc:mysql://localhost:3306/DBHOME1?useSSL=false&autoReconnect=true");
                            ds.userName("jonathan");
                            ds.password("***REMOVED***");
                        }));

        swarm.start();

        JAXRSArchive deployment = ShrinkWrap.create(JAXRSArchive.class);

        ClassLoader classLoader = SwarmMain.class.getClassLoader();
        deployment.addAsWebInfResource(classLoader.getResource("persistence.xml"), "META-INF/persistence.xml");
        deployment.addPackages(true, Package.getPackages());
        deployment.addAllDependencies();

        swarm.deploy(deployment);

    }
}