package hcl_coding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class EmployeeFirstNameComparator implements Comparator<Employee> {

	@Override
	public int compare(Employee emp1, Employee emp2) {
		return emp1.getFirstName().compareTo(emp2.getFirstName());
	}
}

class EmployeeLastNameComparator implements Comparator<Employee> {

	@Override
	public int compare(Employee emp1, Employee emp2) {
		return emp1.getLastName().compareTo(emp2.getLastName());
	}
}

class EmployeeAgeComparator implements Comparator<Employee> {

	@Override
	public int compare(Employee emp1, Employee emp2) {
		return emp1.getAge().compareTo(emp2.getAge());
	}
}

class EmployeeCountryComparator implements Comparator<Employee> {

	@Override
	public int compare(Employee emp1, Employee emp2) {
		return emp1.getCountry().compareTo(emp2.getCountry());
	}
}

public class SortEmployeeByAnyDifferentOptions {
	public void sortEmployeeData(List<Employee> empData, String sortParams[]) {
		if (empData != null && empData.size() > 0 && sortParams != null && sortParams.length > 0) {
			List<Comparator<Employee>> allComparators = new ArrayList<Comparator<Employee>>();

			List<String> params = Arrays.asList(sortParams);
			if (params.stream().anyMatch(x -> x.equalsIgnoreCase("firstName"))) {
				allComparators.add(new EmployeeFirstNameComparator());
			}
			if (params.stream().anyMatch(x -> x.equalsIgnoreCase("lastName"))) {
				allComparators.add(new EmployeeLastNameComparator());
			}
			if (params.stream().anyMatch(x -> x.equalsIgnoreCase("age"))) {
				allComparators.add(new EmployeeAgeComparator());
			}
			if (params.stream().anyMatch(x -> x.equalsIgnoreCase("country"))) {
				allComparators.add(new EmployeeCountryComparator());
			}

			EmployeeChainedComparator employeeChainedComparator = new EmployeeChainedComparator(allComparators);
			Collections.sort(empData, employeeChainedComparator);
			for (Employee e : empData)
				System.out.println(e.toString());

			/*
			 * Comparator<Employee> compareByName =
			 * Comparator.comparing(Employee::getFirstName)
			 * .thenComparing(Employee::getAge);
			 * 
			 * List<Employee> sortedEmployees =
			 * empData.stream().sorted(compareByName).collect(Collectors.toList());
			 * System.out.println("***************"); for (Employee e : sortedEmployees)
			 * System.out.println(e.toString());
			 */
		}
	}

	public static void main(String args[]) {
		List<Employee> listOfEmps = new ArrayList<Employee>();
		//provide list of employees to sort the data
		listOfEmps.add(new Employee("Gopal", "Mukhee", 28, "India"));
		listOfEmps.add(new Employee("Tanmai", "Mukhee", 3, "India"));
		listOfEmps.add(new Employee("Teju", "Mukhee", 3, "India"));
		listOfEmps.add(new Employee("Sowjanya", "Mukhee", 24, "India"));
		// just pass the params to sort the employee list
		String arr[] = { "firstName", "age" };
		SortEmployeeByAnyDifferentOptions s = new SortEmployeeByAnyDifferentOptions();
		s.sortEmployeeData(listOfEmps, arr);
	}
}

class EmployeeChainedComparator implements Comparator<Employee> {

	private List<Comparator<Employee>> listComparators;

	public EmployeeChainedComparator() {
	}

	public EmployeeChainedComparator(List<Comparator<Employee>> comparators) {
		this.listComparators = comparators;
	}

	@Override
	public int compare(Employee emp1, Employee emp2) {
		for (Comparator<Employee> comparator : listComparators) {
			int result = comparator.compare(emp1, emp2);
			if (result != 0) {
				return result;
			}
		}
		return 0;
	}
}

class Employee {
	private String firstName;
	private String lastName;
	private Integer age;
	private String country;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Employee() {

	}

	public Employee(String firstName, String lastName, Integer age, String country) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.country = country;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (age != other.age)
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Employee [firstName=" + firstName + ", lastName=" + lastName + ", age=" + age + ", country=" + country
				+ "]";
	}

}
