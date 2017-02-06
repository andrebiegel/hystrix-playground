package de.abiegel.rest;

public class TransferObject {

	public static TransferObject createTransferObject(String name, String sense, boolean legal) {
		return new TransferObject(name, sense, legal);
	}

	private String name;
	private String job;
	private boolean legal;

	public TransferObject() {
		super();
	}

	private TransferObject(String name, String job, boolean legal) {
		super();
		this.name = name;
		this.job = job;
		this.legal = legal;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public boolean isLegal() {
		return legal;
	}

	public void setLegal(boolean legal) {
		this.legal = legal;
	}

	public static Object asDefault() {

		return createTransferObject("gandalf", "wizard", Boolean.FALSE);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((job == null) ? 0 : job.hashCode());
		result = prime * result + (legal ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		TransferObject other = (TransferObject) obj;
		if (job == null) {
			if (other.job != null)
				return false;
		} else if (!job.equals(other.job))
			return false;
		if (legal != other.legal)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
