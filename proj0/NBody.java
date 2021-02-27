public class NBody {
    public static double readRadius(String filename){
        In in = new In(filename);
        int N = in.readInt();
        double R = in.readDouble();
        return R;
    }

    public static Body[] readBodies(String filename){
        In in = new In(filename);
        int N = in.readInt();
        double R = in.readDouble();
        Body[] bodies = new Body[N];
        for (int i=0; i<N; i++){
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            Body b = new Body(xP, yP, xV, yV, m, img);
            bodies[i] = b;
        }
        return bodies;
    }

    public static void main(String[] args){
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];

        double R = readRadius(filename);
        Body[] bodies = readBodies(filename);

        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-R, R);
        StdDraw.clear();
        double current_time = 0;
        while (current_time < T){
            double[] xForces = new double[bodies.length];
            double[] yForces = new double[bodies.length];
            for (int i=0; i<bodies.length; i++){
                xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
                yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
                bodies[i].update(dt, xForces[i], yForces[i]);
            }
            for (int i=0; i<bodies.length; i++){
                bodies[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (Body b: bodies){
                b.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            current_time += dt;

        }
        StdOut.printf("%d\n", bodies.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < bodies.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
                    bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);
        }


    }
}
