/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.us.isa.sedl.module;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import es.us.isa.sedl.core.Experiment;
import es.us.isa.sedl.module.marshaller.RModuleMarshaller;
import es.us.isa.sedl.module.marshaller.RModuleUnmarshaller;

import java.util.List;
import java.util.Set;

/**
 *
 * @author Jose Antonio Parejo
 */
public class RModule extends BaseSEDLModule implements SEDLModule {
   private static final Set<String> moduleNames=Sets.newHashSet("R","RScripts");
   private static final List<Class<? extends Experiment>> applicableExperimentTypes=Lists.newArrayList();
   
   public RModule()
   {
       super(moduleNames, applicableExperimentTypes);       
       this.getMarshallers().put("SEDL4People", new RModuleMarshaller());
       this.getUnmarshallers().put("SEDL4People", new RModuleUnmarshaller());
   }    
}
