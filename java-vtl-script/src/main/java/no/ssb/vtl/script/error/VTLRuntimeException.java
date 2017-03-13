package no.ssb.vtl.script.error;

import no.ssb.vtl.model.DataPoint;

import static no.ssb.vtl.script.error.VTLErrorCodeUtil.*;

/**
 * Thrown when the VTL execution failed at runtime.
 */
public class VTLRuntimeException extends RuntimeException implements VTLThrowable {

    private static final long serialVersionUID = 7253953869019949964L;
    private final String VTLCode;
    private final DataPoint dataPoint;

    public VTLRuntimeException(String s, String vtlCode, DataPoint dataPoint) {
        super(s);
        this.VTLCode = checkVTLCode(vtlCode, "VTL-1");
        this.dataPoint = dataPoint;
    }

    public VTLRuntimeException(Exception e, String vtlCode, DataPoint dataPoint) {
        super(e);
        this.VTLCode = checkVTLCode(vtlCode, "VTL-1");
        this.dataPoint = dataPoint;
    }
    
    @Override
    public String getMessage() {
        String message = super.getMessage();
        return message + " for dataPoint: " + dataPoint;
    }
    
    @Override
    public String getVTLCode() {
        return VTLCode;
    }
}
