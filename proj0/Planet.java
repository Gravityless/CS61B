public class Planet {
    double xxPos;
    double yyPos;
    double xxVel;
    double yyVel;
    double mass;
    String imgFileName;
    static final double G=6.67e-11;

    public Planet(double xP, double yP, double xV, double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p){
        this(p.xxPos, p.yyPos, p.xxVel, p.yyVel, p.mass, p.imgFileName);
    }

    public double calcDistance(Planet p){
        return Math.sqrt((xxPos-p.xxPos)*(xxPos-p.xxPos) + (yyPos-p.yyPos)*(yyPos-p.yyPos));
    }

    public double calcForceExertedBy(Planet p){
        double r = calcDistance(p);
        return G*mass*p.mass/r/r;
    }

    public double calcForceExertedByX(Planet p){
        return calcForceExertedBy(p)*(p.xxPos-xxPos)/calcDistance(p);
    }

    public double calcForceExertedByY(Planet p){
        return calcForceExertedBy(p)*(p.yyPos-yyPos)/calcDistance(p);
    }

    public double calcNetForceExertedByX(Planet[] ps){
        double sum = 0;
        for(int i=0;i<ps.length;i++){
            if(this.equals(ps[i]) == false){
                sum += calcForceExertedByX(ps[i]);
            }
        }
        return sum;
    }

    public double calcNetForceExertedByY(Planet[] ps){
        double sum = 0;
        for(int i=0;i<ps.length;i++){
            if(this.equals(ps[i]) == false){
                sum += calcForceExertedByY(ps[i]);
            }
        }
        return sum;
    }

    public void update(double dt, double fX,double fY){
        double ax = fX/mass;
        double ay = fY/mass;
        xxVel += dt*ax;
        yyVel += dt*ay;
        xxPos += dt*xxVel;
        yyPos += dt*yyVel;
    }

    public void draw(){
        StdDraw.picture(xxPos,yyPos,"./images/" + imgFileName);
    }
}
