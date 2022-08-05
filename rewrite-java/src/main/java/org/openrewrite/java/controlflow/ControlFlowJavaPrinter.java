/*
 * Copyright 2022 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * https://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.openrewrite.java.controlflow;

import lombok.AllArgsConstructor;
import org.openrewrite.Cursor;
import org.openrewrite.PrintOutputCapture;
import org.openrewrite.internal.lang.Nullable;
import org.openrewrite.java.JavaPrinter;
import org.openrewrite.java.tree.J;
import org.openrewrite.java.tree.JRightPadded;
import org.openrewrite.java.tree.Space;
import org.openrewrite.java.tree.Statement;
import org.openrewrite.marker.Marker;

import java.util.List;

@AllArgsConstructor
public class ControlFlowJavaPrinter<P> extends JavaPrinter<P> {
    final List<J> nodesToPrint;

    @Override
    public J visitAssert(J.Assert azzert, PrintOutputCapture<P> p) {
        maybeEnableOrDisable(azzert, p);
        J j = super.visitAssert(azzert, p);
        return j;
    }

    @Override
    public J visitAssignment(J.Assignment assignment, PrintOutputCapture<P> p) {
        maybeEnableOrDisable(assignment, p);
        J j = super.visitAssignment(assignment, p);
        return j;
    }

    @Override
    public J visitAssignmentOperation(J.AssignmentOperation assignOp, PrintOutputCapture<P> p) {
        maybeEnableOrDisable(assignOp, p);
        J j = super.visitAssignmentOperation(assignOp, p);
        return j;
    }

    @Override
    public J visitBinary(J.Binary binary, PrintOutputCapture<P> p) {
        maybeEnableOrDisable(binary, p);
        J j = super.visitBinary(binary, p);
        return j;
    }

    @Override
    public J visitBlock(J.Block block, PrintOutputCapture<P> p) {
        maybeEnableOrDisable(block, p);
        visitSpace(block.getEnd(), Space.Location.BLOCK_PREFIX, p);
        visitMarkers(block.getMarkers(), p);


        if (block.isStatic()) {
            p.append("static");
            visitRightPadded(block.getPadding().getStatic(), JRightPadded.Location.STATIC_INIT, p);
        }

        p.append('{');
        visitStatements(block.getPadding().getStatements(), JRightPadded.Location.BLOCK_STATEMENT, p);
        visitSpace(block.getEnd(), Space.Location.BLOCK_END, p);
        p.append('}');
        return block;
    }

    @Override
    protected void visitStatement(@Nullable JRightPadded<Statement> paddedStat, JRightPadded.Location location, PrintOutputCapture<P> p) {
        if (paddedStat == null) {
            return;
        }
        maybeEnableOrDisable(paddedStat.getElement(), p);
        super.visitStatement(paddedStat, location, p);
    }

    @Override
    public J visitBreak(J.Break breakStatement, PrintOutputCapture<P> p) {
        maybeEnableOrDisable(breakStatement, p);
        J j = super.visitBreak(breakStatement, p);
        return j;
    }

    @Override
    public J visitCase(J.Case caze, PrintOutputCapture<P> p) {
        maybeEnableOrDisable(caze, p);
        J j = super.visitCase(caze, p);
        return j;
    }

    @Override
    public J visitCatch(J.Try.Catch catzh, PrintOutputCapture<P> p) {
        maybeEnableOrDisable(catzh, p);
        J j = super.visitCatch(catzh, p);
        return j;
    }

    @Override
    public J visitClassDeclaration(J.ClassDeclaration classDecl, PrintOutputCapture<P> p) {
        maybeEnableOrDisable(classDecl, p);
        J j = super.visitClassDeclaration(classDecl, p);
        return j;
    }

    @Override
    public J visitContinue(J.Continue continueStatement, PrintOutputCapture<P> p) {
        maybeEnableOrDisable(continueStatement, p);
        J j = super.visitContinue(continueStatement, p);
        return j;
    }

    @Override
    public <T extends J> J visitControlParentheses(J.ControlParentheses<T> controlParens, PrintOutputCapture<P> p) {
        J.If enclosing = getCursor().firstEnclosing(J.If.class);
        if (enclosing != null && enclosing.getIfCondition() == controlParens) {
            return super.visitControlParentheses(controlParens, p);
        }
        J.WhileLoop enclosingLoop = getCursor().firstEnclosing(J.WhileLoop.class);
        if (enclosingLoop != null && enclosingLoop.getCondition() == controlParens) {
            return super.visitControlParentheses(controlParens, p);
        }
        J.DoWhileLoop enclosingDoWhileLoop = getCursor().firstEnclosing(J.DoWhileLoop.class);
        if (enclosingDoWhileLoop != null && enclosingDoWhileLoop.getWhileCondition() == controlParens) {
            return super.visitControlParentheses(controlParens, p);
        }
        maybeEnableOrDisable(controlParens, p);
        J j = super.visitControlParentheses(controlParens, p);
        return j;
    }

    @Override
    public J visitDoWhileLoop(J.DoWhileLoop doWhileLoop, PrintOutputCapture<P> p) {
        maybeEnableOrDisable(doWhileLoop, p);
        J j = super.visitDoWhileLoop(doWhileLoop, p);
        return j;
    }

    @Override
    public J visitEnumValue(J.EnumValue enoom, PrintOutputCapture<P> p) {
        maybeEnableOrDisable(enoom, p);
        J j = super.visitEnumValue(enoom, p);
        return j;
    }

    @Override
    public J visitEnumValueSet(J.EnumValueSet enums, PrintOutputCapture<P> p) {
        maybeEnableOrDisable(enums, p);
        J j = super.visitEnumValueSet(enums, p);
        return j;
    }

    @Override
    public J visitFieldAccess(J.FieldAccess fieldAccess, PrintOutputCapture<P> p) {
        maybeEnableOrDisable(fieldAccess, p);
        J j = super.visitFieldAccess(fieldAccess, p);
        return j;
    }

    @Override
    public J visitForLoop(J.ForLoop forLoop, PrintOutputCapture<P> p) {
        maybeEnableOrDisable(forLoop, p);
        J j = super.visitForLoop(forLoop, p);
        return j;
    }

    @Override
    public J visitForEachLoop(J.ForEachLoop forEachLoop, PrintOutputCapture<P> p) {
        maybeEnableOrDisable(forEachLoop, p);
        J j = super.visitForEachLoop(forEachLoop, p);
        return j;
    }

    @Override
    public J visitIdentifier(J.Identifier ident, PrintOutputCapture<P> p) {
        // If the variable side of an assignment is not a flow step, so we don't need to do anything
        J.Assignment parentAssignment = getCursor().firstEnclosing(J.Assignment.class);
        if (parentAssignment != null && parentAssignment.getVariable().unwrap() == ident) {
            return super.visitIdentifier(ident, p);
        }
        J.VariableDeclarations.NamedVariable parentNamedVariable = getCursor().firstEnclosing(J.VariableDeclarations.NamedVariable.class);
        if (parentNamedVariable != null && parentNamedVariable.getName() == ident) {
            return super.visitIdentifier(ident, p);
        }
        // If the identifier is a field access, don't modify the printer state
        J.FieldAccess parentFieldAccess = getCursor().firstEnclosing(J.FieldAccess.class);
        if (parentFieldAccess != null && parentFieldAccess.getName() == ident) {
            return super.visitIdentifier(ident, p);
        }
        // If the identifier is a new class name, don't modify the printer state
        J.NewClass parentNewClass = getCursor().firstEnclosing(J.NewClass.class);
        if (parentNewClass != null && parentNewClass.getClazz() == ident) {
            return super.visitIdentifier(ident, p);
        }
        // If the identifier is a method name, don't modify the printer state
        J.MethodInvocation parentMethodInvocation = getCursor().firstEnclosing(J.MethodInvocation.class);
        if (parentMethodInvocation != null && parentMethodInvocation.getName() == ident) {
            return super.visitIdentifier(ident, p);
        }
        maybeEnableOrDisable(ident, p);
        J j = super.visitIdentifier(ident, p);
        return j;
    }

    @Override
    public J visitIf(J.If iff, PrintOutputCapture<P> p) {
        maybeEnableOrDisable(iff, p);
        J j = super.visitIf(iff, p);
        return j;
    }

    @Override
    public J visitInstanceOf(J.InstanceOf instanceOf, PrintOutputCapture<P> p) {
        maybeEnableOrDisable(instanceOf, p);
        J j = super.visitInstanceOf(instanceOf, p);
        return j;
    }

    @Override
    public J visitLambda(J.Lambda lambda, PrintOutputCapture<P> p) {
        maybeEnableOrDisable(lambda, p);
        J j = super.visitLambda(lambda, p);
        return j;
    }

    @Override
    public J visitLiteral(J.Literal literal, PrintOutputCapture<P> p) {
        maybeEnableOrDisable(literal, p);
        J j = super.visitLiteral(literal, p);
        return j;
    }

    @Override
    public J visitMemberReference(J.MemberReference memberRef, PrintOutputCapture<P> p) {
        maybeEnableOrDisable(memberRef, p);
        J j = super.visitMemberReference(memberRef, p);
        return j;
    }

    @Override
    public J visitMethodDeclaration(J.MethodDeclaration method, PrintOutputCapture<P> p) {
        maybeEnableOrDisable(method, p);
        J j = super.visitMethodDeclaration(method, p);
        return j;
    }

    @Override
    public J visitMethodInvocation(J.MethodInvocation method, PrintOutputCapture<P> p) {
        maybeEnableOrDisable(method, p);
        J j = super.visitMethodInvocation(method, p);
        return j;
    }

    @Override
    public J visitMultiCatch(J.MultiCatch multiCatch, PrintOutputCapture<P> p) {
        maybeEnableOrDisable(multiCatch, p);
        J j = super.visitMultiCatch(multiCatch, p);
        return j;
    }

    @Override
    public J visitVariableDeclarations(J.VariableDeclarations multiVariable, PrintOutputCapture<P> p) {
        maybeEnableOrDisable(multiVariable, p);
        J j = super.visitVariableDeclarations(multiVariable, p);
        return j;
    }

    @Override
    public J visitNewArray(J.NewArray newArray, PrintOutputCapture<P> p) {
        maybeEnableOrDisable(newArray, p);
        J j = super.visitNewArray(newArray, p);
        return j;
    }

    @Override
    public <T extends J> J visitParentheses(J.Parentheses<T> parens, PrintOutputCapture<P> p) {
        maybeEnableOrDisable(parens, p);
        J j = super.visitParentheses(parens, p);
        return j;
    }

    @Override
    public J visitSwitch(J.Switch switzh, PrintOutputCapture<P> p) {
        maybeEnableOrDisable(switzh, p);
        J j = super.visitSwitch(switzh, p);
        return j;
    }

    @Override
    public J visitSynchronized(J.Synchronized synch, PrintOutputCapture<P> p) {
        maybeEnableOrDisable(synch, p);
        J j = super.visitSynchronized(synch, p);
        return j;
    }

    @Override
    public J visitTernary(J.Ternary ternary, PrintOutputCapture<P> p) {
        maybeEnableOrDisable(ternary, p);
        J j = super.visitTernary(ternary, p);
        return j;
    }

    @Override
    public J visitThrow(J.Throw thrown, PrintOutputCapture<P> p) {
        maybeEnableOrDisable(thrown, p);
        J j = super.visitThrow(thrown, p);
        return j;
    }

    @Override
    public J visitTry(J.Try tryable, PrintOutputCapture<P> p) {
        maybeEnableOrDisable(tryable, p);
        J j = super.visitTry(tryable, p);
        return j;
    }

    @Override
    public J visitUnary(J.Unary unary, PrintOutputCapture<P> p) {
        maybeEnableOrDisable(unary, p);
        J j = super.visitUnary(unary, p);
        return j;
    }

    @Override
    public J visitVariable(J.VariableDeclarations.NamedVariable variable, PrintOutputCapture<P> p) {
        maybeEnableOrDisable(variable, p);
        J j = super.visitVariable(variable, p);
        return j;
    }

    @Override
    public J visitWhileLoop(J.WhileLoop whileLoop, PrintOutputCapture<P> p) {
        maybeEnableOrDisable(whileLoop, p);
        J j = super.visitWhileLoop(whileLoop, p);
        return j;
    }

    static class ControlFlowPrintOutputCapture<P> extends PrintOutputCapture<P> {
        boolean enabled = false;

        public ControlFlowPrintOutputCapture(P p) {
            super(p);
        }

        @Override
        public PrintOutputCapture<P> append(char c) {
            if (enabled) {
                return super.append(c);
            } else {
                switch (c) {
                    case '\t':
                    case '\n':
                    case '\r':
                        return super.append(c);
                    default:
                        return super.append(' ');
                }
            }
        }

        @Override
        public PrintOutputCapture<P> append(@Nullable String text) {
            if (enabled) {
                return super.append(text);
            } else if (text != null) {
                return super.append(text.replaceAll(
                        "[^ \\t\\n\\r]",
                        " "));
            } else {
                return this;
            }
        }

        public void enable() {
            enabled = true;
        }

        public void disable() {
            enabled = false;
        }
    }

    private void maybeEnableOrDisable(J j, PrintOutputCapture<P> p) {
        if (nodesToPrint.contains(j)) {
            castPrint(p).enable();
        } else {
            castPrint(p).disable();
        }
    }
    private static <P> ControlFlowPrintOutputCapture<P> castPrint(PrintOutputCapture<P> print) {
        return (ControlFlowPrintOutputCapture<P>) print;
    }

}