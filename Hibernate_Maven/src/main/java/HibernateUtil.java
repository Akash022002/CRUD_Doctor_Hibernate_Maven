import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

public class HibernateUtil 
{
//apply factory design pattern here
	//create object of session factory
	public static SessionFactory factory;
	//create one static method
	public static SessionFactory getSessionFactory()
	{
		if(factory==null)
		{
			Configuration cfg=new Configuration();
			//store data in key and value pair use Properties class which is 
			//inside a util package
			Properties properties=new Properties();
			//Database Properties
			properties.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
			properties.put(Environment.URL, "jdbc:mysql://localhost:3306/hibernate_maven");
			properties.put(Environment.USER,"root");
			properties.put(Environment.PASS,"Akash@2002");
			//Hibernate Properties
			properties.put(Environment.DIALECT,"org.hibernate.dialect.MySQL57Dialect");
			properties.put(Environment.HBM2DDL_AUTO,"update");
			properties.put(Environment.SHOW_SQL,"true");
			//Add all properties into Configuration class
			cfg.setProperties(properties);
			//Add Mapping Class
			cfg.addAnnotatedClass(Doctor.class);
			//to create object of session factory we required a method
			StandardServiceRegistry registry=new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
			factory=cfg.buildSessionFactory(registry);
					}
		return factory;
	}
	
}