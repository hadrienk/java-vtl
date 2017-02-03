package no.ssb.vtl.script.visitors;

import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.parser.VTLBaseVisitor;
import no.ssb.vtl.parser.VTLParser;

import javax.script.Bindings;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.lang.String.format;

/**
 * The reference visitor tries to find references to object in a Bindings.
 */
public class ReferenceVisitor extends VTLBaseVisitor<Object> {

    private Bindings scope;

    public ReferenceVisitor(Bindings scope) {
        this.scope = checkNotNull(scope, "scope cannot be empty");
    }

    private static <T> T findObject(Map<String, ?> scope, String key, Class<T> clazz) {
        if (!scope.containsKey(key)) {
            return null;
        }

        Object ref = scope.get(key);
        if (clazz.isAssignableFrom(ref.getClass())) {
            return (T) ref;
        }
        throw new RuntimeException(
                format("wrong type for [%s], expected %s, got %s", key, clazz, ref.getClass())
        );
    }

    private static String removeQuoteIfNeeded(String key) {
        if (!key.isEmpty() && key.length() > 3) {
            if (key.charAt(0) == '\'' && key.charAt(key.length() - 1) == '\'') {
                return key.substring(1, key.length() - 1);
            }
        }
        return key;
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * @param ctx
     */
    @Override
    public Object visitComponentRef(VTLParser.ComponentRefContext ctx) {
        String key = removeQuoteIfNeeded(ctx.componentID().getText());
        Map<String, ?> scope = this.scope;

        if (ctx.datasetRef() != null) {
            Dataset ds = (Dataset) visit(ctx.datasetRef());
            scope = ds.getDataStructure();
        }

        Component component = findObject(scope, key, Component.class);

        if (component == null) {
            throw new RuntimeException(format("component [%s] not found", ctx.getText()));
        }

        return component;
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * @param ctx
     */
    @Override
    public Object visitDatasetRef(VTLParser.DatasetRefContext ctx) {
        String key = removeQuoteIfNeeded(ctx.getText());

        Dataset dataset = findObject(scope, key, Dataset.class);

        if (dataset == null) {
            throw new RuntimeException(format("component [%s] not found", ctx.getText()));
        }

        return dataset;

    }
}
