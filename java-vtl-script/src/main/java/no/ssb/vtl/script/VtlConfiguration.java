package no.ssb.vtl.script;

public class VtlConfiguration {

    private static final ThreadLocal<VtlConfiguration> localConfiguration = new ThreadLocal<>();

    private boolean filterOptimization = true;
    private boolean filterPropagation = true;

    public static VtlConfiguration getConfig() {
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

}
