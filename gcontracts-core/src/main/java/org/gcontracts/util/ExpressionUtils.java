/**
 * Copyright (c) 2011, Andre Steingress
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the
 * following conditions are met:
 *
 * 1.) Redistributions of source code must retain the above copyright notice, this list of conditions and the following
 * disclaimer.
 * 2.) Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following
 * disclaimer in the documentation and/or other materials provided with the distribution.
 * 3.) Neither the name of Andre Steingress nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES,
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE
 * USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.gcontracts.util;

import org.codehaus.groovy.ast.expr.BooleanExpression;
import org.codehaus.groovy.ast.expr.ClosureExpression;
import org.codehaus.groovy.ast.stmt.BlockStatement;
import org.codehaus.groovy.ast.stmt.ExpressionStatement;
import org.codehaus.groovy.ast.stmt.Statement;

import java.util.List;

/**
 * Internal utility class for extracting a boolean expression from the given closure expression.
 *
 * @see ClosureExpression
 * @see BooleanExpression
 *
 * @author ast
 */
public class ExpressionUtils {

    /**
     * Returns the first {@link org.codehaus.groovy.ast.expr.BooleanExpression} in the given {@link org.codehaus.groovy.ast.expr.ClosureExpression}.
     *
     * @param closureExpression the assertion's {@link org.codehaus.groovy.ast.expr.ClosureExpression}
     * @return the first {@link org.codehaus.groovy.ast.expr.Expression} found in the given {@link org.codehaus.groovy.ast.expr.ClosureExpression}
     */
    public static BooleanExpression getBooleanExpression(ClosureExpression closureExpression)  {
        if (closureExpression == null) return null;

        final BlockStatement closureBlockStatement = (BlockStatement) closureExpression.getCode();
        final List<Statement> statementList = closureBlockStatement.getStatements();

        for (Statement stmt : statementList)  {
            if (stmt instanceof ExpressionStatement && ((ExpressionStatement) stmt).getExpression() instanceof BooleanExpression)  {
                return (BooleanExpression) ((ExpressionStatement) stmt).getExpression();
            } else if (stmt instanceof ExpressionStatement)  {
                BooleanExpression result = new BooleanExpression(((ExpressionStatement) stmt).getExpression());
                result.setSourcePosition(stmt);
                return result;
            }
        }

        return null;
    }
}