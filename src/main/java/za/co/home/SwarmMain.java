package za.co.home;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.ClassLoaderAsset;
import org.wildfly.swarm.Swarm;
import org.wildfly.swarm.datasources.DatasourcesFraction;
import org.wildfly.swarm.jaxrs.JAXRSArchive;
import org.wildfly.swarm.jpa.JPAFraction;
import za.co.home.greeting.GreetUser;
import za.co.home.security.AuthenticationService;

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

        swarm.fraction(new JPAFraction()
                .defaultDatasource("jboss/datasources/MyDS")
        );
        swarm.start();

        JAXRSArchive deployment = ShrinkWrap.create(JAXRSArchive.class);
        ClassLoader classLoader = SwarmMain.class.getClassLoader();
        deployment.addAsWebInfResource(new ClassLoaderAsset("META-INF/persistence.xml", classLoader), "classes/META-INF/persistence.xml");
        deployment.addPackages(true, "za.co.home");
        deployment.addResource(Ping.class);
        deployment.addResource(GreetUser.class);
        deployment.addResource(AuthenticationService.class);
        deployment.addAllDependencies();
        swarm.deploy(deployment);

    }
}