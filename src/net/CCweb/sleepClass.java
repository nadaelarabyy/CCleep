package net.CCweb;

import org.semanticweb.owlapi.model.IRI;

public class sleepClass {
	private int sleepHygieneScore;
	private int sleepQualityScore;
	private double calories;
	private double METValues;
	private IRI className;
	
	public double getCalories() {
		return calories;
	}
	public void setCalories(double calories) {
		this.calories = calories;
	}
	public double getMETValues() {
		return METValues;
	}
	public void setMETValues(double mETValues) {
		METValues = mETValues;
	}
	
	@Override
	public String toString() {
		return "sleepClass [sleepHygieneScore=" + sleepHygieneScore + ", sleepQualityScore=" + sleepQualityScore
				+ ", calories=" + calories + ", METValues=" + METValues + ", className=" + className + "] \n";
	}
	public sleepClass() {
		super();
		// TODO Auto-generated constructor stub
		sleepHygieneScore=0;
		sleepQualityScore=0;
		calories=0;
		METValues=0;
	}
	public int getSleepHygieneScore() {
		return sleepHygieneScore;
	}
	public void setSleepHygieneScore(int sleepHygieneScore) {
		this.sleepHygieneScore = sleepHygieneScore;
	}
	public int getSleepQualityScore() {
		return sleepQualityScore;
	}
	public void setSleepQualityScore(int sleepQualityScore) {
		this.sleepQualityScore = sleepQualityScore;
	}
	public IRI getClassName() {
		return className;
	}
	public void setClassName(IRI className) {
		this.className = className;
	}


}
