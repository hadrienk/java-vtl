package no.ssb.vtl.script;

import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.model.VTLObject;
import no.ssb.vtl.model.VTLTyped;

public abstract class VTLDataset extends VTLObject<Dataset> implements VTLTyped<VTLDataset> {

    @Override
    public Class<VTLDataset> getType() {
        return VTLDataset.class;
    }

    private VTLDataset() {
        // private
    }

    public static VTLDataset of(Dataset dataset) {
        return new VTLDataset() {
            @Override
            public Dataset get() {
                return dataset;
            }
        };
    }
}
