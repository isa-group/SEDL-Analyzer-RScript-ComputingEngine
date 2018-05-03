/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.us.isa.sedl.module.marshaller;

import es.us.isa.sedl.core.BasicExperiment;
import es.us.isa.sedl.core.Experiment;
import es.us.isa.sedl.core.ExtensionPointElement;
import es.us.isa.sedl.core.analysis.datasetspecification.DatasetSpecification;
import es.us.isa.sedl.core.design.AnalysisSpecification;
import es.us.isa.sedl.core.design.AnalysisSpecificationGroup;
import es.us.isa.sedl.core.design.StatisticalAnalysisSpec;
import es.us.isa.sedl.core.util.Error;
import es.us.isa.sedl.error.SEDL4PeopleError;
import es.us.isa.sedl.marshaller.analysis.statistic.DatasetSpecificationParser;
import es.us.isa.sedl.module.SEDLModuleUnmarshaller;
import es.us.isa.sedl.core.analysis.statistic.module.rscript.RScript;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Jose Antonio Parejo
 */
public class RModuleUnmarshaller implements SEDLModuleUnmarshaller {

    DatasetSpecificationParser dssParser;

    public RModuleUnmarshaller() {
        dssParser=new DatasetSpecificationParser();
    }
    
    
            
    @Override
    public Collection<? extends Error> unmarshall(ExtensionPointElement element, Experiment experiment) {
        List<Error> result=new ArrayList<Error>();
        String content=element.getContent();
        if(content.contains(":"))
            content=content.substring(content.indexOf(":")+1);
        String scriptName=content.replace(":", "").replace("'","").replace("\n","");
        if(scriptName.equals("")){
            Error e=new SEDL4PeopleError(element.getContext().start.getLine(), 
                                            element.getContext().start.getStartIndex(), element.getContext().stop.getStopIndex(), Error.ERROR_SEVERITY.ERROR, 
                                            "The R script filename should not be a non empty string. E.g.: 'myAnalysisScript.r'");
            result.add(e);
        }
        RScript script=new RScript();        
        script.setFileName(scriptName);        
        AnalysisSpecificationGroup aspec=findAnalysisSpecificationGroup(element,experiment);        
        if(aspec!=null){
            StatisticalAnalysisSpec sas=new StatisticalAnalysisSpec();
            sas.getStatistic().add(script);
            aspec.getAnalyses().add(sas);
        }else{
            Error e=new SEDL4PeopleError(element.getContext().start.getLine(), 
                                            element.getContext().start.getStartIndex(), element.getContext().stop.getStopIndex(), Error.ERROR_SEVERITY.ERROR, 
                                            "Unable to find the analysis groupt to which the R Script should be added");
            result.add(e);
        }
        return result;
    }

    private AnalysisSpecificationGroup findAnalysisSpecificationGroup(ExtensionPointElement element, Experiment experiment) {
        AnalysisSpecificationGroup aspec=null;
        List<AnalysisSpecificationGroup> analysisGroups=((BasicExperiment)experiment).getDesign().getExperimentalDesign().getIntendedAnalyses();
        String analysisGroupID=null;
        if(element.getExtensionPointLocator()!=null && !element.getExtensionPointLocator().isEmpty())
            analysisGroupID=element.getExtensionPointLocator().get(0);
        if(analysisGroupID!=null && analysisGroups!=null && !analysisGroups.isEmpty()){            
            for(AnalysisSpecificationGroup ag:analysisGroups){
                if(analysisGroupID.equals(ag.getId()))
                    aspec=ag;
            }
        }
        return aspec;
    }
    
}
