package net.CCweb;

import java.util.ArrayList;

import org.semanticweb.owlapi.model.IRI;

public class ontologyClass {
	String className_en;
	String className_ar;
	String definition;
	String comment;
	IRI iri;
    ArrayList<ontologyClass> instances;
	
	
	public ontologyClass() {
		super();
		instances=new ArrayList<ontologyClass>();
		className_en="";
		className_en="";
		comment="";
		definition="";
		iri=IRI.create("");
		// TODO Auto-generated constructor stub
	}
	public ArrayList<ontologyClass> getInstances() {
		return instances;
	}
	public void setInstances(ArrayList<ontologyClass> instances1) {
		
//		for(int i=0;instances1.size()>i;i++) {
//			instances.add(instances1.get(i));
//		}
		this.instances=instances1;
		
	}
	public String getClassName_en() {
		return className_en;
	}
	public void setClassName_en(String className_en) {
		this.className_en = className_en;
	}
	public String getClassName_ar() {
		return className_ar;
	}
	public void setClassName_ar(String className_ar) {
		this.className_ar = className_ar;
	}
	public String getDefinition() {
		return definition;
	}
	public void setDefinition(String definition) {
		this.definition = definition;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	@Override
	public String toString() {
		return "ontologyClass [className_en=" + className_en + ", className_ar=" + className_ar +"\n" +", definition="
				+ definition + ", comment=" + comment +"\n"+ ", iri=" + iri.getShortForm() +"\n"+ ", instances=" + instances + "]";
	}
	public IRI getIri() {
		return iri;
	}
	public String getIriShortForm() {
		return iri.getShortForm().toString();
	}
	public void setIri(IRI iri) {
		this.iri = iri;
	}
	

}
