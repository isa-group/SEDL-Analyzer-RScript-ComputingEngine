/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.us.isa.sedl.module.marshaller;

import es.us.isa.sedl.marshaller.SEDL4PeopleStringTemplateMarshaller;
import es.us.isa.sedl.module.SEDLModuleMarshaller;
import es.us.isa.sedl.module.rscript.RScript;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.List;


/**
 *
 * @author Jose Antonio Parejo
 */
public class RModuleMarshaller implements SEDLModuleMarshaller<RScript> {

    private SEDL4PeopleStringTemplateMarshaller marshaller;

    public RModuleMarshaller() {
        marshaller=new SEDL4PeopleStringTemplateMarshaller();        
    }
    
    
    
    @Override
    public List<String> write(RScript extensionPointRealization, OutputStream os, String modulePrefix) throws IOException {
        os.write((modulePrefix+"::'"+extensionPointRealization.getFileName()+"'").getBytes(Charset.forName("UTF-8")));
        return Collections.EMPTY_LIST;
    }

    @Override
    public String asString(RScript extensionPointRealization, String modulePrefix) {
        return modulePrefix+"::"+extensionPointRealization.getClass().getSimpleName()+
                " "+marshaller.asString(extensionPointRealization.getDatasetSpecification());
    }
    
}
