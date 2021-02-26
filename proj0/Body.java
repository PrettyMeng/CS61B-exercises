public class Body {
    double xxPos, yyPos;
    double xxVel, yyVel;
    double mass;
    String imgFileName;
    static final double G = 6.67e-11;

    public Body(double xP, double yP, double xV, double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Body(Body b){
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    public double calcDistance(Body b){
        double distance;
        distance = Math.sqrt(Math.pow(xxPos - b.xxPos, 2) + Math.pow(yyPos - b.yyPos, 2));
        return distance;
    }

    public double calcForceExertedBy(Body b){
        double F;
        F = (G * mass * b.mass) / Math.pow(this.calcDistance(b), 2);
        return F;
    }

    public double calcForceExertedByX(Body b){
        double F_overall, F_x;
        F_overall = calcForceExertedBy(b);
        F_x = - F_overall * (xxPos - b.xxPos) / calcDistance(b);
        return F_x;
    }

    public double calcForceExertedByY(Body b){
        double F_overall, F_y;
        F_overall = calcForceExertedBy(b);
        F_y = - F_overall * (yyPos - b.yyPos) / calcDistance(b);
        return F_y;
    }

    public static void main(String[] args){

    }
}
