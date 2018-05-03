/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.us.isa.sedl.module;

import es.us.isa.jdataset.Column;
import es.us.isa.sedl.analysis.operations.information.computestats.StatisticalAnalysisOperation;
import es.us.isa.sedl.analysis.operations.information.computestats.UnsupportedStatisticException;
import es.us.isa.sedl.analysis.operations.information.computestats.engine.DescriptiveStatisticsComputingEngine;
import es.us.isa.sedl.analysis.operations.information.computestats.engine.StatisticComputingEnginePlugin;
import es.us.isa.sedl.core.analysis.statistic.Statistic;
import es.us.isa.sedl.core.analysis.statistic.StatisticalAnalysisResult;
import es.us.isa.sedl.core.analysis.statistic.module.rscript.RScript;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/**
 *
 * @author Jose Antonio Parejo
 */
public class RComputingEngine implements StatisticComputingEnginePlugin {

    private DescriptiveStatisticsComputingEngine dsce;

    public RComputingEngine() {
        dsce = new DescriptiveStatisticsComputingEngine();
    }

    @Override
    public boolean isSupported(Statistic s) {
        return s instanceof RScript;
    }

    @Override
    public List<StatisticalAnalysisResult> compute(StatisticalAnalysisOperation operation) throws UnsupportedStatisticException {
        List<StatisticalAnalysisResult> result=null;
        if(!(operation.getStatistic() instanceof RScript))
            return Collections.EMPTY_LIST;
        RScript script=(RScript)operation.getStatistic();
        // TODO: Implement R Script Computation.
        return result;
    }

    
    @Override
    public Class<? extends Statistic> supportedStatistic() {
        return RScript.class;
    }    
}
