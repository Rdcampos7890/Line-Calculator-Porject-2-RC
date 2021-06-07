package Main;

/*
 * Line class, it stores the properties of a line based on the input from the user and
 * can compare this line relative to other elements of a graph.
 */
public class Line {

    private double a;
    private double b;
    private double c;
    private double slope;
    private double intercept;

    /*
     * Initialized the variables of the Line class given the parameters slope and intercept.
     */
    public Line(double slope, double intercept) {

        this.slope = slope;
        this.intercept = intercept;
        this.a = -slope;
        this.c = intercept;
        this.b = 1;

    }

    /*
     * Initialized the variables of the Line class given the parameters a, b, and c.
     */
    public Line(double a, double b, double c) {

        this.a = a;
        this.b = b;
        this.c = c;
        this.slope = -a/b;
        this.intercept = c/b;

    }

    /*
     * Returns the current value of a.
     */
    public double getA() {
        return a;
    }

    /*
     * Returns the current value of b.
     */
    public double getB() {
        return b;
    }

    /*
     * Returns the current value of c.
     */
    public double getC() {
        return c;
    }

    /*
     * Returns the current value of intercept.
     */
    public double getIntercept() {
        return intercept;
    }

    /*
     * Returns the current value of slope.
     */
    public double getSlope() {
        return slope;
    }
    
    /*
     * Sets the value of a to the parameter.
     */
    public void setA(double a) {
        this.a = a;
    }

    /*
     * Sets the value of b to the parameter.
     */
    public void setB(double b) {
        this.b = b;
    }

    /*
     * Sets the value of c to the parameter.
     */
    public void setC(double c) {
        this.c = c;
    }

    /*
     * Sets the value of intercept to the parameter.
     */
    public void setIntercept(double intercept) {
        this.intercept = intercept;
    }

    /*
     * Sets the value of slope to the parameter.
     */
    public void setSlope(double slope) {
        this.slope = slope;
    }

    /*
     * Returns true if a point given by the coordinates (x,y) is on the line, false otherwise.
     */
    public boolean isOnLine(double x, double y) {
        if(a*x + b*y == c) {
            return true;
        }
        return false;
    }

    /*
     * Returns true of the point given by the coordinates (x,y) is above the line, false otherwise. 
     */
    public boolean checkPoint(double x, double y) {
        if(a*x + b*y > c) {
            return true;
        }
        return false;
    }
    
    /*
     * Returns true if the given "Line" object intercepts the line, false otherwise.
     */
    public boolean intercepts(Line line) {
        if(line.getSlope() == slope) {
            return false;
        }
        return true;
    }
    
    /*
     * Returns an array with the coordinates for where the given "Line" object intercepts the line.
     * Index 0 is the x coordinate and index 1 is the y coordinate.
     */
    public double[] interception(Line line) {
        double[] arr = new double[2];
        if(intercepts(line)) {
            arr[0] = (line.getC()/line.getB() - c/b)/(-a/b + line.getA()/line.getB());
            arr[1] = slope * arr[0] + intercept;
        }
        return arr;
    }

}

