package org.apache.doris.nereids.trees.expressions.functions.scalar;

import org.apache.doris.catalog.FunctionSignature;
import org.apache.doris.nereids.trees.expressions.Expression;
import org.apache.doris.nereids.trees.expressions.functions.ExplicitlyCastableSignature;
import org.apache.doris.nereids.trees.expressions.functions.PropagateNullable;
import com.google.common.collect.ImmutableList;
import org.apache.doris.nereids.trees.expressions.visitor.ExpressionVisitor;
import org.apache.doris.nereids.types.MapType;
import org.apache.doris.nereids.types.StringType;
import org.apache.doris.nereids.types.VarcharType;
import com.google.common.base.Preconditions;

import java.util.List;

public class StrToMap extends ScalarFunction
    implements ExplicitlyCastableSignature, PropagateNullable {

    public static final List<FunctionSignature> SIGNATURES = ImmutableList.of(
        FunctionSignature.ret(MapType.SYSTEM_DEFAULT).args(VarcharType.SYSTEM_DEFAULT,VarcharType.SYSTEM_DEFAULT,VarcharType.SYSTEM_DEFAULT),
        FunctionSignature.ret(MapType.of(StringType.INSTANCE,StringType.INSTANCE)).args(StringType.INSTANCE,StringType.INSTANCE,StringType.INSTANCE)
    );

    public StrToMap(Expression arg0, Expression arg1, Expression arg2) {

        super("str_to_map", arg0, arg1, arg2);
    }

    @Override
    public List<FunctionSignature> getSignatures() {
        return SIGNATURES;
    }


    @Override
    public <R, C> R accept(ExpressionVisitor<R, C> visitor, C context) {
        return visitor.visitStrToMap(this, context);
    }
    @Override
    public boolean nullable() {
        return false;
    }
}
