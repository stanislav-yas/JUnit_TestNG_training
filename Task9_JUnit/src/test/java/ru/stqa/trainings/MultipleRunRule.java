package ru.stqa.trainings;

import org.junit.Assume;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import java.util.regex.Matcher;

public class MultipleRunRule implements TestRule {
    @Override
    public Statement apply(Statement statement, Description description){
        Unstable unstable = description.getAnnotation(Unstable.class);
        if(unstable != null){
            return new MultipleRunRuleStatement(statement, description, unstable.value());
        }
        return statement;
    }

    private class MultipleRunRuleStatement extends Statement{

        Statement statement;
        Description description;
        int value;

        MultipleRunRuleStatement(Statement statement, Description description, int value){
            this.statement = statement;
            this.description = description;
            this.value = value;
        }

        @Override
        public void evaluate() throws Throwable{
            Assume.assumeTrue("Annotation Unstable value should be > 0 in method: " + description.getMethodName(),value > 0);
            Throwable throwable = null;
            for (int i = 0; i < value; i++) {
                try {
                    statement.evaluate();
                }catch (Throwable t){
                    throwable = t;
                    continue;
                }
                return;
            }
            throw throwable;
        }
    }
}
