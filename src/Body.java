
public class Body {
	
	private double myXPos;
	private double myYPos;
	private double myXVel;
	private double myYVel;
	private double myMass;
	private String myFileName;
	
	/**
	 * Create a Body from parameters
	 * @param xp initial x position
	 * @param yp initial y position
	 * @param xv initial x velocity
	 * @param yv initial y velocity
	 * @param mass of object
	 * @param filename of image for object animation
	 */
	public Body(double xp, double yp, double xv, double yv, double mass, String filename) {
		myXPos = xp;
		myYPos = yp;
		myXVel = xv;
		myYVel = yv;
		myMass = mass;
		myFileName = filename;
	}
	
	/**
	 * Gets the x position of a body
	 * @return x position of body
	 */
	public double getX() {
		return myXPos;
	}
	
	/**
	 * Gets the y position of a body
	 * @return y position of body
	 */
	public double getY() {
		return myYPos;
	}
	
	/**
	 * Gets the x velocity of a body
	 * @return x velocity of body
	 */
	public double getXVel() {
		return myXVel;
	}
	
	/**
	 * Gets the y velocity of a body
	 * @return y velocity of body
	 */
	public double getYVel() {
		return myYVel;
	}
	
	/**
	 * Gets the mass of a body
	 * @return mass of body
	 */
	public double getMass() {
		return myMass;
	}
	
	/**
	 * Gets the filename of a body
	 * @return filename of body
	 */
	public String getName() {
		return myFileName;
	}
	
	/**
	 * Copy constructor: copy instance variables from one
	 * body to this body
	 * @param b used to initialize this body
	 */
	public Body(Body b) {
		this.myXPos = b.myXPos;
		this.myYPos = b.myYPos;
		this.myXVel = b.myXVel;
		this.myYVel = b.myYVel;
		this.myMass = b.myMass;
		this.myFileName = b.myFileName;
	}
	
	/**
	 * Return the distance between this body and another
	 * @param b the other body to which distance is calculated
	 * @return distance between this body and b
	 */
	public double calcDistance(Body b) {
		double dx = myXPos - b.myXPos;
		double dy = myYPos - b.myYPos;
		double r = Math.sqrt((dx*dx) + (dy*dy));
		return r;
	}
	
	/**
	 * Return the force exerted by a body on another
	 * @param p the other body which exerts the force
	 * @return the force of p on this body
	 */
	public double calcForceExertedBy(Body p) {
		double G = 6.67*1e-11;
		double m1 = myMass;
		double m2 = p.myMass;
		double r = calcDistance(p);
		double F = (G*m1*m2)/(r*r);
		return F;
	}
	
	/**
	 * Return the force exerted by a body on another in the x direction
	 * @param p the other body which exerts the force
	 * @return the force of p on this body in the x direction
	 */
	public double calcForceExertedByX(Body p) {
		double F = calcForceExertedBy(p);
		double r = calcDistance(p);
		double dx = p.myXPos - myXPos;
		double Fx = F*dx/r;
		return Fx;
	}
	
	/**
	 * Return the force exerted by a body on another in the y direction
	 * @param p the other body which exerts the force
	 * @return the force of p on this body in the y direction
	 */
	public double calcForceExertedByY(Body p) {
		double F = calcForceExertedBy(p);
		double r = calcDistance(p);
		double dy = p.myYPos - myYPos;
		double Fy = F*dy/r;
		return Fy;
	}
	
	/**
	 * Return the force exerted on a body by all other bodies in the x direction
	 * @param bodies the array of bodies which exert force on this body
	 * @return the net force on this body exerted by all the others in the x direction
	 */
	public double calcNetForceExertedByX(Body[] bodies) {
		double sum = 0;
		for(Body b : bodies) {
			if(! b.equals(this)) {
				sum += calcForceExertedByX(b);
			}
		}
		return sum;
	}
	
	/**
	 * Return the force exerted on a body by all other bodies in the y direction
	 * @param bodies the array of bodies which exert force on this body
	 * @return the net force on this body exerted by all the others in the y direction
	 */
	public double calcNetForceExertedByY(Body[] bodies) {
		double sum = 0;
		for(Body b : bodies) {
			if(! b.equals(this)) {
				sum += calcForceExertedByY(b);
			}
		}
		return sum;
	}
	
	/**
	 * Updates the position and velocity of a body after a
	 * short period of time
	 * @param deltaT the amount of time used to calculate new
	 * positions and velocities
	 * @param xforce net force exerted on this body by all other bodies
	 * in the x direction
	 * @param yforce net force exerted on this body by all other bodies
	 * in the y direction
	 */
	public void update(double deltaT, double xforce, double yforce) {
		double ax = xforce/myMass;
		double ay = yforce/myMass;
		double nvx = myXVel + deltaT*ax;
		double nvy = myYVel + deltaT*ay;
		double nx = myXPos + deltaT*nvx;
		double ny = myYPos + deltaT*nvy;
		myXPos = nx;
		myYPos = ny;
		myXVel = nvx;
		myYVel = nvy;
	}
	
	/**
	 * Draws image of a body in the simulation
	 */
	public void draw() {
		StdDraw.picture(myXPos, myYPos, "images/"+myFileName);
	}
}
