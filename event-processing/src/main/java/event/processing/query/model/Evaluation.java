package event.processing.query.model;

import event.processing.query.Query;
import event.processing.query.Query.COMPARE_FUNCTION;

public class Evaluation {

    public static final String PREFIX = "d.deviceInformation.";

    private COMPARE_FUNCTION operator;

    private String evaluation;

    private String property1 = null;

    private String property2 = null;

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    public String getProperty1() {
        return property1;
    }

    public void setProperty1(String property1) {
        this.property1 = property1;
    }

    public String getProperty2() {
        return property2;
    }

    public void setProperty2(String property2) {
        this.property2 = property2;
    }

    public COMPARE_FUNCTION getOperator() {
        return operator;
    }

    public void setOperator(COMPARE_FUNCTION operator) {
        this.operator = operator;
    }

    public String generate() {
        StringBuilder sb = new StringBuilder();
        sb.append(property1);
        sb.append(" ");
        sb.append(operator.getFunction());
        sb.append(" ");
        sb.append(property2);

        return sb.toString();
    }

    public String generateInclPrefix() {

        StringBuilder sb = new StringBuilder();
        sb.append(Query.addPrefix(property1));
        sb.append(" ");
        sb.append(operator.getFunction());
        sb.append(" ");
        sb.append(Query.addPrefix(property2));

        return sb.toString();
    }
}