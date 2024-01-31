import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Doctor {

	@Id
	private int did;
	private String dname;
	private int salary;
	
	@Override
	public String toString() {
		return "Doctor [did=" + did + ", dname=" + dname + ", salary=" + salary + "]";
	}
	
	
	public int getDid() {
		return did;
	}
	public void setDid(int did) {
		this.did = did;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	
	
}
