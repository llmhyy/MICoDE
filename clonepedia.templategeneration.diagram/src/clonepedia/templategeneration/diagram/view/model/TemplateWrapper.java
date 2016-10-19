package clonepedia.templategeneration.diagram.view.model;

import java.util.ArrayList;

import clonepedia.model.template.CandidateTemplate;

public class TemplateWrapper {
	private ArrayList<TMGWrapper> tmgWrapperList = new ArrayList<TMGWrapper>();
	private CandidateTemplate candidateTemplate;

	public TemplateWrapper(CandidateTemplate tfgList) {
		super();
		this.candidateTemplate = tfgList;
	}

	public ArrayList<TMGWrapper> getTmgWrapperList() {
		return tmgWrapperList;
	}

	public void setTmgWrapperList(ArrayList<TMGWrapper> tmgWrapperList) {
		this.tmgWrapperList = tmgWrapperList;
	}

	public CandidateTemplate getCandidateTemplate() {
		return candidateTemplate;
	}

	public void setCandidateTemplate(CandidateTemplate tfgList) {
		this.candidateTemplate = tfgList;
	}

}
