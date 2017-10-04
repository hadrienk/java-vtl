package no.ssb.vtl.model;

public interface VTLTyped<T> {

    /**
     * Deprecated: The name is confusing. getJavaClass() should be used instead.
     */
    @Deprecated
    Class<? extends T> getType();

    default Class<? extends T> getJavaClass() {
        return getType();
    }

}
