package hcl_coding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class ElectionResults {
	public static void main(String args[]) throws ParseException {
		// providing the candidates information for kharadi location
		String candidate1Birthdate = "31/12/1998";
		String candidate2Birthdate = "31/12/1998";
		String candidate3Birthdate = "31/12/1998";
		Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(candidate1Birthdate);
		Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse(candidate2Birthdate);
		Date date3 = new SimpleDateFormat("dd/MM/yyyy").parse(candidate3Birthdate);
		Candidate candidate1 = new Candidate("Gopi", "Mukhee", 28, date1, 20000);
		Candidate candidate2 = new Candidate("Gopalarao", "Mukhee", 28, date2, 10000);
		Candidate candidate3 = new Candidate("Gopal", "Mukhee", 28, date3, 30000);
		List<Candidate> candidatesForRegion1 = new ArrayList<Candidate>();
		candidatesForRegion1.add(candidate1);
		candidatesForRegion1.add(candidate2);
		candidatesForRegion1.add(candidate3);
		Region region1 = new Region();
		region1.setRegionName("kharadi");
		region1.setCandidates(candidatesForRegion1);
		List<Region> regions = new ArrayList<Region>();
		regions.add(region1);

		// providing the candidates information for magarpatta location
		List<Candidate> candidatesForRegion2 = new ArrayList<Candidate>();
		Candidate candidate4 = new Candidate("Gopi", "Mukhee", 28, date1, 40000);
		Candidate candidate5 = new Candidate("Gopalarao", "Mukhee", 30, date2, 50000);
		Candidate candidate6 = new Candidate("Gopal", "Mukhee", 23, date3, 10000);
		Candidate candidate7 = new Candidate("Gopala", "Mukhee", 26, date3, 1000);
		candidatesForRegion2.add(candidate4);
		candidatesForRegion2.add(candidate5);
		candidatesForRegion2.add(candidate6);
		candidatesForRegion2.add(candidate7);
		Region region2 = new Region();
		region2.setRegionName("magarpatta");
		region2.setCandidates(candidatesForRegion2);
		regions.add(region2);
		Election election = new Election();
		election.setElectionDate(new Date());
		election.setRegions(regions);
		//finding winners
		findElectionWinners(election);
	}

	private static void findElectionWinners(Election election) {
		Comparator<Candidate> compareByVotes = Comparator.comparing(Candidate::getVotes);
		Comparator<Candidate> compareByVotesAndFirstName = compareByVotes.thenComparing(Candidate::getFirstName);
		Comparator<Candidate> compareByVotesAndFirstNameAndBirthdate = compareByVotesAndFirstName
				.thenComparing(Candidate::getDateOfBirth);
		if (election != null && election.getRegions() != null && election.getRegions().size() > 0)
			for (Region region : election.getRegions()) {
				Optional<Candidate> winner = region.getCandidates().stream()
						.max(compareByVotesAndFirstNameAndBirthdate);
				if (winner.isPresent()) {
					Candidate winnerCandidate = winner.get();
					System.out.println(
							"Region: " + region.getRegionName() + " Winner Name: " + winnerCandidate.getFirstName()
									+ " " + winnerCandidate.getLastName() + " Votes:" + winnerCandidate.getVotes());
				}
			}

	}
}

class Election {
	private Date electionDate;
	private List<Region> regions;

	public Date getElectionDate() {
		return electionDate;
	}

	public void setElectionDate(Date electionDate) {
		this.electionDate = electionDate;
	}

	public List<Region> getRegions() {
		return regions;
	}

	public void setRegions(List<Region> regions) {
		this.regions = regions;
	}

	public Election() {
	}

	public Election(Date electionDate, List<Region> regions) {
		this.electionDate = electionDate;
		this.regions = regions;
	}

}

class Region {
	private String regionName;
	private List<Candidate> candidates;

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public List<Candidate> getCandidates() {
		return candidates;
	}

	public void setCandidates(List<Candidate> candidates) {
		this.candidates = candidates;
	}

	public Region(String regionName, List<Candidate> candidates) {
		this.regionName = regionName;
		this.candidates = candidates;
	}

	public Region() {

	}

}

class Candidate {
	private String firstName;
	private String lastName;
	private Integer age;
	private Date dateOfBirth;
	private Integer votes;

	public Candidate(String firstName, String lastName, Integer age, Date dateOfBirth, Integer votes) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.dateOfBirth = dateOfBirth;
		this.votes = votes;
	}

	public Candidate() {

	}

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

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Integer getVotes() {
		return votes;
	}

	public void setVotes(Integer votes) {
		this.votes = votes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((age == null) ? 0 : age.hashCode());
		result = prime * result + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((votes == null) ? 0 : votes.hashCode());
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
		Candidate other = (Candidate) obj;
		if (age == null) {
			if (other.age != null)
				return false;
		} else if (!age.equals(other.age))
			return false;
		if (dateOfBirth == null) {
			if (other.dateOfBirth != null)
				return false;
		} else if (!dateOfBirth.equals(other.dateOfBirth))
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
		if (votes == null) {
			if (other.votes != null)
				return false;
		} else if (!votes.equals(other.votes))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Candidates [firstName=" + firstName + ", lastName=" + lastName + ", age=" + age + ", dateOfBirth="
				+ dateOfBirth + ", votes=" + votes + "]";
	}

}