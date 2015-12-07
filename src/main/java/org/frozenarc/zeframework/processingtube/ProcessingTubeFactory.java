package org.frozenarc.zeframework.processingtube;

import org.frozenarc.zeframework.constants.FWConstants;
import org.frozenarc.zeframework.fwconfig.FWConfigUtil;
import org.frozenarc.zeframework.util.ReflectionUtil;

/**
 * Factory which creates AbstractProcessingTube
 * @author Manan
 */
public class ProcessingTubeFactory {

    public static AbstractProcessingTube getProcessingTube() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        if (ProcessingTubeContext.getValue(FWConstants.PROCESSING_TUBE) != null) {
            return ProcessingTubeContext.getValue(FWConstants.PROCESSING_TUBE);
        } else {
            return createProcessingTubeSingleton();
        }
    }

    protected static synchronized AbstractProcessingTube createProcessingTubeSingleton() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        if (ProcessingTubeContext.getValue(FWConstants.PROCESSING_TUBE) == null) {
            String processingTubeName = FWConfigUtil.getProcessingTube();
            ProcessingTubeContext.putValue(FWConstants.PROCESSING_TUBE, processingTubeName == null ? new DefaultProcessingTube() : (AbstractProcessingTube) ReflectionUtil.createInstance(processingTubeName));
        }
        return ProcessingTubeContext.getValue(FWConstants.PROCESSING_TUBE);
    }
}
