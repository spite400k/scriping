package com.spite.scriping;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sc_wikipedia")
public class DumpEntry implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Date gettime;
	private String id;
	private String ref;
	private String status;

	public DumpEntry() {
	}
	
	public DumpEntry(Date gettime, String id, String ref, String status) {
		this.gettime = gettime;
		this.id = id;
		this.ref = ref;
		this.status = status;
	}

	public Date getTGettime() {
		return gettime;
	}

	public String getId() {
		return id;
	}

	public String getRef() {
		return ref;
	}

	public String getStatus() {
		return status;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof DumpEntry)) return false;

		DumpEntry dumpEntry = (DumpEntry) o;

		if (!id.equals(dumpEntry.id)) return false;
		if (!ref.equals(dumpEntry.ref)) return false;
		if (!status.equals(dumpEntry.status)) return false;
		if (!gettime.equals(dumpEntry.gettime)) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = gettime.hashCode();
		result = 31 * result + id.hashCode();
		result = 31 * result + ref.hashCode();
		result = 31 * result + status.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return "DumpEntry{" +
				"timestamp=" + gettime +
				", id='" + id + '\'' +
				", ref='" + ref + '\'' +
				", status='" + status + '\'' +
				'}';
	}
}
