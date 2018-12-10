package no.ssb.vtl.script;

public class VtlConfiguration {

    private static final ThreadLocal<VtlConfiguration> localConfiguration = new ThreadLocal<>();

    private boolean filterOptimization = true;
    private boolean filterPropagation = true;

    private boolean profiling = true;
    private boolean sortAssert = true;

    public static VtlConfiguration getConfig() {
        VtlConfiguration configuration = localConfiguration.get();
        if (configuration == null) {
            localConfiguration.set(new VtlConfiguration());
        }
        return localConfiguration.get();
    }

    static void setConfig(VtlConfiguration config) {
        localConfiguration.set(config);
    }

    /**
     * Some dataset operation can add filters to optimize the execution.
     * {@link no.ssb.vtl.script.operations.unfold.UnfoldOperation} adds a filter on the values that are unfolded.
     * {@link no.ssb.vtl.script.operations.join.InnerJoinOperation} add a range filter based on the first and last
     * identifiers of the datasets it joins.
     */
    public boolean isFilterOptimizationEnabled() {
        return this.filterOptimization;
    }

    /**
     * When filter propagation is enabled, dataset operations will try to
     * push back part of the filters they received back to the operations
     * behind them.
     */
    public boolean isFilterPropagationEnabled() {
        return this.filterPropagation;
    }

    /**
     * Profiling enables counting and timing data processing. Note that profiling will
     * reduce performances.
     */
    public boolean isProfilingEnabled() {
        return profiling;
    }

    /**
     * If enabled all ordering invariant will be asserted.
     */
    public boolean isSortAssertionEnabled() {
        return sortAssert;
    }

}
