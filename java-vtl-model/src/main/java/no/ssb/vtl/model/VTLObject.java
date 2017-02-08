package no.ssb.vtl.model;

public abstract class VTLObject {

    private VTLObject() {
    }

    public static VTLObject wrap(Object o) {
        return new VTLObject() {
            @Override
            Object getValue() {
                return null;
            }
        };
    }

    abstract Object getValue();

    public Dataset asDataset() {
        return (Dataset) getValue();
    }

    private boolean isDataset() {
        return getValue() instanceof Dataset;
    }

    public Component asComponent() {
        return (Component) getValue();
    }

    private boolean isComponent() {
        return getValue() instanceof Component;
    }
}
