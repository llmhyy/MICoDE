package clonepedia.templategeneration.diagram.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

import template_model.diagram.preferences.TemplateGenerationPreferencePage;
import template_model.diagram.util.AutoGenCTSettings;
import clonepedia.Activator;
import clonepedia.codegeneration.diagram.bean.Application;
import clonepedia.codegeneration.diagram.bean.DesignList;
import clonepedia.codegeneration.diagram.bean.Multiset;
import clonepedia.codegeneration.diagram.bean.TemplateDesign;
import clonepedia.codegeneration.diagram.bean.TemplateDesignComparator;
import clonepedia.filepraser.CloneDetectionFileParser;
import clonepedia.templategeneration.routine.TypeCluster;
import clonepedia.templategeneration.routine.Corresponder;
import clonepedia.templategeneration.routine.DesignBuilder;
import clonepedia.templategeneration.routine.DesignXMLReader;
import clonepedia.templategeneration.routine.DesignXMLWriter;
import clonepedia.templategeneration.routine.ProgramAbstractor;
import clonepedia.templategeneration.routine.StructureExtractor;
//import clonepedia.syntactic.util.comparator.LevenshteinPathComparator;

public class TemplateGenerationAction implements IWorkbenchWindowActionDelegate {
	@Override
	public void run(IAction action) {
		
		Job job = new Job("building template"){
			protected IStatus run(IProgressMonitor monitor) {	
				AutoGenCTSettings.cloneSets = new CloneDetectionFileParser(false, "").getCloneSets();
				String templateFileLocation = AutoGenCTSettings.retrieveTemplateFileLocation();
				
				File file = new File(templateFileLocation);
				if(!file.exists()){
					StructureExtractor extractor = new StructureExtractor();
					ArrayList<Application> applications = extractor.
							extractPrgroamStructure(new String[]{AutoGenCTSettings.retrieveExcludePath()});
					
					Application app = applications.get(0);
					ArrayList<Multiset> typeSets = new TypeCluster().clusterTypes(app.getTypes());
					ArrayList<Multiset> correspondingTypes = new ArrayList<>();
					for(Multiset typeSet: typeSets){
						new Corresponder().match(typeSet);
						correspondingTypes.add(typeSet);
					}
					
					
//					Multiset applicationSet = new Multiset();
//					for(Application app: applications){
//						applicationSet.addElement(app);
//					}
					
//					clearCloneSets();
//					
//					ArrayList<Multiset> correspondingTypes = new Corresponder().match(applicationSet);
					
					DesignList designs = new DesignBuilder().buildDesign(correspondingTypes);
					ArrayList<TemplateDesign> l = (ArrayList<TemplateDesign>)designs;
					Collections.sort(l, new TemplateDesignComparator());
					
					designs = new ProgramAbstractor().abstractTemplate(designs);
					
					AutoGenCTSettings.designs = designs;
					
					
					DesignXMLWriter.design2XML(designs, templateFileLocation);
					
					
					System.currentTimeMillis();
				}
				else{
					DesignList designs = DesignXMLReader.xml2design(templateFileLocation);
					AutoGenCTSettings.designs = designs;
					
					System.currentTimeMillis();
				}
				
				
				/*monitor.beginTask("Template generation start", 100);
				
				CloneSets sets = (CloneSets)MinerUtil.deserialize(Settings.ontologyFile, true);
				//sets.setPathComparator(new LevenshteinPathComparator());
				
				//TMGBuilder2 builder = new TMGBuilder2(sets);
				TMGBuilder builder = new TMGBuilder(sets);
				builder.build(monitor);
				HashSet<TemplateMethodGroup> templateMethodGroupList = builder.getMethodGroupList();
				
				ArrayList<TemplateMethodGroup> list = new ArrayList<TemplateMethodGroup>();
				
				ArrayList<TemplateMethodGroup> sigificantTMGList = new ArrayList<TemplateMethodGroup>();
				for(TemplateMethodGroup tmg: templateMethodGroupList){
					
					if(tmg.toString().contains("main")){
						System.out.println();
					}
					
					list.add(tmg);
					if(tmg.getMethods().size() > 2){
						sigificantTMGList.add(tmg);
					}
				}
				
				System.out.println("significant TMG: " + sigificantTMGList.size());
				
				CandidateTemplateBuilder featureBuilder = new CandidateTemplateBuilder(list);
				featureBuilder.generateTemplateFeatures(monitor);
				CandidateTemplateList featureGroups = featureBuilder.getCandidateTemplateList();
				
				try {
					MinerUtil.serialize(featureGroups, "featureGroups", false);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				System.out.println("cadidate template list: " + featureGroups.size());
				
				//CandidateTemplateList featureGroups = (CandidateTemplateList)MinerUtil.deserialize("featureGroups", false);
				
				CandidateTemplateList significantGroups = new CandidateTemplateList();
				for(CandidateTemplate feature: featureGroups){
					int count = 0;
					for(SubCandidateTemplate tfg: feature){
						count += tfg.getTemplateMethodGroupList().size();
					}
					if(count > 3){
						
						if(feature.toString().contains("Main")){
							System.out.println();
						}
						
						TemplateBuilder templateBuilder = new TemplateBuilder(feature);
						Template template = templateBuilder.buildTemplate();
						
						feature.setTemplate(template);
						significantGroups.add(feature);
					}
				}
				
				//System.out.println("cadidate template list: " + featureGroups.size());
				
				monitor.worked(30);
				
				//TMGList tmgList = new TMGList(sets, list);
				//TFGList tfgList = new TFGList(sets, featureGroups);
				
				try {
					//MinerUtil.serialize(tmgList, "tmgList", false);
					MinerUtil.serialize(significantGroups, "totalTFGs", false);
				} catch (Exception e) {
					e.printStackTrace();
				}*/
				
				//sets.setTemplateMethodGroup(list);
				//sets.setFeatureGroupList(featureGroups);
				return Status.OK_STATUS;
			}
		};
		
		job.schedule();
		
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(IWorkbenchWindow window) {
		// TODO Auto-generated method stub

	}

}
