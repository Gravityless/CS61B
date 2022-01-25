public class NBody {
    public static double readRadius(String path){
        In in = new In(path);
        in.readInt();
        return in.readDouble();
    }

    public static Planet[] readPlanets(String path){
        In in = new In(path);
        int nums = in.readInt();
        in.readDouble();

        Planet[] planets = new Planet[nums];
        for(int i=0;i<nums;i++){
            Planet p = new Planet(in.readDouble(), in.readDouble(), in.readDouble(),
                    in.readDouble(), in.readDouble(), in.readString());
            planets[i] = p;
        }
        return planets;
    }

    public static void printResult(Planet[] planets, double radius){
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }

    public static void main(String args[]){
        // Collecting All Needed Input
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];

        //Setting Scale
        double radius = readRadius(filename);
        Planet[] ps = readPlanets(filename);

        StdDraw.setScale(-radius,radius);
        StdDraw.clear();

        //Creating an Animation
        StdDraw.enableDoubleBuffering();
        double t = 0;
        int nums = ps.length;
        while(t < T){
            double[] xForces = new double[nums];
            double[] yForces = new double[nums];
            for(int i=0;i<nums;i++){
                xForces[i] = ps[i].calcNetForceExertedByX(ps);
                yForces[i] = ps[i].calcNetForceExertedByY(ps);
            }
            for(int i=0;i<nums;i++){
                ps[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.picture(0,0,"images/starfield.jpg");
            for (Planet p: ps) p.draw();
            StdDraw.show();
            StdDraw.pause(10);
            t += dt;
        }

        printResult(ps, radius);
    }
}
